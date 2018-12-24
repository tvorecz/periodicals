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
}
