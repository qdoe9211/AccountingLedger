package org.yearup;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public static void addADeposit() {


    }

    public static void makeAPayment() {

    }

    public static void viewLedger() {

    }

}
