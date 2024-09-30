package com.pluralsight;

import java.util.Scanner;

public class FinancialCalculators {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        chooseCalculator();

    }

    // prompt user for what calculator they want to use
    private static void chooseCalculator() {
        int typeCalculator = 0;

        do {
            System.out.println("What kind of Calculator would you like to use? \n" +
                    "(1) Mortgage Calculator \n" +
                    "(2) Future Value Calculator \n" +
                    "(3) Present Value Calculator \n");
            typeCalculator = scanner.nextInt();
        } while (typeCalculator < 0 || typeCalculator > 3);

        switch (typeCalculator) {
            case 1:
                System.out.println("You are choosing Mortgage Calculator");
                mortgage();
                break;
            case 2:
                System.out.println("You are choosing Future Value Calculator");
                futureValue();
                break;
            case 3:
                System.out.println("You are choosing Present Value Calculator");
                presentValue();
                break;

        }
    }

    // mortgage calculator
    private static void mortgage() {
        System.out.print("what is your principal: ");
        float principal = scanner.nextFloat();

        System.out.print("what is your interest rate: ");
        float interestRate = scanner.nextFloat();
        float monthlyRate = interestRate / 12 / 100;

        System.out.print("what is your loan length in years: ");
        int loanLength = scanner.nextInt();
        int lengthInMonth = loanLength * 12;

        float monthlyPayment = (float) (principal *
                        (monthlyRate * Math.pow(1 + monthlyRate, lengthInMonth)) /
                        (Math.pow(1 + monthlyRate, lengthInMonth) - 1));

        float totalInterest = monthlyPayment * lengthInMonth - principal;

        System.out.printf("A $%.2f loan at %.2f%% interest for %d years would have a " +
                "$%.2f/mo payment with a total interest of $%.2f",
                principal, interestRate, loanLength, monthlyPayment, totalInterest);
    }

    // future value
    private static void futureValue() {
        System.out.print("what is your deposit: ");
        float deposit = scanner.nextFloat();

        System.out.print("what is your interest rate: ");
        float interestRate = scanner.nextFloat() ;

        System.out.print("what is deposit length in years: ");
        int length = scanner.nextInt();

        float total = (float) (deposit * Math.pow((1 + interestRate / 100f / 365f), 365f * length));
        float earn = total - deposit;

        System.out.printf("If you deposit $%.2f in a CD that earns %.2f%% interest and matures in %d years, your CD's ending balance will be $%.2f and you would have earned $%.2f in interest",
                deposit, interestRate, length, total, earn);
    }

    // present value
    private static void presentValue() {
        System.out.print("what is your monthly payout: ");
        float monthlyPayout = scanner.nextFloat();

        System.out.print("what is your interest rate: ");
        float interestRate = scanner.nextFloat() ;
        float monthlyInterestRate = interestRate / 12 / 100;

        System.out.print("year to pay out: ");
        int lengthInYear = scanner.nextInt();

        double requiredInvestment =  monthlyPayout * (1 - (1 / Math.pow(1 + monthlyInterestRate, lengthInYear * 12))) /monthlyInterestRate;

        System.out.printf("To fund an annuity that pays $%.2f monthly for %d years and earns an expected %.2f%% interest, you would need to invest $%.2f today.", monthlyPayout, lengthInYear, interestRate, requiredInvestment);
    }

}
