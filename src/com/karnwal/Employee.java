package com.karnwal;


import java.util.ArrayList;

public class Employee {

    private final String empName;
    private final double empHours;
    private final double empPayRate;
    private final double empIncomeTaxRate;
    protected static Double totalGrossIncome = 0.0;
    protected static Double totalNetIncome = 0.0;

    public Employee(String empName, double empHours, double empPayRate, double empIncomeTaxRate) {
        this.empName = empName;
        this.empHours = empHours;
        this.empPayRate = empPayRate;
        this.empIncomeTaxRate = empIncomeTaxRate;
    }

    public void getInfo() {
        ArrayList<Double> incomeList = new ArrayList<>();
        incomeList.add(0, 0.0);
        incomeList.add(1, 0.0);
        final double grossIncome;
        final double netIncome;
        final double taxAmount;
        if (getEmpHours() <= 40) grossIncome = getEmpHours() * getEmpPayRate();
        else {
            final double overtimePay = (getEmpPayRate() * (getEmpHours()-40))*1.5;
            final double overtimeHours = getEmpHours()-40;
            grossIncome = (getEmpHours()-overtimeHours) * getEmpPayRate() +  overtimePay;
        }
        taxAmount = grossIncome * getEmpIncomeTaxRate();
        netIncome = grossIncome - taxAmount;
        Employee.totalGrossIncome += incomeList.get(0) + grossIncome;
        Employee.totalNetIncome += incomeList.get(1) + netIncome;
        incomeList.set(0, Employee.totalGrossIncome);
        incomeList.set(1, Employee.totalNetIncome);
        System.out.printf("\n%s's Gross Income is $%.2f\n", getEmpName(), grossIncome);
        System.out.printf("Taxes Withheld @ %.1f%% is $%.2f\n", getEmpIncomeTaxRate()*100, taxAmount);
        System.out.printf("%s's Net Income is $%.2f\n\n", getEmpName(), netIncome);
    }

    public String getEmpName() {
        return empName;
    }

    public double getEmpHours() {
        return empHours;
    }

    public double getEmpPayRate() {
        return empPayRate;
    }

    public double getEmpIncomeTaxRate() {
        return empIncomeTaxRate;
    }

}
