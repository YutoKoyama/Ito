package com.example.ito2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class NumCheckActivity extends AppCompatActivity implements View.OnClickListener{

    //static変数
    TextView textViewNumber;
    Random rnd = new Random();
    Integer randomNumber;

    private CommonVariable commonVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_check);

        Button btnRemember = findViewById(R.id.btn_remember);
        btnRemember.setOnClickListener(this);

        textViewNumber = findViewById(R.id.number);

        commonVariable = (CommonVariable) getApplication();

        randomNumber = rnd.nextInt(100)+1;

        while (commonVariable.numbersList.contains(randomNumber)){
            randomNumber = rnd.nextInt(100)+1;
        }

        commonVariable.numbersList.add(randomNumber);

        textViewNumber.setText(String.valueOf(randomNumber));
    }

    @Override
    public void onClick(View v) {

        if(commonVariable.numPlayer == commonVariable.transitionCount){
            Intent intent_numberCheck_to_chooseThema = new Intent(this, ChooseThemaActivity.class);
            startActivity(intent_numberCheck_to_chooseThema);
        }else {
            Intent intent_numberCheck_to_playerCheck = new Intent(this, PlayerCheckActivity.class);
            startActivity(intent_numberCheck_to_playerCheck);
        }

    }
}
