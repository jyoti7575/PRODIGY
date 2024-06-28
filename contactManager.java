import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class contactManager {
    private static final String FILE_NAME = "contacts.ser";
    private ArrayList<Contact> contacts;

    public contactManager() {
        contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    public void editContact(int index, Contact updatedContact) {
        if (index >= 0 && index < contacts.size()) {
            contacts.set(index, updatedContact);
            saveContacts();
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            saveContacts();
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    private void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contacts = (ArrayList<Contact>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing contacts found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nContact Management System");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();
                    contactManager.addContact(new Contact(name, phoneNumber, email));
                    break;
                case 2:
                    contactManager.viewContacts();
                    break;
                case 3:
                    System.out.print("Enter contact number to edit: ");
                    int editIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    if (editIndex >= 0 && editIndex < contactManager.contacts.size()) {
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new phone number: ");
                        String newPhoneNumber = scanner.nextLine();
                        System.out.print("Enter new email address: ");
                        String newEmail = scanner.nextLine();
                        contactManager.editContact(editIndex, new Contact(newName, newPhoneNumber, newEmail));
                    } else {
                        System.out.println("Invalid contact number.");
                    }
                    break;
                case 4:
                    System.out.print("Enter contact number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    if (deleteIndex >= 0 && deleteIndex < contactManager.contacts.size()) {
                        contactManager.deleteContact(deleteIndex);
                    } else {
                        System.out.println("Invalid contact number.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
