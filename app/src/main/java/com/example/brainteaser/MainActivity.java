package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout optionslayout;
    ConstraintLayout timerLayout;

    Button start;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgain;

    TextView sumTextView;
    TextView score;
    TextView result;
    TextView timer;

    int rightAnswer;
    int scoreGained = 0;
    int totalQue = 0;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void start(View view){

        start.setVisibility(View.INVISIBLE);
        timerLayout.setVisibility(View.VISIBLE);
        playAgainFunction(findViewById(R.id.button2));

    }

    public void playAgainFunction(View view) {

        optionslayout.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        result.setText("Lets Begin");
        scoreGained = 0;
        totalQue = 0;
        timer.setText("30S");
        score.setText(Integer.toString(scoreGained)+"/"+Integer.toString(totalQue));

        display();

        new CountDownTimer(30100,1000){

        @Override
        public void onTick(long millisUntilFinished) {
            timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
        }

        @Override
        public void onFinish() {
            result.setText("Times UP");
            playAgain.setVisibility(View.VISIBLE);
            optionslayout.setVisibility(View.INVISIBLE);
        }
    }.start();

    }

    public void checkAnswer(View view){

        Log.i("Tag :",view.getTag().toString());
        if (Integer.toString(rightAnswer).equals(view.getTag().toString())){
            //Toast.makeText(this, "You  got it!!", Toast.LENGTH_SHORT).show();
            result.setText("Correct !");
            scoreGained++;
        }
        else {
            //Toast.makeText(this, "Try next time", Toast.LENGTH_SHORT).show();
            result.setText("Wrong :/");
        }
        totalQue++;
        score.setText(Integer.toString(scoreGained)+"/"+Integer.toString(totalQue));
        display();

    }

    public void display(){

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        rightAnswer = random.nextInt(4);

        answers.clear();

        for (int i =0; i<4; i++){
            if( i == rightAnswer ){
                answers.add(a+b);
            }
            else {

                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(41);
                }

                for (int j = 0; j < i;j++){
                    if (wrongAnswer == answers.get(j)){
                        wrongAnswer = random.nextInt(41);
                    }
                }
                answers.add(wrongAnswer);
            }
        }

        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionslayout = (ConstraintLayout) findViewById(R.id.optionsLayout);
        timerLayout = (ConstraintLayout) findViewById(R.id.timerLayout);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        result = (TextView)findViewById(R.id.result);
        score = (TextView) findViewById(R.id.score);
        timer = (TextView) findViewById(R.id.timer);

        start = (Button) findViewById(R.id.start);
        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        playAgain = (Button) findViewById(R.id.playAgain);


        optionslayout.setVisibility(View.INVISIBLE);
        timerLayout.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);

    }
}
