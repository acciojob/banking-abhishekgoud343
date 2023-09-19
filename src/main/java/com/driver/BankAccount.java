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

        int j = (int) Math.ceil((double) sum / digits);
        int i = j;
        while (digits > 0 && sum >= 0) {
            if (i > 9)
                i = j;
            if (sum < i)
                i = sum;

            accountNo.append(i);
            sum -= i;

            ++i;
            --digits;
        }

//        while (digits > 0) {
//            accountNo.append(0);
//            --digits;
//        }

        return accountNo.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
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