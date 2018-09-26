package com.example.user.jj01_0;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activityFinal extends MainActivity {

    // define and get global object references

    private Button P1RightButtonF;
    private Button P1WrongButtonF;
    private Button P2RightButtonF;
    private Button P2WrongButtonF;
    private Button recordButton;

    private TextView p1GameScoreText;
    private TextView p2GameScoreText;
    private TextView p1TotalScoreDisplay;
    private TextView p2TotalScoreDisplay;
    private TextView p1WinsText;
    private TextView p2WinsText;
    private TextView tiesText;

    private EditText p1BetText;
    private EditText p2BetText;

    // define local variables
    private boolean recorded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

            // local object references
        TextView p1TotalScoreLabelText;
        TextView p2TotalScoreLabelText;
        TextView p1GameScoreLabelText;
        TextView p2GameScoreLabelText;

        // assign objects to reference variables
        P1RightButtonF = findViewById(R.id.buttonP1RightF);
        P1WrongButtonF = findViewById(R.id.buttonP1WrongF);
        P2RightButtonF = findViewById(R.id.buttonP2RightF);
        P2WrongButtonF = findViewById(R.id.buttonP2WrongF);
        recordButton = findViewById(R.id.buttonRecord);
        p1GameScoreText = findViewById(R.id.P1GameScoreText);
        p2GameScoreText = findViewById(R.id.P2GameScoreText);
        p1TotalScoreDisplay = findViewById(R.id.P1TotalScoreText);
        p2TotalScoreDisplay = findViewById(R.id.P2TotalScoreText);
        p1BetText = findViewById(R.id.P1BetText);
        p2BetText = findViewById(R.id.P2BetText);
        p1WinsText = findViewById(R.id.P1WinsText);
        p2WinsText = findViewById(R.id.P2WinsText);
        tiesText = findViewById(R.id.TiesText);
        p1TotalScoreLabelText = findViewById(R.id.P1TotalScoreLabelText);
        p2TotalScoreLabelText = findViewById(R.id.P2TotalScoreLabelText);
        p1GameScoreLabelText = findViewById(R.id.P1GameScoreLabelText);
        p2GameScoreLabelText = findViewById(R.id.P2GameScoreLabelText);

        // assign historical and game-so-far values

        p1GameScoreText.setText(String.valueOf(MainActivity.p1Value));
        p2GameScoreText.setText(String.valueOf(MainActivity.p2Value));

        p1TotalScoreDisplay.setText(String.valueOf(MainActivity.P1TotalScore));
        p2TotalScoreDisplay.setText(String.valueOf(MainActivity.P2TotalScore));

        p1WinsText.setText(String.valueOf(MainActivity.wins1));
        p2WinsText.setText(String.valueOf(MainActivity.wins2));

        tiesText.setText(String.valueOf(MainActivity.ties));

        p1TotalScoreDisplay.requestFocus();

        // assign names
 //       P1RightButtonF.setText(P1Name.toUpperCase() + getString(R.string.right));
        String text = getString(R.string.right, P1Name.toUpperCase());
        P1RightButtonF.setText(text);

        text = getString(R.string.wrong, P1Name.toUpperCase());
        P1WrongButtonF.setText(text);

        text = getString(R.string.right, P2Name.toUpperCase());
        P2RightButtonF.setText(text);

        text = getString(R.string.wrong, P2Name.toUpperCase());
        P2WrongButtonF.setText(text);

        text = getString(R.string.total, P1Name.toUpperCase());
        p1TotalScoreLabelText.setText(text);

        text = getString(R.string.total, P2Name.toUpperCase());
        p2TotalScoreLabelText.setText(text);

        text = getString(R.string.game, P1Name.toUpperCase());
        p1GameScoreLabelText.setText(text);

        text = getString(R.string.game, P2Name.toUpperCase());
        p2GameScoreLabelText.setText(text);



        // calculate suggested bets


        if ((MainActivity.p1Value >= 0)
                && (MainActivity.p2Value >= 0)) { // don't propose bets with negative score(s)
            // p1 >= 2x p2     bet enough so other player can't win
            if (MainActivity.p1Value >= (MainActivity.p2Value * 2)) {
                p1BetText.setText("");
                p1BetText.setText(String.valueOf((MainActivity.p1Value -1) - (MainActivity.p2Value * 2)));
                p2BetText.setText("");
                p2BetText.setText(String.valueOf(MainActivity.p2Value));
            }

            // p2 >= 2x p1
            else if (MainActivity.p2Value >= (MainActivity.p1Value * 2)) {
                p2BetText.setText("");
                p2BetText.setText(String.valueOf((MainActivity.p2Value - 1) - (MainActivity.p1Value * 2)));
                p1BetText.setText("");
                p1BetText.setText(String.valueOf(MainActivity.p1Value));
            }

            // p1 = p2      both bet it all
            else if (MainActivity.p1Value == MainActivity.p2Value) {
                p1BetText.setText("");
                p1BetText.setText(String.valueOf(MainActivity.p1Value));
                p2BetText.setText("");
                p2BetText.setText(String.valueOf(MainActivity.p2Value));
            }

            // p1 < p2      p1 bets it all, p2 enough to win
            else if (MainActivity.p1Value < MainActivity.p2Value) {
                p1BetText.setText("");
                p1BetText.setText(String.valueOf(MainActivity.p1Value));
                p2BetText.setText("");
                p2BetText.setText(String.valueOf(Math.abs(MainActivity.p2Value - (MainActivity.p1Value * 2)) + 1));
            }


            // p2 < p1      p2 bets it all, p1 enough to win
            else if (MainActivity.p2Value < MainActivity.p1Value) {
                p2BetText.setText("");
                p2BetText.setText(String.valueOf(MainActivity.p2Value));
                p1BetText.setText("");
                p1BetText.setText(String.valueOf(Math.abs(MainActivity.p1Value - (MainActivity.p2Value * 2)) + 1));
            }
        }
    }

 /*   public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            P1Bet = Integer.parseInt(p1BetText.getText().toString());
            if (P1Bet >= 0) { // prohibit betting with negative scores / bets
                if (P1Bet > MainActivity.p1Value) {
                    p1BetText.setText("");
                    p1BetText.setError("Too large - Re-enter BET");
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(p1BetText.getApplicationWindowToken(), 0);
                }
                P1Bet = 0;
            }
            return true;
        }
        return false;
    }

*/
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void buttonFinalOnClick(View v) {
        int P1Bet;
        int P2Bet;

        switch (v.getId()) {
            case R.id.buttonP1RightF:
                P1WrongButtonF.setBackgroundResource(R.drawable.roundedred);
                P1RightButtonF.setBackgroundResource(R.drawable.roundedorange);
                P1Bet = Integer.parseInt(p1BetText.getText().toString());
                MainActivity.p1Value += P1Bet;
                p1GameScoreText.setText(String.valueOf(MainActivity.p1Value));
                break;
            case R.id.buttonP1WrongF:
                P1WrongButtonF.setBackgroundResource(R.drawable.roundedorange);
                P1RightButtonF.setBackgroundResource(R.drawable.roundedgreen);
                P1Bet = Integer.parseInt(p1BetText.getText().toString());
                MainActivity.p1Value -= P1Bet;
                p1GameScoreText.setText(String.valueOf(MainActivity.p1Value));
                break;
            case R.id.buttonP2RightF:
                P2WrongButtonF.setBackgroundResource(R.drawable.roundedred);
                P2RightButtonF.setBackgroundResource(R.drawable.roundedorange);
                P2Bet = Integer.parseInt(p2BetText.getText().toString());
                MainActivity.p2Value += P2Bet;
                p2GameScoreText.setText(String.valueOf(MainActivity.p2Value));
                break;
            case R.id.buttonP2WrongF:
                P2WrongButtonF.setBackgroundResource(R.drawable.roundedorange);
                P2RightButtonF.setBackgroundResource(R.drawable.roundedgreen);
                P2Bet = Integer.parseInt(p2BetText.getText().toString());
                MainActivity.p2Value -= P2Bet;
                p2GameScoreText.setText(String.valueOf(MainActivity.p2Value));
                break;
            case R.id.buttonRecord:
                if (!recorded) {
                    recorded = true; // only once per game
                    recordButton.setBackgroundResource(R.drawable.roundedorange); // orange
                    // update scores
                    if (MainActivity.p1Value > MainActivity.p2Value) {
                        MainActivity.wins1++;
                        p1WinsText.setText(String.valueOf(MainActivity.wins1));
                    } else if (MainActivity.p2Value > MainActivity.p1Value) {
                        MainActivity.wins2++;
                        p2WinsText.setText(String.valueOf(MainActivity.wins2));
                    } else {
                        MainActivity.ties++;
                        tiesText.setText(String.valueOf(MainActivity.ties));
                    }
                    // either P1, P2 or both have negative gameScore(s)
                    if ((MainActivity.p1Value < 0) && (MainActivity.p2Value < 0)) {  // are both negative
                        p2BetText.setText("0");
                        p2BetText.setBackgroundColor(0x00000000); // black to mask characters
                        p2BetText.setKeyListener(null);  //disallow input
                        p1BetText.setText("0");
                        p1BetText.setVisibility(View.GONE);
                        p2BetText.setBackgroundColor(0x00000000); // black to mask characters
                        p2BetText.setKeyListener(null);  //disallow input
                        p2BetText.setVisibility(View.GONE);
                        new AlertDialog.Builder(this).setTitle("No Winner").setMessage("Both negative!").setNeutralButton("Close", null).show();
                    } else {  // not both
                        if (MainActivity.p1Value < 0) {
                            p1BetText.setText("0");
                            p1BetText.setBackgroundColor(0x00000000); // black to mask characters
                            p1BetText.setKeyListener(null);  //disallow input
                            p1BetText.setVisibility(View.GONE);
                            new AlertDialog.Builder(this).setTitle(P2Name + " Wins").setMessage(P1Name + " negative!").setNeutralButton("Close", null).show();
                        }
                        if (MainActivity.p2Value < 0) {
                            p1BetText.setText("0");
                            p2BetText.setBackgroundColor(0x00000000); // black to mask characters
                            p2BetText.setKeyListener(null);  //disallow input
                            new AlertDialog.Builder(this).setTitle(P1Name + " Wins").setMessage(P2Name + " negative!").setNeutralButton("Close", null).show();
                        }
                    }

                    // update finals
                    p1TotalScoreDisplay.setText(String.valueOf(MainActivity.p1Value + MainActivity.P1TotalScore));
                    MainActivity.P1TotalScore += MainActivity.p1Value;
                    MainActivity.P2TotalScore += MainActivity.p2Value;
                    p2TotalScoreDisplay.setText(String.valueOf(MainActivity.p2Value + MainActivity.P2TotalScore));
                    p1GameScoreText.setText(String.valueOf(MainActivity.p1Value));
                    p2GameScoreText.setText(String.valueOf(MainActivity.p2Value));
                    onPause();
                    //                    Helper.saveAll();
                    break;
                }
        }
    }
}


