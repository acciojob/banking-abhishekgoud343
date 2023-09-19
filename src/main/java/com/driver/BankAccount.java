package com.driver;

public class BankAccount {

    private final String name;
    private double balance;
    private final double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception {
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(digits * 9 < sum)
            throw new Exception("Account Number can not be generated");

        StringBuilder accountNo = new StringBuilder();

        while (digits-- > 0 && sum > 0) {
            if (sum >= 9) {
                accountNo.append(9);
                sum -= 9;
            }
            else {
                accountNo.append(sum);
                sum = 0;
            }
        }

        while (digits-- > 0)
            accountNo.append(0);

        return accountNo.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
        if (amount > 0)
            balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if (balance - amount < minBalance)
            throw new Exception("Insufficient Balance");

        balance -= amount;
    }

    public String getName() {
        return name;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public double getBalance() {
        return balance;
    }
}