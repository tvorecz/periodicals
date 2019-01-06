package by.training.zorich.bean;

import java.io.Serializable;

public class SubscriptionType implements Serializable {
    private static final long serialVersionUID = 2116317964265647888L;

    private int id;
    private String name;
    private int monthAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        SubscriptionType that = (SubscriptionType) o;

        if (id != that.id) {
            return false;
        }
        if (monthAmount != that.monthAmount) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + monthAmount;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", monthAmount=" + monthAmount +
               '}';
    }
}
