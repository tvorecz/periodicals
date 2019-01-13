package by.training.zorich.bean;

import java.io.Serializable;

public class Payment implements Serializable {
    private static final long serialVersionUID = 2028645606983954164L;

    private int id;
    private double amount;
    private boolean payStatus;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Payment payment = (Payment) o;

        if (id != payment.id) {
            return false;
        }
        if (Double.compare(payment.amount, amount) != 0) {
            return false;
        }
        if (payStatus != payment.payStatus) {
            return false;
        }
        return userId == payment.userId;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (payStatus ? 1 : 0);
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "id=" + id +
               ", amount=" + amount +
               ", payStatus=" + payStatus +
               ", userId=" + userId +
               '}';
    }
}
