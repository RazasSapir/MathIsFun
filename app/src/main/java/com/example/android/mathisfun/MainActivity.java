package com.example.android.mathisfun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int min;
    int max;
    int firstNumber;
    int secondNumber;
    String operator = "";
    int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateExercise();

        /*TODO: Identify the problem and add a checker and next buttons and functions
         */
    }

    public void initiateExercise() {
        int operatorInt;
        getMinimum();
        getMaximum();
        Random rnd = new Random();
        firstNumber = rnd.nextInt((max - min) + 1) + min;
        secondNumber = rnd.nextInt((max - min) + 1) + min;
        while (operator.equals("")) {
            operatorInt = rnd.nextInt(4) + 1;
            if (isValidOperators(operatorInt)) {
                assignOperator(operatorInt);
                if (operator.equals("/") && firstNumber % secondNumber != 0) {
                    secondNumber = betterMatch(firstNumber);
                }
            }
        }
        String firstInString = Integer.toString(firstNumber);
        String secondInString = Integer.toString(secondNumber);
        displayExercise(firstInString, operator, secondInString);
    }

    public void displayFirstNum(String text) {
        TextView firstNumTextView = (TextView) findViewById(R.id.first_number);
        firstNumTextView.setText(text);
    }

    public void displayOperator(String text) {
        TextView operatorTextView = (TextView) findViewById(R.id.operator);
        operatorTextView.setText(text);
    }

    public void displaySecondNun(String text) {
        TextView secondNumTextView = (TextView) findViewById(R.id.second_number);
        secondNumTextView.setText(text);
    }

    public void displayExercise(String first, String operator, String second) {
        displayFirstNum(first);
        displayOperator(operator);
        displaySecondNun(second);
    }

    public boolean isValidOperators(int numberOfOperator) {
        switch (numberOfOperator) {
            case 1:
                CheckBox plusChecker = (CheckBox) findViewById(R.id.plus_checkBox);
                return plusChecker.isChecked();
            case 2:
                CheckBox minusChecker = (CheckBox) findViewById(R.id.minus_checkBox);
                return minusChecker.isChecked();
            case 3:
                CheckBox multiChecker = (CheckBox) findViewById(R.id.multiplication_checkBox);
                return multiChecker.isChecked();
            case 4:
                CheckBox divChecker = (CheckBox) findViewById(R.id.division_checkBox);
                return divChecker.isChecked();
        }
        return false;
    }

    public void assignOperator(int numberOfOperator) {
        switch (numberOfOperator) {
            case 1:
                operator = "+";
                break;
            case 2:
                operator = "-";
                break;
            case 3:
                operator = "*";
                break;
            case 4:
                operator = "/";
                break;
        }
    }

    public void getMinimum() {
        EditText minEditText = (EditText) findViewById(R.id.from_number);
        min = Integer.parseInt(minEditText.getText().toString());
    }

    public void getMaximum() {
        EditText maxEditText = (EditText) findViewById(R.id.from_number);
        max = Integer.parseInt(maxEditText.getText().toString());
    }

    public ArrayList<Integer> getArrayDivisible(int num) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                toReturn.add(i);
            }
        }
        return toReturn;
    }

    public int betterMatch(int number) {
        ArrayList<Integer> divisibleNumbers = getArrayDivisible(number);
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(divisibleNumbers.size() - 1);
        return divisibleNumbers.get(randomNumber);
    }
}
