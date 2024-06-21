import java.util.Scanner;

 class temperatureConverter {

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5/9 + 273.15;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the temperature value: ");
        double temperature = scanner.nextDouble();

        System.out.print("Enter the unit of measurement (C/F/K): ");
        char unit = scanner.next().charAt(0);

        switch (unit) {
            case 'C':
            case 'c':
                double fahrenheitFromCelsius = celsiusToFahrenheit(temperature);
                double kelvinFromCelsius = celsiusToKelvin(temperature);
                System.out.printf("%.2f C is equal to %.2f F and %.2f K%n", temperature, fahrenheitFromCelsius, kelvinFromCelsius);
                break;

            case 'F':
            case 'f':
                double celsiusFromFahrenheit = fahrenheitToCelsius(temperature);
                double kelvinFromFahrenheit = fahrenheitToKelvin(temperature);
                System.out.printf("%.2f F is equal to %.2f C and %.2f K%n", temperature, celsiusFromFahrenheit, kelvinFromFahrenheit);
                break;

            case 'K':
            case 'k':
                double celsiusFromKelvin = kelvinToCelsius(temperature);
                double fahrenheitFromKelvin = kelvinToFahrenheit(temperature);
                System.out.printf("%.2f K is equal to %.2f C and %.2f F%n", temperature, celsiusFromKelvin, fahrenheitFromKelvin);
                break;

            default:
                System.out.println("Invalid unit of measurement. Please enter C, F, or K.");
                break;
        }

        scanner.close();
    }
}
