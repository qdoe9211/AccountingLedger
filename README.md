# AccountingLedger


***Description***

Accounting Ledger is a program that allows users to budget by keeping track of their earnings, and spending habits. Users
are able to add entries to the ledger in the form of deposits or payments. Users are walked through both processes, and are
able to see their entries in the ledger. Users also have the option to view monthly and annual reports.

The user is first greeted with a "Welcome!" message, and presented with the home screen. The home screen lists the options
to add a deposit, add a payment, view ledger or to exit out of the program. The displayHomeScreen method was created to 
prompt the user for their input, which lets the program know which screen to navigate to next.

If the user decides to add a deposit, they will be prompted to enter the following information for the deposit: date, time,
brief description of the deposit, the vendor's name, and the amount of the deposit. The user is then given confirmation
that their deposit has been successfully added, and displays the entry the user just input.

If the user decides to make a payment, they will be prompted to enter the same information as the deposit. The user is given
confirmation that their payment has been added successfully, and displays the user's input. The amount will show as a
negative as it was money taken out of the account, and not added.

If the user chooses to view ledger, they will be taken to the Ledger Screen. The Ledger Screen gives the options to display
all entries, display only the deposits, display only the payments, to view reports, or to go back to the homepage. 

If the user decides to display all entries, every entry in the ledger, both deposits and payments, will be displayed. The
oldest will slow last, and the newer entries show first. The list displays the date, time, description, vendor name, and
amount for every entry. The ledger screen is re-populated at the bottom of the screen to easily allow the user to choose
another option.

If the user decides to view only the deposits, a list of all the deposits that have been added to the ledger will be displayed.
The ledger screen is re-populated at the bottom of the screen to easily allow the user to make another choice.

If the user decides to view only the payments, a list of all the payments that have been added to the ledger will be displayed.
The ledger screen is re-populated at the bottom of the screen to easily allow the user to make another choice.

If the user decides to view reports, they will then be taken to the Reports Screen. The Reports Screen displays the option
to view reports for the current month (month-to-date), the previous month, the current year (year-to-date), the previous
year, or to search by vendor.

If the user decides to view the Month-To-Date Report, they will see all the transactions for the current month, and the
total balance of the transactions. The Reports Screen is re-populated at the bottom of the screen to allow the user to 
easily make another choice.
If the user decides to view the Previous Month Report, they will see all the transactions from the previous month, and 
the total balance of the transactions. The Reports Screen is re-populates at the bottom of the screen to allow the user to
easily make another choice.

If the user decides to view the Year-To-Date Report, they will see all the transactions from the current year, and the 
total balance of those transactions. The Reports Screen is re-populated at the bottom of the screen to allow the user to
easily make another choice.
If the user decides to view the Previous Year Report, they will see all the transactions from the previous year, and the
total balance of those transactions. The Reports Screen is re-populated at the bottom of the screen to allow the user to
easily make another choice.

If the user decides to search by vendor, they will be prompted to enter the name of the vendor they would like to search.
After entering the name of the vendor, all transactions between that vendor will populate. The Reports Screen is re-populated
at the bottom of the screen to allow the user to easily make another choice.

If the user decides to go back, they will be taken to the Ledger Screen. The user can choose another option again or enter
H to back to the home screen. On the home screen, if the user is finished with the program, they can then enter X to exit 
the program. The user will be met with a message that reads, "Have a great day!", and the program ends.

On each screen, if the user enters an invalid response to any of the prompts, they will be met with an "Invalid Response.
Please try again." message, and will be taken back to the previous screen to renter their prompt.


***Interesting Piece of Code***

An interesting piece of code I used was the Comparator interface. The method compares the date of the transactions, and
lists them in descending order to show the newest entries first, and the oldest entries last. I made sure to show the newest
entries first by listing t2 first and comparing it to t1.

Comparator Interface Visual

![Screenshot (526)](https://user-images.githubusercontent.com/130028689/236374226-087aa567-2deb-4050-ab1c-edc80bde417c.png)

***Program Visuals***

![Screenshot (526)](https://user-images.githubusercontent.com/130028689/236374226-087aa567-2deb-4050-ab1c-edc80bde417c.png)
![Screenshot (513)](https://user-images.githubusercontent.com/130028689/236374243-873c824f-b648-43e9-b340-3a793c6ec8f1.png)
![Screenshot (514)](https://user-images.githubusercontent.com/130028689/236374262-7fba0fdc-f4ee-408b-83bc-ec8d1512e237.png)
![Screenshot (515)](https://user-images.githubusercontent.com/130028689/236374269-81fb0fc7-f7bf-4e44-ad77-53b240fd0126.png)
![Screenshot (516)](https://user-images.githubusercontent.com/130028689/236374289-68d1adbc-1eb3-4a42-be9f-a4d287526439.png)
![Screenshot (517)](https://user-images.githubusercontent.com/130028689/236374290-80244aa5-3a03-4d8f-a8d7-0d2d6f7d564c.png)
![Screenshot (518)](https://user-images.githubusercontent.com/130028689/236374291-24276d36-1bb8-46ca-a819-c33976971929.png)
![Screenshot (519)](https://user-images.githubusercontent.com/130028689/236374292-b2972ed5-89de-4ce2-b750-538d42f4002a.png)
![Screenshot (520)](https://user-images.githubusercontent.com/130028689/236374293-8d2cf366-cf9c-4701-9ec3-7b5723c9e40a.png)
![Screenshot (521)](https://user-images.githubusercontent.com/130028689/236374294-cf9bf5b8-79d1-45d8-9a53-c3c34a8d202b.png)
![Screenshot (522)](https://user-images.githubusercontent.com/130028689/236374295-1a05b1c8-05dd-437c-bf0e-1794be6fa952.png)
![Screenshot (523)](https://user-images.githubusercontent.com/130028689/236374296-bab7f1ba-376c-41cf-9aa5-7c116655e3e9.png)
![Screenshot (524)](https://user-images.githubusercontent.com/130028689/236374298-421bbb81-d2e0-4aab-9782-b27331b965b1.png)
![Screenshot (525)](https://user-images.githubusercontent.com/130028689/236374286-1994d110-5742-45ac-ade4-f43162cc100e.png)



