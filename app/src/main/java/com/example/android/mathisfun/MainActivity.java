package com.example.android.mathisfun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int min;
    int max;
    int firstNumber;
    int second;
    int operator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayFirstNum() {
        TextView firstNumTextView= (TextView) findViewById(R.id.first_number);

    }
    public void displayOperator() {

    }
    public void displaySecondNun () {

    }
    public void displayExercise() {
        displayFirstNum();
        displayOperator();
        displaySecondNun();
    }
    public void getValidOperators() {

    }
}
