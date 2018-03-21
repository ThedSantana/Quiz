package com.example.anuj.quiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random=new Random();

    boolean active=true;
    int totalQues=1;
    int correct=0;
    int quesPos;
    int opPos;

    String ques[]={"The language spoken by the people by Pakistan is ?","The World Largest desert is ?","Country that has the highest in Barley Production ?",
            "The metal whose salts are sensitive to light is ?","The Central Rice Research Station is situated in ?","Mount Everest is located in ?",
            "Which soil is suitable for agriculture ?","Black soils are best suited for the cultivation of ?","The device used for measuring altitudes is ?",
            "The Gate way of India is ?","The first chairman of the Atomic Energy Commission was ?","D.D.T. was invented by ?","Which is considered as the biggest port of India ?",
            "The gas used for making vegetables is ?","The chief ore of Aluminium is ?","Sharavati projects is in ?","Country that was called as Land of Rising Sun ?",
            "Pink city in India is ?","Deficiency of Iron leads to ?","The state which has desert in India is ?"};

    int asked[]={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    int opSeq[][]={{1,2,3,4},{2,3,4,1},{3,4,1,2},{4,1,2,3},{1,4,2,3},{4,2,1,3},{3,1,2,4},{2,3,1,4},
            {1,2,4,3},{1,3,2,4},{1,3,4,2},{1,4,3,2},{2,1,3,4},{2,1,4,3},{2,4,1,3},{2,4,3,1},
            {3,1,4,2},{3,2,1,4},{3,2,4,1},{3,4,2,1},{4,1,3,2},{4,2,3,1},{4,3,1,2},{4,3,2,1}};

    String option[]={"Sindhi","Hindi","Palauan","Nauruan","Sahara","Thar","Kalahari","Sonoran","Russia","China","India","France","Silver","Zinc","Copper","Aluminium",
            "Cuttack","Chennai","Bangalore","Quilon","Nepal","India","Tibet","China","Peaty soil","Red soil","Sand","Black soil","Cotton","Rice","Cereals","Sugarcane",
            "Altimeter","Ammeter","Audiometer","Galvanometer","Mumbai","Chennai","Kolkata","New Delhi","Dr.H.J.Bhabha","Dr.C.V.Raman","Dr.A.P.J.Abdul Kalam","Dr.Vickram Sarabhai",
            "Mosley","Rudeolf","Karl Benz","Dalton","Mumbai","Kolkata","Cochin","Chennai","Hydrogen","Oxygen","Carbon dioxide","Nitrogen","Bauxite","Iron","Cryolite","Haematite",
            "Andhra Pradesh","Orissa","Kerala","Karnataka","Japan","Russia","Korea","Holland","Jaipur","Mysore","Karnataka","Hyderabad","Anaemia","Rickets","Malaria","Dental Cavity",
            "Rajasthan","Punjab","Uttar Pradesh","Madhya Pradesh"};

    Button goButton;
    Button playAgain;
    TextView timeLeftField;
    TextView questionField;
    TextView marksField;
    TextView result;
    LinearLayout linearLayout;
    GridLayout gridLayout;

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftField.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timeLeftField.setText("0s");
                displayResult();
                active = false;
            }
        }.start();
        quesPos = random.nextInt(20);
        asked[quesPos] = 0;
        questionField.setText(ques[quesPos]);
        marksField.setText("0/1");
        setOption();
        linearLayout.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);

    }

    public void setOption(){
        opPos=random.nextInt(24);
        int poss=quesPos*4;
        for(int i:opSeq[opPos]){
            ((TextView)gridLayout.getChildAt(i-1)).setText(option[poss]);
            poss++;
        }
    }

    public void checkAns(View view){
        if(active) {
            TextView opSelected = (TextView) view;
            totalQues++;
            if (opSelected.getText() == option[quesPos * 4]) {
                correct++;
            }
            String st = String.valueOf(correct) + "/" + String.valueOf(totalQues);
            marksField.setText(st);
            while (asked[quesPos] == 0)
                quesPos = random.nextInt(20);
            asked[quesPos] = 0;
            questionField.setText(ques[quesPos]);
            setOption();
        }
    }

    public void displayResult(){
        String res="Your Score : "+marksField.getText();
        result.setText(res);
        result.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.VISIBLE);

    }

    public void reset(View view){
        result.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        totalQues=1;
        correct=0;
        active=true;
        start(goButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.goButton);
        playAgain=findViewById(R.id.playAgain);
        timeLeftField= findViewById(R.id.timeLeftField);
        questionField=findViewById(R.id.questionField);
        marksField=findViewById(R.id.marksField);
        result=findViewById(R.id.result);
        linearLayout=findViewById(R.id.linearLayout);
        gridLayout=findViewById(R.id.gridLayout);
    }
}

