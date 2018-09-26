package com.example.user.jj01_0;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class activityDailyLive extends MainActivity{//} {

private EditText bet1;

private int p1Bet = 0;

public void runMain(View view) {
    Intent mainIntent = new Intent(this, MainActivity.class);
    startActivity(mainIntent);
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // get current scores, etc. for bet validation
    Helper.getAll(this);
   // bets per live play
        setContentView(R.layout.activity_daily_live);
        bet1 = findViewById(R.id.Name1Entry);


  /*  bet1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
       @Override
       public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
           if (actionId == EditorInfo.IME_ACTION_DONE) {
               if (actionId == EditorInfo.IME_ACTION_DONE) {
                   bet1.setText(p1Bet);
                   p1Bet = Integer.parseInt(bet1.getText().toString());
                   return true;
               }
           }
           return false;
       }
   });
*/

    bet1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                p1Bet = Integer.parseInt(bet1.getText().toString());
//                   hideKeyboard(activityDailyLive);
                InputMethodManager imm = (InputMethodManager) activityDailyLive.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(bet1.getWindowToken(), 0);
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
    } });

}
    // process enter key press
    public void buttonDoneOnClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDone:
                // go back to main activity
                Intent mainIntent = new Intent(this, MainActivity.class);
                // make bets available to main activity
                Helper.saveAll(this);
                MainActivity.bet1 = p1Bet;
                MainActivity.bet2 = p1Bet;
                startActivity(mainIntent); // go
                break;
        }
    }

}
