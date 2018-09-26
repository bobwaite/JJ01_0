package com.example.user.jj01_0;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;


public class activityDailyLimited extends AppCompatActivity {

    private int limit = 0;
    private int p1Bet = 0;
    private int p2Bet = 0;
    private int p1GameScore = 0;
    private int p2GameScore = 0;

//    private boolean dj = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_limited); // paint the screen - 2 entries

        final EditText bet1 = findViewById(R.id.Bet1); // get references to bet inputs
        final EditText bet2 = findViewById(R.id.Bet2);

        if (MainActivity.DJ) {//set non score-based limit based on game state
            limit = 2000;
        }
        else{
            limit = 1000;
        }

        // use current scores, etc. for bet validation

        bet1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                   p1Bet = Integer.parseInt(bet1.getText().toString());
                   if ((p1Bet >= limit) && (p1Bet >= p1GameScore)) { // too big
                        bet1over();
                        bet1.setText("0");
                   }

                    InputMethodManager imm = (InputMethodManager)activityDailyLimited.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(bet1.getWindowToken(), 0);
                        return true;
                    }
                    return true;
               }
               return false;
           }
       });

        bet2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               if (actionId == EditorInfo.IME_ACTION_DONE) {
                   p2Bet = Integer.parseInt(bet2.getText().toString());
                   if ((p2Bet >= limit) && (p2Bet >= p2GameScore)){
                       bet2over();
                       bet2.setText("0");
                   }
                   InputMethodManager imm = (InputMethodManager)activityDailyLimited.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                   if (imm != null) {
                       imm.hideSoftInputFromWindow(bet2.getWindowToken(), 0);
                       return true;
                   }
               }
               return false;
           }
       });

       bet1.setOnFocusChangeListener( new View.OnFocusChangeListener() {

           public void onFocusChange( View v, boolean hasFocus ) {
               if( hasFocus ) {
                   bet1.setText( "", TextView.BufferType.EDITABLE );
               }
           }
       } );

        bet2.setOnFocusChangeListener( new View.OnFocusChangeListener() {

            public void onFocusChange( View v, boolean hasFocus ) {
                if( hasFocus ) {
                    bet2.setText( "", TextView.BufferType.EDITABLE );
                }
        } });
    }

    private void bet1over() {
            new AlertDialog.Builder(this).setTitle("TOO MUCH").setMessage("Bet must be <= GameScore or Limit").setNeutralButton("Close", null).show();
        }
        private void bet2over() {
            new AlertDialog.Builder(this).setTitle("TOO MUCH").setMessage("Bet must be <= GameScore or Limit").setNeutralButton("Close", null).show();
        }

        // process enter key press
        public void buttonDoneOnClick(View v) {
            switch (v.getId()) {
                case R.id.buttonDone:
                    // set up to go back to main activity
                    Intent mainIntent = new Intent(this, MainActivity.class);
                    // make bets available to main activity
                    Helper.saveAll(this);
                    MainActivity.bet1 = p1Bet;
                    MainActivity.bet2 = p2Bet;
                    startActivity(mainIntent); // go
                    break;
            }
        }

    }
