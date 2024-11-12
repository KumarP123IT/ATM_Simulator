import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {

    static Scanner input = new Scanner(System.in);

    private boolean flag = true ;
    private double balance = 1500.0;
    private final List<String> transactionHistory = new ArrayList<>();

    void selectOption() {
        byte option;

        do {
            System.out.println("Enter the option below :");
            System.out.println("1. Withdrawal");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");

            try {
                option = input.nextByte();

                if (option <= 5 && option > 0) {
                    showLoading();

                    switch (option) {
                        case 1:
                            withdrawal();
                            break;
                        case 2:
                            deposit();
                            break;
                        case 3:
                            checkBalance();
                            break;
                        case 4:
                            showTransactionHistory();
                            break;
                        case 5:
                            System.out.println();
                            System.out.println("Thank you for using ATM. Goodbye!");
                            flag = false;
                            break;
                    }
                } 
                else {
                    System.out.println("Invalid option. Please select a valid option.");
                }
            }
            catch(Exception e){
                System.out.println("Invalid input. Please enter a numeric option.");
                input.next();
            }
        }
        while(flag);
    }


    void withdrawal() {
        System.out.println();

        System.out.print("Enter the 4-digit PIN: ");
        int pinNumber = input.nextInt();
        int length = String.valueOf(pinNumber).length();

        try{
            if (length == 4) {
                showLoading();
                System.out.println();
                System.out.println("Enter the withdrawal amount: ");
                double amount = input.nextDouble();

                if(amount > 0 && amount <= balance) {
                    balance -= amount;
                    System.out.println("Withdrawal Successful! New balance: $" +String.format("%.2f" , balance));
                    logTransaction("Withdrawal: $" + amount);
                }
                else {
                    System.out.println("Insufficient balance or invalid amount.");
                }
            }
            else {
                System.out.println("Invalid PIN. Please enter a 4-digit number.");
            }
        }
        catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            input.next();
        }
    }


    void deposit() {
        System.out.println();
        System.out.print("Enter the 4-digit PIN: ");
        int pinNumber = input.nextInt();
        int length = String.valueOf(pinNumber).length();

        if (length == 4) {
            System.out.println("Enter the deposit amount: ");

            try {
                double amount = input.nextDouble();

                if(amount > 0) {
                    balance += amount;
                    System.out.println("Deposit successful! New balance: $" +String.format("%.2f" , balance));
                    logTransaction("Deposited: $" + amount);
                }
                else {
                    System.out.println("Invalid amount. Please enter a positive value.");
                }
            }catch (Exception e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                input.next();
            }
        }
        else {
            System.out.println("Invalid PIN. Please enter a 4-digit number.");
        }

    }

    void showLoading() {
        System.out.print("processing your request");

        for (int i = 0; i < 3; i++ ) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    void checkBalance() {
        System.out.println();
        System.out.print("Enter the 4-digit PIN: ");
        int pinNumber = input.nextInt();
        int length = String.valueOf(pinNumber).length();

        if (length == 4) {
            System.out.printf("Your current balance is: $%.2f%n", balance);
        }
        else {
            System.out.println("Invalid PIN. Please enter a 4-digit number.");
        }

    }

    void showTransactionHistory() {
        System.out.println();
        System.out.print("Enter the 4-digit PIN: ");
        int pinNumber = input.nextInt();
        int length = String.valueOf(pinNumber).length();

        if (length == 4) {
            if (transactionHistory.isEmpty()) {
                System.out.println("No transaction available.");
            }
            else {
                System.out.println("Transaction History:");

                for(String transaction : transactionHistory) {
                    System.out.println(transaction);
                    
                }
            }
        }
        else {
            System.out.println("Invalid PIN. Please enter a 4-digit number.");

        }

    }


    void logTransaction(String transaction) {
        transactionHistory.add(transaction);

    }


    public static  void main(String[] args) {
        ATM atm = new ATM();

        System.out.println("\nWelcome to the ATM Machine!");
        atm.selectOption();
    }

}
