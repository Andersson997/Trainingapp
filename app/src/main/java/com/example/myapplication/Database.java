package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "ExcerciseSchedule.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER = "USER";
    private static final String COLUMN_ID = "UID";
    private static final String COLUMN_FNAME = "Förnamn";
    private static final String COLUMN_LNAME = "Efternamn";
    private static final String COLUMN_PWORD = "Lösenord";
    private static final String COLUMN_UNAME = "Användarnamn";
    private static final String TABLE_USERCONNECTION = "Användaresschema";
    private static final String COLUMN_UID = "UID";
    private static final String COLUMN_EID = "EID";
    private static final String TABLE_SCHEDULE = "Träningsscheman";
    private static final String COLUMN_EXID = "EID";
    private static final String COLUMN_NAME ="Typ";
    private static final String TABLE_ECONNECTION = "schemakoppling";
    private static final String COLUMN_EFID ="EID";
    private static final String COLUMN_PFID = "PID";
    private static final String TABLE_EXCERCISE = "Övning";
    private static final String COLUMN_PID = "PID";
    private static final String COLUMN_ENAME = "Namn";
    private static final String COLUMN_DESCRIPTION ="Beskrivning";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USER + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_UNAME + " TEXT, " +
                COLUMN_PWORD + " TEXT, " +
                COLUMN_FNAME + " TEXT, " +
                COLUMN_LNAME + " TEXT); ";
        String query1 = "CREATE TABLE " + TABLE_EXCERCISE + " (" + COLUMN_PID + " INTEGER PRIMARY KEY, " + COLUMN_ENAME + " TEXT, " +
        COLUMN_DESCRIPTION + " TEXT) ";

        String query2 = "CREATE TABLE " + TABLE_SCHEDULE + " (" + COLUMN_EXID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT) ";

        String query3 = "CREATE TABLE " + TABLE_USERCONNECTION + " (" + COLUMN_UID + " INTEGER, " + COLUMN_EID + " INTEGER, " + " PRIMARY KEY " +
                "(" + COLUMN_UID + "," + COLUMN_EID + ")," + " FOREIGN KEY (" + COLUMN_UID +") REFERENCES " + TABLE_USER +" (" + COLUMN_ID + "), FOREIGN KEY (" +
                COLUMN_EID + ") REFERENCES " + TABLE_SCHEDULE + " (" + COLUMN_EXID + "))";

        String query4 = "CREATE TABLE " + TABLE_ECONNECTION + " (" + COLUMN_EFID + " INTEGER, " + COLUMN_PFID + " INTEGER, PRIMARY KEY (" + COLUMN_EFID +"," +COLUMN_PFID + "), FOREIGN KEY (" + COLUMN_EFID + ") REFERENCES " + TABLE_SCHEDULE + " (" + COLUMN_EXID + "), FOREIGN KEY " + "(" + COLUMN_PFID + ") REFERENCES " + TABLE_EXCERCISE + " (" +
        COLUMN_PID + "))";

        db.execSQL(query);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_UNAME, user.getAnamn());
        cv.put(COLUMN_PWORD, user.getLord());
        cv.put(COLUMN_FNAME, user.getFnamn());
        cv.put(COLUMN_LNAME, user.getEnamn());

        long result = db.insert(TABLE_USER, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Misslyckades", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Registrering Lyckades", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public List<User> listOfAllUser()
    {
        String query = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        List<User> userList = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String anamn = cursor.getString(1);
                String lord = cursor.getString(2);
                String fnamn = cursor.getString(3);
                String enamn = cursor.getString(4);
                User user = new User(id,anamn,lord,fnamn,enamn);
                userList.add(user);

            }while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return userList;
    }

    public List<Schedule> listOfAllSchedule()
    {
        String query = "SELECT * FROM " + TABLE_SCHEDULE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        List<Schedule> scheduleList = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String namn = cursor.getString(1);
                Schedule schedule = new Schedule(id,namn);
                scheduleList.add(schedule);

            }while(cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return scheduleList;
    }
    public List<Excercise> listOfAllExcercise()
    {
        String query = "SELECT * FROM " + TABLE_EXCERCISE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        List<Excercise> excerciseList = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String namn = cursor.getString(1);
                String description = cursor.getString(2);
                Excercise excercise = new Excercise(id, namn, description);
                excerciseList.add(excercise);

            }while(cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return excerciseList;
    }
    public void addHardcodedSchedule(ArrayList<Schedule> scheduleList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        long count = DatabaseUtils.queryNumEntries(db, TABLE_SCHEDULE);

        if (count == 0)
        {
            for (Schedule e:scheduleList){
                cv.put(COLUMN_NAME, e.getNamn());
                db.insert(TABLE_SCHEDULE, null, cv);
                for (Excercise exercise: e.getExcerciseArrayList())
                {
                    ContentValues cv1 = new ContentValues();
                    cv1.put(COLUMN_EFID, e.getId());
                    cv1.put(COLUMN_PFID, exercise.getId());
                    db.insert(TABLE_ECONNECTION, null, cv1);
                }
            }

        }
        db.close();
    }
    public void addHardCodedExcercise(ArrayList<Excercise> excerciseList)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        long count = DatabaseUtils.queryNumEntries(db, TABLE_EXCERCISE);

        if (count == 0)
        {
            for (Excercise e: excerciseList)
            {
                cv.put(COLUMN_ENAME, e.getName());
                cv.put(COLUMN_DESCRIPTION, e.getDescription());
                db.insert(TABLE_EXCERCISE, null, cv);
            }
        }
        db.close();
    }
    public List<Excercise> getExcercisesForSchedule (int id)
    {
        String query = "SELECT " + COLUMN_ENAME +","+ COLUMN_DESCRIPTION + " FROM " + TABLE_ECONNECTION + "," + TABLE_EXCERCISE + " WHERE "
        + COLUMN_EFID + " = ? AND " + TABLE_EXCERCISE + "." + COLUMN_PFID + " = " + TABLE_ECONNECTION + "." + COLUMN_PFID;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Excercise> excerciseList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(id)});

        if (cursor.moveToFirst())
        {
            do {
                String namn = cursor.getString(0);
                String description = cursor.getString(1);
                Excercise excercise = new Excercise(namn, description);
                excerciseList.add(excercise);
                }while(cursor.moveToNext());
        }
         cursor.close();
         db.close();
         return excerciseList;
    }
    public List<Schedule>getScheduleForUser (int id)
    {
        String query = "SELECT " + COLUMN_NAME + " FROM " + TABLE_USERCONNECTION + "," + TABLE_SCHEDULE + " WHERE " + COLUMN_UID + " = ? AND " +
                TABLE_SCHEDULE + "." + COLUMN_EXID + " = " + TABLE_USERCONNECTION + "." + COLUMN_EID;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Schedule> scheduleList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(id)});

        if (cursor.moveToFirst())
        {
            do {
                String namn = cursor.getString(0);
                Schedule schedule = new Schedule(namn);
                scheduleList.add(schedule);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scheduleList;
    }
    public void addScheduleForUser(ArrayList <Schedule> scheduleList, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (Schedule e: scheduleList)
        {
            cv.put(COLUMN_EID, e.getId());
            cv.put(COLUMN_UID, id);
            db.insert(TABLE_USERCONNECTION, null,cv);
        }
        db.close();
    }
}
