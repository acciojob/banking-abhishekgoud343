package com.driver;

public class CurrentAccount extends BankAccount {

    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;

        if (balance < 5000)
            throw new Exception("Insufficient Balance");
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if (!valid(tradeLicenseId)) {
            String rearrangedString = rearrangeString(tradeLicenseId);
            if (rearrangedString.equals(""))
                throw new Exception("Valid License can not be generated");
            else
                tradeLicenseId = rearrangedString;
        }
    }

    boolean valid(String str) {
        for (int i = 0; i < str.length() - 1; ++i)
            if (str.charAt(i) == str.charAt(i + 1))
                return false;

        return true;
    }

    static String rearrangeString(String str) {
        int N = str.length();
        if (N == 0)
            return "";

        int[] count = new int[26];
        for (int i = 0; i < 26; ++i)
            count[i] = 0;
        for (char ch : str.toCharArray())
            count[ch - 'A']++;

        char ch_max = getMaxCountChar(count);
        int maxCount = count[ch_max - 'A'];

        // check if the result is possible or not
        if (maxCount > (N + 1) / 2)
            return "";

        StringBuilder res = new StringBuilder();
        res.append(" ".repeat(N));

        int ind = 0;
        // filling the most frequently occurring char in the even indices
        while (maxCount-- > 0) {
            res = new StringBuilder(res.substring(0, ind) + ch_max + res.substring(ind + 1));
            ind += 2;
        }
        count[ch_max - 'A'] = 0;

        // now filling the other Chars; first filling the even positions and then the odd positions
        for (int i = 0; i < 26; ++i) {
            while (count[i]-- > 0) {
                ind = (ind >= N) ? 1 : ind;
                res = new StringBuilder(res.substring(0, ind) + (char) ('A' + i) + res.substring(ind + 1));
                ind += 2;
            }
        }

        return res.toString();
    }

    static char getMaxCountChar(int[] count) {
        int max = 0;
        char ch = 0;

        for (int i = 0; i < 26; ++i)
            if (count[i] > max) {
                max = count[i];
                ch = (char) (i + 'A');
            }

        return ch;
    }
}