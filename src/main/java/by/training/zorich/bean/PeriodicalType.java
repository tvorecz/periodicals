/**
 * Bean-class for storing information about periodical type.
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.bean;

import java.io.Serializable;

public class PeriodicalType implements Serializable {
    private static final long serialVersionUID = 1306836853123867372L;

    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PeriodicalType that = (PeriodicalType) o;

        if (id != that.id) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "id=" + id +
               ", name=" + name +
               '}';
    }
}
