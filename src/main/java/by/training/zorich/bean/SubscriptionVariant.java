package by.training.zorich.bean;

import java.io.Serializable;

public class SubscriptionVariant implements Serializable {
    private static final long serialVersionUID = 6468690392646791170L;

    private int id;
    private String index;
    private Periodical periodical;
    private SubscriptionType subscriptionType;
    private double costForIssue;
    private Double actualCost;

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

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public double getCostForIssue() {
        return costForIssue;
    }

    public void setCostForIssue(double costForIssue) {
        this.costForIssue = costForIssue;
    }

    public double getActualCost() {
        return actualCost;
    }

    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }

    public void calculateActualCost() {
        actualCost = periodical.getPeriodicityInMonth() * subscriptionType.getMonthAmount() * costForIssue;
        actualCost = Math.rint(actualCost * 100.0)/ 100.0;
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
        if (Double.compare(that.costForIssue, costForIssue) != 0) {
            return false;
        }
        if (index != null ? !index.equals(that.index) : that.index != null) {
            return false;
        }
        if (periodical != null ? !periodical.equals(that.periodical) : that.periodical != null) {
            return false;
        }
        return subscriptionType != null ? subscriptionType.equals(that.subscriptionType) :
                that.subscriptionType == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (periodical != null ? periodical.hashCode() : 0);
        result = 31 * result + (subscriptionType != null ? subscriptionType.hashCode() : 0);
        temp = Double.doubleToLongBits(costForIssue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "id=" + id +
               ", index='" + index + '\'' +
               ", periodical=" + periodical +
               ", subscriptionType=" + subscriptionType +
               ", costForIssue=" + costForIssue +
               '}';
    }
}
