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
        StringBuilder accountNo = new StringBuilder();

        int j = (int) Math.ceil((double) sum / digits);

        if (j <= 9) {
            while (digits-- > 0 && (sum / digits) < 1)
                accountNo.append("0");

            int i = j;
            while (digits-- > 0 && sum >= 0) {
                if (i > 9)
                    i = j;
                if (sum < i)
                    i = sum;

                accountNo.append(i);
                sum -= i;

                ++i;
            }
        }

        if (digits == -1 && sum == 0)
            return accountNo.toString();

        throw new Exception("Account Number can not be generated");
    }

    public void deposit(double amount) {
        //add amount to balance
        if (amount > 0)
            balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if (balance - amount >= minBalance)
            balance -= amount;
        else
            throw new Exception("Insufficient Balance");
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