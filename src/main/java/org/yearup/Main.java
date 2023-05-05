package org.yearup;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println(ColorCodes.ORANGE + "Welcome!" + ColorCodes.RESET);

        AccountingLedgerApp app = new AccountingLedgerApp();
        AccountingLedgerApp.run();
    }
}