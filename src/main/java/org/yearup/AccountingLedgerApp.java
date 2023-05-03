package org.yearup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class AccountingLedgerApp {

    static ArrayList<Transactions> accountingLedger = new ArrayList<>();
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
            accountingLedger.add(transactions);
            line = reader.readLine();
        }
        reader.close();

        while (true) {

            char option = displayHomeScreen();

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

    public static char displayHomeScreen() {
        System.out.println("What would you like to do?");
        System.out.println(" D - Add A Deposit");
        System.out.println(" P - Make A Payment");
        System.out.println(" L - View Ledger");
        System.out.println(" X - Exit");
        System.out.print("Enter your option: ");
        char option = scanner.nextLine().charAt(0);
        return option;
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
        accountingLedger.add(deposit);

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
        accountingLedger.add(debit);

        FileWriter writer = new FileWriter("transactions.csv", true);
        writer.write("\n" + debit.getDate().format(dateFormatter) + "|" + debit.getTime().format(timeFormatter) + "|" + debit.getDescription() + "|" + debit.getVendor() + "|-" + debit.getAmount());
        writer.close();

        System.out.println("Your payment has been added.");
    }

    public static void viewLedger() {

        while (true) {

            System.out.println(" A - Display All Entries");
            System.out.println(" D - Display All Deposits");
            System.out.println(" P - Display All Payments");
            System.out.println(" R - Reports");
            System.out.println(" H - Go Back To Homepage");
            System.out.print("Enter your option: ");
            char option = scanner.next().charAt(0);
            scanner.nextLine();

            switch (option) {
                case 'A':
                case 'a':
                    System.out.println("All Entries");
                    System.out.println("Date|Time|Description|Vendor|Amount");
                    System.out.println("--------------");
                    for (Transactions transactions : accountingLedger) {
                        System.out.println(transactions.getDate().format(dateFormatter) + "|" + transactions.getTime().format(timeFormatter) + "|" + transactions.getDescription() + "|" + transactions.getVendor() + "|" + transactions.getAmount());
                        System.out.println();
                    }
                    break;
                case 'D':
                case 'd':
                    System.out.println("All Deposits");
                    System.out.println("---------------");
                    for (Transactions transactions : accountingLedger) {
                        if (transactions.getAmount() >= 0) {
                            System.out.println(transactions.getDate().format(dateFormatter) + "|" + transactions.getTime().format(timeFormatter) + "|" + transactions.getDescription() + "|" + transactions.getVendor() + "|" + transactions.getAmount());
                        }
                    }
                    break;
                case 'P':
                case 'p':
                    System.out.println("All Payments");
                    System.out.println("---------------");
                    for (Transactions transactions : accountingLedger) {
                        if (transactions.getAmount() < 0) {
                            System.out.println(transactions.getDate().format(dateFormatter) + "|" + transactions.getTime().format(timeFormatter) + "|" + transactions.getDescription() + "|" + transactions.getVendor() + "|" + transactions.getAmount());
                        }
                    }
                    break;
                case 'R':
                case 'r':
                    viewReports();
                    break;
                case 'H':
                case 'h':
                    displayHomeScreen();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void viewReports() {

        System.out.println("Which report would you like?");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("0) Back");
        System.out.print("Enter your option: ");
        int command = scanner.nextInt();
        scanner.nextLine();

        switch (command) {
            case 1:
                System.out.println("Month To Date Report");
                System.out.println("-----------------------------");
                break;
            case 2:
                System.out.println("Previous Month Report");
                break;
            case 3:
                System.out.println("Year To Date Report");
                break;
            case 4:
                System.out.println("Previous Year Report");
            case 5:
                System.out.println("Vendor Report");
            case 0:
                displayHomeScreen();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;


        }


    }
}
