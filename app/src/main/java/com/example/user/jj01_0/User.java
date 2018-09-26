package com.example.user.jj01_0;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "pair")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name_one")
    private String name1;

    @ColumnInfo(name = "name_two")
    private String name2;

    @ColumnInfo(name = "total_score_1")
    private int totalScoreOne;

    @ColumnInfo(name = "total_score_2")
    private int totalScoreTwo;

    @ColumnInfo(name = "wins_1")
    private int winsOne;

    @ColumnInfo(name = "wins_2")
    private int winsTwo;

    @ColumnInfo(name = "ties")
    private int ties;

    @ColumnInfo(name = "limit")
    private int limit;



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNameOne() { return name1;}

    public void setNameOne(String name1) {
        this.name1 = name1;
    }

    public String getNameTwo() { return name2;}

    public void setNameTwo(String name2) {
        this.name2 = name2;
    }

    public int getTotalScoreOne() { return totalScoreOne;}

    public void setTotalScoreOne(int totalScoreOne) {
        this.totalScoreOne = totalScoreOne;
    }

    public int totalScoreTwo() { return totalScoreTwo;}

    public void setTotalScoreTwo(int totalScoreTwo) {
        this.totalScoreTwo = totalScoreTwo;
    }

    public int getWinsOne() { return winsOne;}

    public void setWinsOne(int winsOne) {
        this.winsOne = winsOne;
    }

    public int getWinsTwo() { return winsTwo;}

    public void setWinsTwo(int winsTwo) {
        this.winsTwo = winsTwo;
    }

    public int getTies() { return ties;}

    public void setTies(int ties) {
        this.ties = ties;
    }

    public int getLimit() { return limit;}

    public void setLimit(int limit) {
        this.limit = limit;
    }


}
