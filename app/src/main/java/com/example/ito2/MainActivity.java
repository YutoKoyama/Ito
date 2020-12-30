package com.example.ito2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //static変数
    LinearLayout layoutList;
    Button btnAdd;
    Button btnPlay;
    EditText editText;

    private CommonVariable commonVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutList = findViewById(R.id.layout_list);
        btnAdd = findViewById(R.id.btn_add);
        btnPlay = findViewById(R.id.btn_play);

        btnAdd.setOnClickListener(this);
        btnPlay.setOnClickListener(this);

        commonVariable = (CommonVariable) getApplication();
    }


    public  void onClick(View view){
        switch (view.getId()){
            case R.id.btn_add:
                    addView();
            break;

            case R.id.btn_play:
                    Play();
             break;
        }

    }


//    機能
//   ・Viewの追加
//   ・editTextにID付与
//   ・プレイヤー人数のカウント
    private void addView(){
        final View addplayerView = getLayoutInflater().inflate(R.layout.add_new_player,null,false);

        ImageView imageRemove = addplayerView.findViewById(R.id.image_remove);

        imageRemove.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    removeView(addplayerView);
                }
            }

        );

        //layoutに追加
        layoutList.addView(addplayerView);
    }


    private void removeView(View view){
        layoutList.removeView(view);
    }


    private void Play() {
        String playerName;
        LinearLayout layoutChildren;
        Intent intent_main_to_playerCheck = new Intent(this,PlayerCheckActivity.class);

        for (int t = 0; t<layoutList.getChildCount(); t++){
            layoutChildren = (LinearLayout) layoutList.getChildAt(t);
            editText = (EditText) layoutChildren.getChildAt(1);
            playerName = editText.getText().toString();
            commonVariable.map_number_playerName.put(t,playerName);
        }

        commonVariable.numPlayer = layoutList.getChildCount();
        startActivity(intent_main_to_playerCheck);
    }
}








