package org.yearup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedgerApp {

    static ArrayList<Transactions> accountLedger = new ArrayList<>();
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    static Scanner scanner = new Scanner(System.in);

    public static void run() throws Exception {

        FileReader fileReader = new FileReader("transactions.csv");
        BufferedReader reader = new BufferedReader(fileReader);

        reader.readLine();

        String line = reader.readLine();
        while (line != null) {
            if (line.isEmpty()) {
                line = reader.readLine();
                continue;
            }

            String[] columns = line.split("\\|");

            LocalDate date = LocalDate.parse(columns[0], dateFormatter);
            LocalTime time = LocalTime.parse(columns[1], timeFormatter);
            String description = columns[2];
            String vendor = columns[3];
            double amount = Double.parseDouble(columns[4]);

            Transactions transactions = new Transactions(date, time, description, vendor, amount);
            accountLedger.add(transactions);
            line = reader.readLine();
        }
        reader.close();

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println(" D - Add A Deposit");
            System.out.println(" P - Make A Payment");
            System.out.println(" L - View Ledger");
            System.out.println(" X - Exit");
            System.out.println("Enter your option: ");
            char option = scanner.next().charAt(0);
            scanner.nextLine();

            switch (option) {
                case 'D':
                case 'd':
                    addADeposit();
                    break;
                case 'P':
                case 'p':
                    makeAPayment();
                    break;
                case 'L':
                case 'l':
                    viewLedger();
                    break;
                case 'X':
                case 'x':
                    System.out.println("Have a great day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        }
    }

    public static void addADeposit() throws Exception {

        System.out.println("Enter the date of the deposit (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine(), dateFormatter);
        System.out.println("Enter the time of the deposit (HH:mm:ss): ");
        LocalTime time = LocalTime.parse(scanner.nextLine(), timeFormatter);
        System.out.println("Enter a description for the deposit: ");
        String description = scanner.nextLine();
        System.out.println("Enter the name of the vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount of the deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Transactions deposit = new Transactions(date, time, description, vendor, amount);
        accountLedger.add(deposit);

        FileWriter writer = new FileWriter("transactions.csv", true);
        writer.write("\n" + deposit.getDate().format(dateFormatter) + "|" + deposit.getTime().format(timeFormatter) + "|" + deposit.getDescription() + "|" + deposit.getVendor() + "|" + deposit.getAmount());
        writer.close();

        System.out.println("Your deposit has been added.");
    }

    public static void makeAPayment() throws Exception {

        System.out.println("Enter the date of the payment (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine(), dateFormatter);
        System.out.println("Enter the time of the payment (HH:mm:ss): ");
        LocalTime time = LocalTime.parse(scanner.nextLine(), timeFormatter);
        System.out.println("Enter a description for the payment: ");
        String description = scanner.nextLine();
        System.out.println("Enter the name of the vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount of the payment: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Transactions debit = new Transactions(date, time, description, vendor, amount);
        accountLedger.add(debit);

        FileWriter writer = new FileWriter("transactions.csv", true);
        writer.write("\n" + debit.getDate().format(dateFormatter) + "|" + debit.getTime().format(timeFormatter) + "|" + debit.getDescription() + "|" + debit.getVendor() + "|-" + debit.getAmount());
        writer.close();

        System.out.println("Your payment has been added.");
    }

    public static void viewLedger() {


    }

}
