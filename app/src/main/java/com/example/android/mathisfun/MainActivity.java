package com.example.android.mathisfun;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {
    int min = -1;
    int max = -1;
    int firstNumber;
    int secondNumber;
    String operator = "";
    int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCheckboxToTrue();
        setValuesForEditText();
        initiateOnClickExerciseMaker();
        setCheckOnClick();
        initiateExercise();
    }

    public boolean validateAllInstructions() {
        EditText minEditText = (EditText) findViewById(R.id.from_number);
        EditText maxEditText = (EditText) findViewById(R.id.to_number);

        CheckBox plusChecker = (CheckBox) findViewById(R.id.plus_checkBox);
        CheckBox minusChecker = (CheckBox) findViewById(R.id.minus_checkBox);
        CheckBox multiChecker = (CheckBox) findViewById(R.id.multiplication_checkBox);
        CheckBox divChecker = (CheckBox) findViewById(R.id.division_checkBox);
        try {
            int firstNum = Integer.parseInt(minEditText.getText().toString());
            int secondNum = Integer.parseInt(maxEditText.getText().toString());
            if (firstNum > secondNum) {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
        if (plusChecker.isChecked() || minusChecker.isChecked() || multiChecker.isChecked() || divChecker.isChecked()) {
            return true;
        }
        return false;
    }

    public void setCheckOnClick() {
        Button button = (Button) findViewById(R.id.check_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    public void checkAnswer() {
        int userAnswer;
        EditText userAnswerEditText = (EditText) findViewById(R.id.user_answer);
        try {
            userAnswer = Integer.parseInt(userAnswerEditText.getText().toString());
            if (userAnswer == answer) {
                Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
                initiateExercise();
            } else {
                Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.large_answer, Toast.LENGTH_SHORT).show();
        }
    }

    public void setCheckboxToTrue() {
        CheckBox plusChecker = (CheckBox) findViewById(R.id.plus_checkBox);
        CheckBox minusChecker = (CheckBox) findViewById(R.id.minus_checkBox);
        CheckBox multiChecker = (CheckBox) findViewById(R.id.multiplication_checkBox);
        CheckBox divChecker = (CheckBox) findViewById(R.id.division_checkBox);
        plusChecker.setChecked(true);
        minusChecker.setChecked(true);
        multiChecker.setChecked(true);
        divChecker.setChecked(true);
    }

    public void setValuesForEditText() {
        EditText minEditText = (EditText) findViewById(R.id.from_number);
        EditText maxEditText = (EditText) findViewById(R.id.to_number);
        minEditText.setText("1");
        maxEditText.setText("10");
    }

    public void initiateExercise() {
        if (validateAllInstructions()) {
            int operatorInt;
            operator = "";
            getMinimum();
            getMaximum();
            if (min == -1 || max == -1) {
                return;
            }
            Random rnd = new Random();
            firstNumber = rnd.nextInt((max - min) + 1) + min;
            secondNumber = rnd.nextInt((max - min) + 1) + min;
            while (operator.equals("")) {
                operatorInt = rnd.nextInt(4) + 1;
                if (isValidOperators(operatorInt)) {
                    assignOperator(operatorInt);
                    if (operator.equals("/") && firstNumber % secondNumber != 0) {
                        secondNumber = betterMatch(firstNumber);
                    } else if (operator.equals("-") && firstNumber - secondNumber < 0) {
                        secondNumber = rnd.nextInt(firstNumber);
                    }
                    findAnswer(firstNumber, secondNumber, operatorInt);
                }
            }
            String firstInString = Integer.toString(firstNumber);
            String secondInString = Integer.toString(secondNumber);
            displayExercise(firstInString, operator, secondInString);
        } else {
            Toast.makeText(this, R.string.failed_to_show_exercise, Toast.LENGTH_SHORT).show();
        }
    }

    public void findAnswer(int first, int second, int operatorInt) {
        switch (operatorInt) {
            case 1:
                answer = first + second;
                break;
            case 2:
                answer = first - second;
                break;
            case 3:
                answer = first * second;
                break;
            case 4:
                answer = first / second;
                break;
        }
    }

    public void initiateOnClickExerciseMaker() {
        Button button = (Button) findViewById(R.id.new_exercise_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateExercise();
            }
        });
    }

    public void displayFirstNum(String text) {
        TextView firstNumTextView = (TextView) findViewById(R.id.first_number);
        firstNumTextView.setText(text + " ");
    }

    public void displayOperator(String text) {
        TextView operatorTextView = (TextView) findViewById(R.id.operator);
        operatorTextView.setText(text + " ");
    }

    public void displaySecondNun(String text) {
        TextView secondNumTextView = (TextView) findViewById(R.id.second_number);
        secondNumTextView.setText(text + " =");
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
            default:
                return false;
        }
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
        try {
            min = Integer.parseInt(minEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, R.string.large_answer, Toast.LENGTH_SHORT).show();
        }
    }

    public void getMaximum() {
        EditText maxEditText = (EditText) findViewById(R.id.to_number);
        try {
            max = Integer.parseInt(maxEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, R.string.large_answer, Toast.LENGTH_SHORT).show();
        }
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
