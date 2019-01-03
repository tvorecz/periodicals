package by.training.zorich.bean;

import java.io.Serializable;

public class SubscriptionVariant implements Serializable {
    private static final long serialVersionUID = 6468690392646791170L;

    private int id;
    private String index;
    private Periodical periodical;
    private int idSubscriptionType;
    private String typeSubscription;
    private int monthAmount;

    public SubscriptionVariant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public String getTypeSubscription() {
        return typeSubscription;
    }

    public void setTypeSubscription(String typeSubscription) {
        this.typeSubscription = typeSubscription;
    }

    public int getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(int monthAmount) {
        this.monthAmount = monthAmount;
    }

    public int getIdSubscriptionType() {
        return idSubscriptionType;
    }

    public void setIdSubscriptionType(int idSubscriptionType) {
        this.idSubscriptionType = idSubscriptionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SubscriptionVariant that = (SubscriptionVariant) o;

        if (id != that.id) {
            return false;
        }
        if (monthAmount != that.monthAmount) {
            return false;
        }
        if (index != null ? !index.equals(that.index) : that.index != null) {
            return false;
        }
        if (periodical != null ? !periodical.equals(that.periodical) : that.periodical != null) {
            return false;
        }
        return typeSubscription != null ? typeSubscription.equals(that.typeSubscription) :
                that.typeSubscription == null;
    }

    @Override
    public int hashCode() {
        int result;
        result = id;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (periodical != null ? periodical.hashCode() : 0);
        result = 31 * result + (typeSubscription != null ? typeSubscription.hashCode() : 0);
        result = 31 * result + monthAmount;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "id=" + id +
               ", index='" + index + '\'' +
               ", periodical=" + periodical +
               ", typeSubscription='" + typeSubscription + '\'' +
               ", monthAmount=" + monthAmount +
               '}';
    }
}
