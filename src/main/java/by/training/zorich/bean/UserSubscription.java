package by.training.zorich.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class UserSubscription implements Serializable {
    private static final long serialVersionUID = -3224330147747844232L;

    private int id;
    private UserAddress userAddress;
    private SubsciptionVariant subsciptionVariant;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private Payment payment;

    public UserSubscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public SubsciptionVariant getSubsciptionVariant() {
        return subsciptionVariant;
    }

    public void setSubsciptionVariant(SubsciptionVariant subsciptionVariant) {
        this.subsciptionVariant = subsciptionVariant;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
