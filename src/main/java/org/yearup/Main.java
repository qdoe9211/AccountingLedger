package org.yearup;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome!");
        System.out.println();

        AccountingLedgerApp app = new AccountingLedgerApp();
        AccountingLedgerApp.run();
    }
}