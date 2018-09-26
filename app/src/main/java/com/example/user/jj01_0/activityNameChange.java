package com.example.user.jj01_0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class activityNameChange extends AppCompatActivity{

     private String name1;
     private String name2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_name_change); // paint the screen - 2 entries

            final EditText name1Edit = findViewById(R.id.Name1Entry); // get references to name inputs
            final EditText name2Edit = findViewById(R.id.Name2Entry);

            name1Edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        name1 = name1Edit.getText().toString();                       }
                    InputMethodManager imm = (InputMethodManager) com.example.user.jj01_0.activityNameChange.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(name1Edit.getWindowToken(), 0);
                        return true;
                    }
                    return true;
                }
            });

            name2Edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        name2 = name2Edit.getText().toString();                       }
                    InputMethodManager imm = (InputMethodManager) com.example.user.jj01_0.activityNameChange.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(name2Edit.getWindowToken(), 0);
                        return true;
                    }
                    return true;
                }
            });

            name1Edit.setOnFocusChangeListener( new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus ) {
                    if( hasFocus ) {
                        name1Edit.setText( "", TextView.BufferType.EDITABLE );
                    }
                }
            } );

            name2Edit.setOnFocusChangeListener( new View.OnFocusChangeListener() {

                public void onFocusChange( View v, boolean hasFocus ) {
                    if( hasFocus ) {
                        name2Edit.setText( "", TextView.BufferType.EDITABLE );
                    }
                } });
        }

        // process enter key press
        public void buttonOnClickNames(View v) {
            switch (v.getId()) {
                case R.id.buttonNameEntry:
                    // set up to go back to main activity
                    Intent mainIntent = new Intent(this, MainActivity.class);
                    // make names available to main activity
                    MainActivity.P1Name = name1;
                    MainActivity.P2Name  = name2;
                    Helper.saveAll(this);
                    startActivity(mainIntent); // go
                    break;
            }
        }

    }
