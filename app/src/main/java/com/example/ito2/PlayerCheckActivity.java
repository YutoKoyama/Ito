package com.example.ito2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayerCheckActivity extends AppCompatActivity implements View.OnClickListener{

    //static変数
    TextView playerName;
    String playerCheck;

    private CommonVariable commonVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_check);

        playerName = findViewById(R.id.txt_player_name);
        Button btnCheckNumber = findViewById(R.id.btn_check_number);
        btnCheckNumber.setOnClickListener(this);

        commonVariable = (CommonVariable) getApplication();

        playerCheck = getString(R.string.player_check);

        playerName.setText(String.format(playerCheck,commonVariable.map_number_playerName.get(commonVariable.transitionCount)));

        commonVariable.transitionCount++;
    }


    @Override
    public void onClick(View v) {
        Intent intent_playerCheck_to_numberCheck = new Intent(this, NumCheckActivity.class);
        startActivity(intent_playerCheck_to_numberCheck);
    }
}
