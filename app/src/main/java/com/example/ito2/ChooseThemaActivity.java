package com.example.ito2;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class ChooseThemaActivity extends AppCompatActivity implements View.OnClickListener {

    //static変数
    Button btnAnswer;
    Button btnAdd;
    Button btnReplay;
    EditText editText;
    LinearLayout layoutAnswer;
    LinearLayout layoutChildren;
    String playerName;
    boolean answer_is_collect = true;

    private CommonVariable commonVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_thema);

        btnAdd = findViewById(R.id.btn_add);
        btnAnswer = findViewById(R.id.btn_answer);
        btnReplay = findViewById(R.id.btn_replay);
        layoutAnswer = findViewById(R.id.layout_answerlist);

        btnAdd.setOnClickListener(this);
        btnAnswer.setOnClickListener(this);
        btnReplay.setOnClickListener(this);

        commonVariable = (CommonVariable) getApplication();

        commonVariable.makeAnswer();
    }


    public  void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_answer:
                Answer();
                break;

            case R.id.btn_replay:
                Replay();
                break;

            case R.id.btn_add:
                Addplayer();
                break;
        }

    }

    private void Answer() {
        Integer answerName = 0;
        for (int t = 0; t<layoutAnswer.getChildCount(); t++){
            layoutChildren = (LinearLayout) layoutAnswer.getChildAt(t);
            editText = (EditText) layoutChildren.getChildAt(1);
            playerName = editText.getText().toString();

            if(playerName.isEmpty()){ continue; }

            if(!playerName.equals(commonVariable.answer_playerList.get(answerName))){
                answer_is_collect = false;
            }
                answerName++;
        }

        if(answer_is_collect){

           AlertDialog.Builder dialog = new AlertDialog.Builder(this);

           dialog.setTitle(R.string.txt_answer_is_collect);
           dialog.setMessage(commonVariable.answerMessage);
           dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

               @Override
               public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
               }
           });
           dialog.show();

        }else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);

            dialog.setTitle(R.string.txt_answer_is_wrong);
            dialog.setMessage(commonVariable.answerMessage);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog.show();

            answer_is_collect = true;
        }

    }

    private void Replay() {
        Intent intent_chooseThemaActivity_to_playerCheck = new Intent(this,PlayerCheckActivity.class);
        commonVariable.restart();

        startActivity(intent_chooseThemaActivity_to_playerCheck);
    }

    private void Addplayer() {
        final View answerView = getLayoutInflater().inflate(R.layout.add_new_player,null,false);

        ImageView imageRemove = answerView.findViewById(R.id.image_remove);

        imageRemove.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    removeView(answerView);
                }
            }

        );

        layoutAnswer.addView(answerView);

    }

    private void removeView(View view){
        layoutAnswer.removeView(view);
    }
}

