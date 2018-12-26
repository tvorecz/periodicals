package by.training.zorich.bean;

import java.io.Serializable;

public class Periodical implements Serializable {
    private static final long serialVersionUID = -1236041454971946989L;

    private int id;
    private String type;
    private String theme;
    private String name;
    private int periodicityInMonth;
    private String annotation;
    private String imagePath;

    public Periodical() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriodicityInMonth() {
        return periodicityInMonth;
    }

    public void setPeriodicityInMonth(int periodicityInMonth) {
        this.periodicityInMonth = periodicityInMonth;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Periodical that = (Periodical) o;

        if (id != that.id) {
            return false;
        }
        if (periodicityInMonth != that.periodicityInMonth) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (annotation != null ? !annotation.equals(that.annotation) : that.annotation != null) {
            return false;
        }
        return imagePath != null ? imagePath.equals(that.imagePath) : that.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + periodicityInMonth;
        result = 31 * result + (annotation != null ? annotation.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "id=" + id +
               ", type='" + type + '\'' +
               ", theme='" + theme + '\'' +
               ", name='" + name + '\'' +
               ", periodicityInMonth=" + periodicityInMonth +
               ", annotation='" + annotation + '\'' +
               ", imagePath='" + imagePath + '\'' +
               '}';
    }
}
