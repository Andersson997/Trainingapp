package com.example.myapplication;

public class Excercise
{
    private int id;
    private String name;
    private String description;
    private String day;

    public Excercise(int id ,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }
    public Excercise(String name, String description)
    {
     this.name = name;
     this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
