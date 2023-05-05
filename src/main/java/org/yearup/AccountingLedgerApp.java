package org.yearup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AccountingLedgerApp {

    static ArrayList<Transactions> accountingLedger = new ArrayList<>();
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    static Scanner scanner = new Scanner(System.in);

    public static void run() throws Exception
    {
        FileReader fileReader = new FileReader("transactions.csv");
        BufferedReader reader = new BufferedReader(fileReader);

        reader.readLine();

        String line = reader.readLine();
        while (line != null)
        {
            if (line.isEmpty())
            {
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

        while (true)
        {
            char option = displayHomeScreen();

            switch (option)
            {
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
                    System.out.println();
                    System.out.println(ColorCodes.ORANGE + "Have a great day!" + ColorCodes.RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(ColorCodes.RED + "Invalid option. Please try again." + ColorCodes.RESET);
                    break;
            }
        }
    }

    public static char displayHomeScreen()
    {
        System.out.println();
        System.out.println(ColorCodes.YELLOW + ColorCodes.BLACK_BACKGROUND + "HOME SCREEN" + ColorCodes.RESET);
        System.out.println(ColorCodes.YELLOW + "---------------------" + ColorCodes.RESET);
        System.out.println("What would you like to do?");
        System.out.println(" D - Add A Deposit");
        System.out.println(" P - Make A Payment");
        System.out.println(" L - View Ledger");
        System.out.println(" X - Exit");
        System.out.print("Enter your option: ");
        char option = scanner.nextLine().charAt(0);
        return option;
    }


    public static void addADeposit() throws Exception
    {
        System.out.println();
        System.out.println(ColorCodes.RED + ColorCodes.BLACK_BACKGROUND + "ADD A DEPOSIT" + ColorCodes.RESET);
        System.out.println(ColorCodes.RED + "---------------------" + ColorCodes.RESET);
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

        Collections.reverse(accountingLedger);

        FileWriter writer = new FileWriter("transactions.csv", true);
        writer.write("\n" + deposit.getDate().format(dateFormatter) + "|" + deposit.getTime().format(timeFormatter) + "|" + deposit.getDescription() + "|" + deposit.getVendor() + "|" + deposit.getAmount());
        writer.close();

        System.out.println();
        System.out.println(ColorCodes.GREEN + "Your deposit has been added." + " " + deposit.getDate().format(dateFormatter) + "|" + deposit.getTime().format(timeFormatter) + "|" + deposit.getDescription() + "|" + deposit.getVendor() + "|" + deposit.getAmount() + ColorCodes.RESET);
        System.out.println(ColorCodes.CYAN + "\f" + ColorCodes.RESET);
    }

    public static void makeAPayment() throws Exception
    {
        System.out.println();
        System.out.println(ColorCodes.RED + ColorCodes.BLACK_BACKGROUND + "ADD A PAYMENT" + ColorCodes.RESET);
        System.out.println(ColorCodes.RED + "-------------------" + ColorCodes.RESET);
        System.out.println("Enter the date of the payment (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine(), dateFormatter);
        System.out.println("Enter the time of the payment (HH:mm:ss): ");
        LocalTime time = LocalTime.parse(scanner.nextLine(), timeFormatter);
        System.out.println("Enter a description for the payment: ");
        String description = scanner.nextLine();
        System.out.println("Enter the name of the vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount of the payment: ");
        double amount = scanner.nextDouble() * -1;
        scanner.nextLine();

        Transactions debit = new Transactions(date, time, description, vendor, amount);
        accountingLedger.add(debit);

        Collections.reverse(accountingLedger);

        FileWriter writer = new FileWriter("transactions.csv", true);
        writer.write("\n" + debit.getDate().format(dateFormatter) + "|" + debit.getTime().format(timeFormatter) + "|" + debit.getDescription() + "|" + debit.getVendor() + "|" + debit.getAmount());
        writer.close();

        System.out.println();
        System.out.println(ColorCodes.GREEN + "Your payment has been added." + " " + debit.getDate().format(dateFormatter) + "|" + debit.getTime().format(timeFormatter) + "|" + debit.getDescription() + "|" + debit.getVendor() + "|" + debit.getAmount() + ColorCodes.RESET);
        System.out.println(ColorCodes.CYAN + "\f" + ColorCodes.RESET);
    }

    public static void viewLedger()
    {
        while (true)
        {
            System.out.println();
            System.out.println(ColorCodes.CYAN + ColorCodes.BLACK_BACKGROUND + "LEDGER SCREEN" + ColorCodes.RESET);
            System.out.println(ColorCodes.CYAN + "------------------" + ColorCodes.RESET);
            System.out.println(" A - Display All Entries");
            System.out.println(" D - Display All Deposits");
            System.out.println(" P - Display All Payments");
            System.out.println(" R - Reports");
            System.out.println(" H - Go Back To Homepage");
            System.out.print("Enter your option: ");
            char option = scanner.next().charAt(0);
            scanner.nextLine();

            switch (option)
            {
                case 'A':
                case 'a':
                    System.out.println();
                    System.out.println(ColorCodes.PURPLE + ColorCodes.BLACK_BACKGROUND + "All Entries" + ColorCodes.RESET);
                    System.out.println(ColorCodes.PURPLE + "Date|Time|Description|Vendor|Amount" + ColorCodes.RESET);
                    System.out.println(ColorCodes.PURPLE + "----------------------------------------" + ColorCodes.RESET);

                    Collections.sort(accountingLedger, new Comparator<Transactions>()
                    {
                        public int compare(Transactions t1, Transactions t2) {
                            return t2.getDate().compareTo(t1.getDate());
                        }
                    });

                    for (Transactions transactions : accountingLedger)
                    {
                        System.out.println(transactions.getDate().format(dateFormatter) + "|" + transactions.getTime().format(timeFormatter) + "|" + transactions.getDescription() + "|" + transactions.getVendor() + "|" + transactions.getAmount());
                        System.out.println();
                    }
                    break;
                case 'D':
                case 'd':
                    System.out.println();
                    System.out.println(ColorCodes.PURPLE + ColorCodes.BLACK_BACKGROUND + "All Deposits" + ColorCodes.RESET);
                    System.out.println(ColorCodes.PURPLE + "Date|Time|Description|Vendor|Amount" + ColorCodes.RESET);
                    System.out.println(ColorCodes.PURPLE + "---------------" + ColorCodes.RESET);
                    for (Transactions transactions : accountingLedger)
                    {
                        if (transactions.getAmount() >= 0)
                        {
                            System.out.println(transactions.getDate().format(dateFormatter) + "|" + transactions.getTime().format(timeFormatter) + "|" + transactions.getDescription() + "|" + transactions.getVendor() + "|" + transactions.getAmount());
                        }
                    }
                    break;
                case 'P':
                case 'p':
                    System.out.println();
                    System.out.println(ColorCodes.PURPLE + ColorCodes.BLACK_BACKGROUND + "All Payments" + ColorCodes.RESET);
                    System.out.println(ColorCodes.PURPLE + "Date|Time|Description|Vendor|Amount" + ColorCodes.RESET);
                    System.out.println(ColorCodes.PURPLE + "---------------" + ColorCodes.RESET);
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
                    return;
                default:
                    System.out.println(ColorCodes.RED + "Invalid option. Please try again." + ColorCodes.RESET);
                    break;
            }
        }
    }

    public static void viewReports()
    {
        System.out.println();
        System.out.println(ColorCodes.CYAN + ColorCodes.BLACK_BACKGROUND + "REPORTS" + ColorCodes.RESET);
        System.out.println(ColorCodes.CYAN + "------------------" + ColorCodes.RESET);
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

        switch (command)
        {
            case 1:
                monthToDateReport();
                break;
            case 2:
                previousMonthReport();
                break;
            case 3:
                yearToDateReport();
                break;
            case 4:
                previousYearReport();
                break;
            case 5:
                vendorReport();
            case 0:
                return;
            default:
                System.out.println(ColorCodes.RED + "Invalid option. Please try again." + ColorCodes.RESET);
                break;
        }

    }

    public static void monthToDateReport()
    {
        System.out.println();
        System.out.println(ColorCodes.BLACK + ColorCodes.WHITE_BACKGROUND + "MONTH TO DATE REPORT" + ColorCodes.RESET);
        System.out.println(ColorCodes.WHITE + "------------------------" + ColorCodes.RESET);
        HashMap<String, Double> ledger = new HashMap<>();
        double monthToDateTotal = 0.0;
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        for (Transactions transactions : accountingLedger)
        {
            if (transactions.getDate().getYear() == currentYear
                    && transactions.getDate().getMonthValue() == currentMonth)
            {
                String description = transactions.getDescription();
                double amount = transactions.getAmount();
                monthToDateTotal += amount;

                if (ledger.containsKey(description))
                {
                    double currentAmount = ledger.get(description);
                    ledger.put(description, currentAmount + amount);
                } else {
                    ledger.put(description, amount);
                }
            }
        }

        for (HashMap.Entry<String, Double> entry : ledger.entrySet())
        {
            String description = entry.getKey();
            double amount = entry.getValue();
            System.out.printf("%-30s $%.2f%n", description, amount);
        }
        System.out.println();
        System.out.printf("%-30s $%.2f%n", "Month To Date Total:", monthToDateTotal);
        System.out.println(ColorCodes.CYAN + "\f" + ColorCodes.RESET);
        viewReports();
    }

    public static void previousMonthReport()
    {
        System.out.println();
        System.out.println(ColorCodes.BLACK + ColorCodes.WHITE_BACKGROUND + "PREVIOUS MONTH REPORT" + ColorCodes.RESET);
        System.out.println(ColorCodes.WHITE + "------------------------" + ColorCodes.RESET);
        HashMap<String, Double> ledger = new HashMap<>();
        double monthToDateTotal = 0.0;
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        for (Transactions transactions : accountingLedger)
        {
            if (transactions.getDate().getYear() == currentYear
                    && transactions.getDate().getMonthValue() == currentMonth)
            {
                String description = transactions.getDescription();
                double amount = transactions.getAmount();
                monthToDateTotal += amount;

                if (ledger.containsKey(description))
                {
                    double currentAmount = ledger.get(description);
                    ledger.put(description, currentAmount + amount);
                } else {
                    ledger.put(description, amount);
                }
            }
        }

        for (HashMap.Entry<String, Double> entry : ledger.entrySet())
        {
            String description = entry.getKey();
            double amount = entry.getValue();
            System.out.printf("%-30s $%.2f%n", description, amount);
        }
        System.out.println();
        System.out.printf("%-30s $%.2f%n", "Month To Date Total:", monthToDateTotal);
        System.out.println(ColorCodes.CYAN + "\f" + ColorCodes.RESET);
        viewReports();
    }
    public static void yearToDateReport()
    {
        System.out.println();
        System.out.println(ColorCodes.BLACK + ColorCodes.WHITE_BACKGROUND + "YEAR TO DATE REPORT" + ColorCodes.RESET);
        System.out.println(ColorCodes.WHITE + "-----------------------" + ColorCodes.RESET);
        HashMap<String, Double> ledger = new HashMap<>();
        double yearToDateTotal = 0.0;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        for (Transactions transactions : accountingLedger)
        {
            if (transactions.getDate().getYear() == currentYear)
            {
                String description = transactions.getDescription();
                double amount = transactions.getAmount();

                yearToDateTotal += amount;

                if (ledger.containsKey(description))
                {
                    double currentAmount = ledger.get(description);
                    ledger.put(description, currentAmount + amount);
                } else
                {
                    ledger.put(description, amount);
                }
            }
        }
        for (HashMap.Entry<String, Double> entry : ledger.entrySet())
        {
            String description = entry.getKey();
            double amount = entry.getValue();
            System.out.printf("%-35s $%.2f%n", description, amount);
        }
        System.out.println();
        System.out.printf("%-35s $%.2f%n", "Year To Date Total:", yearToDateTotal);
        System.out.println(ColorCodes.CYAN + "\f" + ColorCodes.RESET);
        viewReports();
    }

    public static void previousYearReport()
    {
        System.out.println();
        System.out.println(ColorCodes.BLACK + ColorCodes.WHITE_BACKGROUND + "PREVIOUS YEAR REPORT" + ColorCodes.RESET);
        System.out.println(ColorCodes.WHITE + "-----------------------" + ColorCodes.RESET);
        HashMap<String, Double> ledger = new HashMap<>();
        double yearToDateTotal = 0.0;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR) - 1;

        for (Transactions transactions : accountingLedger) {
            if (transactions.getDate().getYear() == currentYear) {
                String description = transactions.getDescription();
                double amount = transactions.getAmount();

                yearToDateTotal += amount;

                if (ledger.containsKey(description)) {
                    double currentAmount = ledger.get(description);
                    ledger.put(description, currentAmount + amount);
                } else {
                    ledger.put(description, amount);
                }
            }
        }
        for (HashMap.Entry<String, Double> entry : ledger.entrySet())
        {
            String description = entry.getKey();
            double amount = entry.getValue();
            System.out.printf("%-35s $%.2f%n", description, amount);
        }
        System.out.println();
        System.out.printf("%-35s $%.2f%n", "Year To Date Total:", yearToDateTotal);
        System.out.println(ColorCodes.CYAN + "\f" + ColorCodes.RESET);
        viewReports();
    }
    public static void vendorReport()
    {
        System.out.print("Enter the vendor name: ");
        String vendorName = scanner.nextLine().toLowerCase();
        System.out.println("Entries for vendor " + vendorName + ":");
        System.out.println();
        System.out.println(ColorCodes.BLACK + ColorCodes.WHITE_BACKGROUND + "VENDOR REPORT" + ColorCodes.RESET);
        System.out.println(ColorCodes.WHITE + "-----------------------" + ColorCodes.RESET);

        for (Transactions transaction : accountingLedger)
        {
            if (transaction.getVendor().toLowerCase().equals(vendorName))
            {
                System.out.println(transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount());
            }
        }System.out.println(ColorCodes.CYAN + "\f" + ColorCodes.RESET);
    }
}
