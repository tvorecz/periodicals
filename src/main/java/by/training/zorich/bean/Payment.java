package by.training.zorich.bean;

import java.io.Serializable;

public class Payment implements Serializable {
    private static final long serialVersionUID = 2028645606983954164L;

    private int id;
    private double amount;
    private boolean payStatus;

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }
}
