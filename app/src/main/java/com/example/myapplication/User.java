package com.example.myapplication;

import java.util.ArrayList;

public class User
{
    private int id;
    private String anamn;
    private String lord;
    private String fnamn;
    private String enamn;
    private ArrayList <Schedule> schedulelist;
    public User (int id ,String anamn, String lord, String fnamn, String enamn)
    {
        this.id=id;
        this.anamn = anamn;
        this.lord = lord;
        this.fnamn = fnamn;
        this.enamn = enamn;
        schedulelist = new ArrayList<>();
    }
    public User (String anamn, String lord, String fnamn, String enamn)
    {
        this.anamn = anamn;
        this.lord = lord;
        this.fnamn = fnamn;
        this.enamn = enamn;

    }

    public int getId() { return id; }
    public String getAnamn()
    {
        return anamn;
    }
    public String getLord ()
    {
        return lord;
    }
    public String getFnamn ()
    {
        return  fnamn;
    }
    public String getEnamn()
    {
        return enamn;
    }
    public void setAnamn(String anamn)
    {
        this.anamn = anamn;
    }
    public void setLord(String lord)
    {
        this.lord = lord;
    }
    public void setFnamn(String fnamn)
    {
        this.fnamn = fnamn;
    }
    public void setEnamn(String enamn)
    {
        this.enamn = enamn;
    }

    @Override
    public String toString() {
        return "User{" +
                "anamn='" + anamn + '\'' +
                ", lord='" + lord + '\'' +
                ", fnamn='" + fnamn + '\'' +
                ", enamn='" + enamn + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Schedule> getSchedulelist() {
        return schedulelist;
    }

    public void setSchedulelist(ArrayList<Schedule> schedulelist) {
        this.schedulelist = schedulelist;
    }
}
