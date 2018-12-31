package by.training.zorich.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class UserSubscription implements Serializable {
    private static final long serialVersionUID = -3224330147747844232L;

    private int id;
    private UserAddress userAddress;
    private SubscriptionVariant subscriptionVariant;
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

    public SubscriptionVariant getSubscriptionVariant() {
        return subscriptionVariant;
    }

    public void setSubscriptionVariant(SubscriptionVariant subscriptionVariant) {
        this.subscriptionVariant = subscriptionVariant;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserSubscription that = (UserSubscription) o;

        if (id != that.id) {
            return false;
        }
        if (userAddress != null ? !userAddress.equals(that.userAddress) : that.userAddress != null) {
            return false;
        }
        if (subscriptionVariant != null ? !subscriptionVariant.equals(that.subscriptionVariant) :
                that.subscriptionVariant != null) {
            return false;
        }
        if (dateBegin != null ? !dateBegin.equals(that.dateBegin) : that.dateBegin != null) {
            return false;
        }
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) {
            return false;
        }
        return payment != null ? payment.equals(that.payment) : that.payment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (subscriptionVariant != null ? subscriptionVariant.hashCode() : 0);
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "id=" + id +
               ", userAddress=" + userAddress +
               ", subscriptionVariant=" + subscriptionVariant +
               ", dateBegin=" + dateBegin +
               ", dateEnd=" + dateEnd +
               ", payment=" + payment +
               '}';
    }
}
