package com.example.user.jj01_0;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

public class activityDaily extends AppCompatActivity {

    private EditText bet1;
    private EditText bet2;

    private int limit = 0;
    private int modeFlag = 0; // false = LIMITED
    private int p1Bet = 0;
    private int p2Bet = 0;
    private int p1GameScore = 0;
    private int p2GameScore = 0;

    private boolean dj = false;
    boolean display = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get current scores, etc. for bet validation
            setContentView(R.layout.activity_daily_live); // paint the screen - 1 entry
            bet1 = findViewById(R.id.Bet1); // get references to live bet entry
        }

        bet1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                        bet1.setText("");
                        bet1.setText(p1Bet);
                    return true;
                }
            return false;
            }
        }

        }

        bet1.setOnFocusChangeListener( new View.OnFocusChangeListener() {

            public void onFocusChange( View v, boolean hasFocus ) {
                if( hasFocus ) {
                    bet1.setText( "", TextView.BufferType.EDITABLE );
                }
            } });
    }

    // process enter key press
    public void buttonDoneOnClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDone:
                // set up to go back to main activity
                Intent mainIntent = new Intent(this, MainActivity.class);
                // make bets available to main activity
                mainIntent.putExtra("LIVE_BET", p1Bet);
                startActivity(mainIntent); // go
                break;
        }
    }

}
