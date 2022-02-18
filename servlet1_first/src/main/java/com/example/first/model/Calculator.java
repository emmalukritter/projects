package com.example.first.model;

public class Calculator {
    private int firstnum;
    private int secondnum;
    private int sum;

    public int getFirstnum() {
        return firstnum;
    }

    public void setFirstnum(int firstnum) {
        this.firstnum = firstnum;
    }

    public int getSecondnum() {
        return secondnum;
    }

    public void setSecondnum(int secondnum) {
        this.secondnum = secondnum;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "firstnum=" + firstnum +
                ", secondnum=" + secondnum +
                ", sum=" + sum +
                '}';
    }
}
