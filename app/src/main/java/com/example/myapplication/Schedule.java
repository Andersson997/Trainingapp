package com.example.myapplication;

import java.util.ArrayList;

public class Schedule
{
    private int id;
    private String namn;
    private ArrayList<Excercise> excerciseArrayList;

    public Schedule(int id, String namn) {
        this.id = id;
        this.namn = namn;
        excerciseArrayList = new ArrayList<>();
    }
    public Schedule(String namn){
        this.namn = namn;
    }
    public Schedule()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public ArrayList<Excercise> getExcerciseArrayList() {
        return excerciseArrayList;
    }

    public void setExcerciseArrayList(ArrayList<Excercise> excerciseArrayList) {
        this.excerciseArrayList = excerciseArrayList;
    }
}
