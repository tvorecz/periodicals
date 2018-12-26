package by.training.zorich.bean;

import java.io.Serializable;

public class SubsciptionVariant implements Serializable {
    private static final long serialVersionUID = 6468690392646791170L;

    private int id;
    private String index;
    private Periodical periodical;
    private String typeSubscription;
    private double cost;
    private int monthAmount;

    public SubsciptionVariant() {
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(int monthAmount) {
        this.monthAmount = monthAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SubsciptionVariant that = (SubsciptionVariant) o;

        if (id != that.id) {
            return false;
        }
        if (Double.compare(that.cost, cost) != 0) {
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
        long temp;
        result = id;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (periodical != null ? periodical.hashCode() : 0);
        result = 31 * result + (typeSubscription != null ? typeSubscription.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
               ", cost=" + cost +
               ", monthAmount=" + monthAmount +
               '}';
    }
}
