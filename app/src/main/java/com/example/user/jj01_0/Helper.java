package com.example.user.jj01_0;

import android.content.Context;
import android.content.SharedPreferences;


public class Helper {

         public static void saveAll(Context context) {
            // save the current state of game history
            SharedPreferences savedValues =
                    context.getSharedPreferences("savedValues", Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = savedValues.edit();

            prefEditor.putInt("TOTAL_ONE", MainActivity.P1TotalScore);
            prefEditor.putInt("TOTAL_TWO", MainActivity.P2TotalScore);
            prefEditor.putInt("WINS_ONE", MainActivity.wins1);
            prefEditor.putInt("WINS_TWO", MainActivity.wins2);
            prefEditor.putInt("TIES", MainActivity.ties);
            prefEditor.putString("NAME_ONE", MainActivity.P1Name);
            prefEditor.putString("NAME_TWO", MainActivity.P2Name);
            prefEditor.putBoolean("LIVE", MainActivity.liveFlag);
            prefEditor.putInt("AFTER_DOUBLE", MainActivity.after2XFlag);
            prefEditor.putInt("P1_VALUE", MainActivity.p1Value);
            prefEditor.putInt("P2_VALUE", MainActivity.p2Value);
            prefEditor.apply();
        }

    // retrieve full history
    public static void getAll(Context context) {
        SharedPreferences savedValues =
                context.getSharedPreferences("savedValues", Context.MODE_PRIVATE);
        MainActivity.P1TotalScore = savedValues.getInt("TOTAL_ONE", 0);
        MainActivity.P2TotalScore= savedValues.getInt("TOTAL_TWO", 0);
        MainActivity.wins1 = savedValues.getInt("WINS_ONE", 0);
        MainActivity.wins2 = savedValues.getInt("WINS_TWO", 0);
        MainActivity.ties = savedValues.getInt("TIES", 0);
        MainActivity.P1Name = savedValues.getString("NAME_ONE", "FOO");
        MainActivity.P2Name = savedValues.getString("NAME_TWO", "BAR");
        MainActivity.liveFlag = savedValues.getBoolean("LIVE", false);
        MainActivity.after2XFlag = savedValues.getInt("AFTER_DOUBLE", 0);
        MainActivity.p2Value =(savedValues.getInt("P1_VALUE", 0));
        MainActivity.p2Value = (savedValues.getInt("P2_VALUE", 0));
    }

    // retrieve partial history
    public static void getSome(Context context) {
        SharedPreferences savedValues =
                context.getSharedPreferences("savedValues", Context.MODE_PRIVATE);
          MainActivity.P1TotalScore = savedValues.getInt("TOTAL_ONE", 0);
          MainActivity.P2TotalScore= savedValues.getInt("TOTAL_TWO", 0);
//        MainActivity.wins1 = savedValues.getInt("WINS_ONE", 0);
//        MainActivity.wins2 = savedValues.getInt("WINS_TWO", 0);
//        MainActivity.ties = savedValues.getInt("TIES", 0);
        MainActivity.P1Name = savedValues.getString("NAME_ONE", "FOO");
        MainActivity.P2Name = savedValues.getString("NAME_TWO", "BAR");
        MainActivity.liveFlag = savedValues.getBoolean("LIVE", false);
//        MainActivity.after2XFlag = savedValues.getInt("AFTER_DOUBLE", 0);
//        MainActivity.p2Value =(savedValues.getInt("P1_VALUE", 0));
//        MainActivity.p2Value = (savedValues.getInt("P2_VALUE", 0));
    }}

