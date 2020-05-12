package com.example.opetauth;

public class Sales {

    private String title;
    private String description;
    private Double value;

    public Sales(String title, String description, Double value) {
        this.title = title;
        this.description = description;
        this.value = value;
    }

    public Sales() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
