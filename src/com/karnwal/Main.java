package com.karnwal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static String empName;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int empNum = (int) getValidationNum("int", 1);
        for (int i = 0; i < empNum; i++) {
            System.out.print("Enter Employee’s ID (i.e. AF101): ");
            scanner.nextLine();
            System.out.print("Enter Employee’s Name: ");
            empName = scanner.nextLine();
            double empHours = (double) getValidationNum("double", 2);
            double empPayRate = (double) getValidationNum("double", 3);
            double empIncomeTaxRate = (double) getValidationNum("double", 4);
            Employee employee = new Employee(empName, empHours, empPayRate, empIncomeTaxRate);
            employee.getInfo();
        }
        System.out.printf("Total Gross Income for %o employees is $%.2f\n", empNum, Employee.totalGrossIncome);
        System.out.printf("Total Net Income for %o employees is $%.2f\n", empNum, Employee.totalNetIncome);
        System.out.printf("Average Gross Income for the company is $%.2f\n", Employee.totalGrossIncome/empNum);
        System.out.printf("Average Net Income for the company is $%.2f\n", Employee.totalNetIncome/empNum);
    }

    /* KEY
    numPrint 1 == "How many employees in this week’s payroll: "
    numPrint 2 == "Enter {empName}’s Weekly Hours (Maximum of 60 Hours per Week): "
    numPrint 3 == "Enter {empName}’s Hourly pay rate (Minimum of $10.50 To a Maximum of $100.00 Per Hour): "
    numPrint 4 == "Enter {empName}’s Income tax rate (0 to 0.40): "
     */

    public static Number getValidationNum(String type, int numPrint) {
        String print;
        if (numPrint == 1) {
            print = "How many employees in this week’s payroll: ";
        }
        else if (numPrint == 2) {
            print = String.format("Enter %s’s Weekly Hours (Maximum of 60 Hours per Week): ", empName);
        }
        else if (numPrint == 3) {
            print = String.format("Enter %s’s Hourly pay rate (Minimum of $10.50 To a Maximum of $100.00 Per Hour): ", empName);
        }
        else if (numPrint == 4) {
            print = String.format("Enter %s’s Income tax rate (0 to 0.40): ", empName);
        }
        else {
            print = "Error";
        }

        if (type.equals("int")) {
            int num;
            while (true) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print(print);
                    num = scanner.nextInt();
                    if (num < 0) {
                        throw new Exception();
                    }
                    if (num==0){
                        System.out.println("NO PAYROLL? Great! Goodbye.");
                        System.exit(0);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Sorry, illegal input! Please input again.");
                }
            }
            return num;
        }
        if (type.equals("double")) {
            double num;
            while (true) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print(print);
                    num = scanner.nextDouble();
                    if (num < 0) {
                        System.out.println("Sorry, illegal input! Please input again.");
                        throw new Exception();
                    }
                    if (numPrint == 2 && num > 60) {
                        System.out.println("Weekly hours is ABOVE the 60.0 Maximum");
                        throw new Exception();
                    }
                    if (numPrint == 3 && num < 10.50) {
                        System.out.printf("You entered a Pay Rate of %.2f, LESS than the Minimum of $10.50\n", num);
                        throw new Exception();
                    }
                    else if (numPrint == 3 && num > 100) {
                        System.out.printf("You entered a Pay Rate of %.2f, MORE than the Maximum of $100\n", num);
                        throw new Exception();
                    }
                    if (numPrint == 4 && num > 0.40) {
                        System.out.println("Invalid Tax Rate, must be a minimum of 0 to a maximum of 0.40");
                        throw new Exception();
                    }
                    break;
                }
                catch (InputMismatchException inputMismatchException) {
                    System.out.println("Sorry, illegal input! Please input again.");
                }
                catch (Exception ignored) {

                }
            }
            return num;
        }
        return 0;
    }
}