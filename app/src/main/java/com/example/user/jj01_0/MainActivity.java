package com.example.user.jj01_0;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

//import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
        // declare and define constants
    public static final int ALL  = 3;                   // make DD bet line INVISIBLE

        // declare global variables
    protected static int bet1 =    0;
    protected static int bet2 =    0;
    protected static boolean DJ =  false;
    protected static int wins1;
    protected static int wins2;
    protected static int ties;
    protected static boolean liveFlag = false; // LIMITED is default
    protected static int after2XFlag = 0; // false is default
    protected static int p1Value = 0;
    protected static int p2Value = 0;
    //protected static int teamId = 0;
    protected static int P1TotalScore = 0;
    protected static int P2TotalScore = 0;
    protected static String P1Name = "";
    protected static String P2Name = "";
    protected static boolean firstTimeFlag = true;

    private TextView   P1Score;                // declare widget references
    private TextView   P2Score;
    private Button     P1RightButton;
    private Button     P1WrongButton;
    private Button     P2RightButton;
    private Button     P2WrongButton;
    private Button     dailyButton;
    private Button     doubleButton;
    private Button     B2Button;
    private Button     B4Button;
    private Button     B6Button;
    private Button     B8Button;
    private Button     B10Button;
    private TextView   doubleText;
    private TextView   B1Text;
    private TextView   B2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        P1Score = findViewById(R.id.P1ScoreText); // get references to widgets
        P2Score = findViewById(R.id.P2ScoreText);
        P1RightButton = findViewById(R.id.buttonP1Right);
        P1WrongButton = findViewById(R.id.buttonP1Wrong);
        P2RightButton = findViewById(R.id.buttonP2Right);
        P2WrongButton = findViewById(R.id.buttonP2Wrong);
        dailyButton = findViewById(R.id.buttonDaily);
        doubleButton = findViewById(R.id.buttonDouble);
        doubleText = findViewById(R.id.textViewDouble);
        B1Text = findViewById(R.id.bet1View);
        B2Text = findViewById(R.id.bet2View);
        B2Button = (RadioButton) findViewById(R.id.radioButton200);
        B4Button = (RadioButton) findViewById(R.id.radioButton400);
        B6Button = (RadioButton) findViewById(R.id.radioButton600);
        B8Button = (RadioButton) findViewById(R.id.radioButton800);
        B10Button = (RadioButton) findViewById(R.id.radioButton1000);
        if (!firstTimeFlag) {  // get complete history
            Helper.getAll(this);
        }
        else {  // get history without data from last game
            Helper.getSome(this);
        }
        P1Score.setText(String.valueOf(p1Value));
        P2Score.setText(String.valueOf(p2Value));
        P1RightButton.setText(P1Name);
        P1WrongButton.setText(P1Name);
        P2RightButton.setText(P2Name);
        P2WrongButton.setText(P2Name);
        if (after2XFlag == 1) { // returning from double entry - show bet(s)
            B1Text.setVisibility(TextView.VISIBLE);
            B2Text.setVisibility(TextView.VISIBLE);
            B1Text.setText(String.valueOf(bet1));
            B2Text.setText(String.valueOf(bet2));
            doubleText.setVisibility(TextView.VISIBLE);
            after2XFlag = 0;
        }
        else{ // make sure no 2X info or data is visible
            B1Text.setVisibility(TextView.INVISIBLE);
            B2Text.setVisibility(TextView.INVISIBLE);
            doubleText.setVisibility(TextView.INVISIBLE);
        }
    }

    @Override
    protected void onPause()
    {
        Helper.saveAll(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        Helper.getAll(this);
        super.onResume();
    }


    public void buttonOnClick(View v) {
        int bet;
        switch (v.getId()) {
            case R.id.radioButton200:
                clearColors();         // start new round with nothing selected
                bet = 200;
                if (DJ) { // playing DJ
                    bet *= 2;}
                bet1 = bet2 = bet;
                B2Button.setBackgroundColor (0xffff8800);
                cleanAfterDouble(ALL);
                break;
            case R.id.radioButton400:
                clearColors();clearColors(); // start new round with nothing selected
                bet = 400;
                if (DJ) { // playing DJ
                    bet *= 2;}
                bet1 = bet2 = bet;
                B4Button.setBackgroundColor (0xffff8800);
                cleanAfterDouble(ALL);
                break;
            case R.id.radioButton600:
                clearColors(); // start new round with nothing selected
                bet = 600;
                if (DJ) { // playing DJ
                    bet *= 2;}
                bet1 = bet2 = bet;
                B6Button.setBackgroundColor (0xffff8800);
                cleanAfterDouble(ALL);
                break;
            case R.id.radioButton800:
                clearColors(); // start new round with nothing selected
                bet = 800;
                if (DJ) { // playing DJ
                    bet *= 2;}
                bet1 = bet2 = bet;
                B8Button.setBackgroundColor (0xffff8800);
                cleanAfterDouble(ALL);
                break;
            case R.id.radioButton1000:
                clearColors(); // start new round with nothing selected
                bet = 1000;
                if (DJ) { // playing DJ
                    bet *= 2;}
                bet1 = bet2 = bet;
                B10Button.setBackgroundColor (0xffff8800);
                cleanAfterDouble(ALL);
                break;
            case R.id.buttonP1Right:
                P1WrongButton.setBackgroundResource(R.drawable.roundedred);
                P1RightButton.setBackgroundResource(R.drawable.roundedorange);
                p1Value += bet1;
                cleanAfterDouble(1);
                P1Score.setText(String.valueOf(p1Value));
                break;
            case R.id.buttonP1Wrong:
                P1WrongButton.setBackgroundResource(R.drawable.roundedorange);
                P1RightButton.setBackgroundResource(R.drawable.roundedgreen);
                p1Value -= bet1;
                cleanAfterDouble(1);
                P1Score.setText(String.valueOf(p1Value));
                break;
            case R.id.buttonP2Right:
                P2WrongButton.setBackgroundResource(R.drawable.roundedred);
                P2RightButton.setBackgroundResource(R.drawable.roundedorange);
                p2Value += bet2;
                cleanAfterDouble(2);
                P2Score.setText(String.valueOf(p2Value));
                break;
            case R.id.buttonP2Wrong:
                P2WrongButton.setBackgroundResource(R.drawable.roundedorange);
                P2RightButton.setBackgroundResource(R.drawable.roundedgreen);
                p2Value -= bet2;
                cleanAfterDouble(2);
                P2Score.setText(String.valueOf(p2Value));
                break;
            case R.id.buttonDaily:
                dailyButton.setBackgroundColor(0xff00ff00);
                P1Score.setText(String.valueOf(p1Value));
                P2Score.setText(String.valueOf(p2Value));
                after2XFlag = 1;
                Helper.saveAll(this);
                if (liveFlag) { // LIMITED
                    Intent dailyLimitedIntent = new Intent(this, activityDailyLimited.class);
                    startActivity(dailyLimitedIntent);
                } else { // live
                    Intent dailyLiveIntent = new Intent(this, activityDailyLive.class);
                    startActivity(dailyLiveIntent);
                }
                break;
            case R.id.buttonDouble:
                clearColors();         // start new round with nothing selected
                if (DJ){
                    DJ = false;
                    doubleButton.setBackgroundResource(R.drawable.roundedred); // red
                    doubleButton.setTextColor      (0x00000000); // black
                    B2Button.setText(getString(R.string.two_hundred)); // 1x all button labels
                    B4Button.setText(getString(R.string.four_hundred));
                    B6Button.setText(getString(R.string.six_hundred));
                    B8Button.setText(getString(R.string.eight_hundred));
                    B10Button.setText(getString(R.string.one_thousand));
                }
                else {
                    doubleButton.setBackgroundResource(R.drawable.roundedblue);
                    doubleButton.setTextColor(0xffffffff); // white
                    B2Button.setText(getString(R.string.four_hundred)); // 2x all button labels
                    B4Button.setText(getString(R.string.eight_hundred));
                    B6Button.setText(getString(R.string.twelve_hundred));
                    B8Button.setText(getString(R.string.sixteen_hundred));
                    B10Button.setText(getString(R.string.two_thousand));
                    DJ = true;  // flag for calculating 2x bet
                }
                break;
            case R.id.buttonFinal:
                runFinal();
                break;
        }
    }

    private void cleanAfterDouble(int player){
        switch (player){
            case 1:
            {
                B1Text.setVisibility(TextView.INVISIBLE);
            }
            break;
            case 2:
            {
                B2Text.setVisibility(TextView.INVISIBLE);
            }
            break;
            case 3:
            {
                B1Text.setVisibility(TextView.INVISIBLE);
                B2Text.setVisibility(TextView.INVISIBLE);
                if (after2XFlag == 1){
                    bet1 = 0;
                    bet2 = 0;
                }
                doubleText.setVisibility(TextView.INVISIBLE);
                after2XFlag = 0; // back to normal bets
            }
            break;
        }
        Helper.saveAll(this);
    }

    private void clearColors(){
        B2Button.setBackgroundColor      (0xffcc0000); // red
        B4Button.setBackgroundColor      (0xffcc0000);
        B6Button.setBackgroundColor      (0xffcc0000);
        B8Button.setBackgroundColor      (0xffcc0000);
        B10Button.setBackgroundColor     (0xffcc0000);
        P1RightButton.setBackgroundResource(R.drawable.roundedgreen); // green; rounded corners
        P2RightButton.setBackgroundResource(R.drawable.roundedgreen);
        P1WrongButton.setBackgroundResource(R.drawable.roundedred); // red; rounded corners
        P2WrongButton.setBackgroundResource(R.drawable.roundedred);
    }


    // Called when the user taps the FINAL button
    private void runFinal() {
        Helper.saveAll(this);
        Intent finalIntent = new Intent(this, activityFinal.class);
        startActivity(finalIntent);
        Helper.saveAll(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_reset:
                p2Value = 0; // reset game scores
                p2Value = 0;
                P1Score.setText(String.valueOf(p2Value)); // update score display
                P2Score.setText(String.valueOf(p2Value));
                return true;
            case R.id.navigation_mode_limited:
                liveFlag = false;
                Helper.saveAll(this);
                return true;
            case R.id.navigation_mode_live:
                liveFlag = true;
                Helper.saveAll(this);
                return true;
            case R.id.navigation_names:
                Helper.saveAll(this);
                Intent nameChangeIntent = new Intent(this, activityNameChange.class);
                startActivity(nameChangeIntent);
                Helper.saveAll(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
