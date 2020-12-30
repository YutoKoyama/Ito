package com.example.ito2;


import android.app.Application;
import android.os.Build;

import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

public class CommonVariable extends Application {
    Integer numPlayer = 0;
    HashMap<Integer, String> map_number_playerName = new HashMap<>();
    HashMap<Integer,String> map_answer = new HashMap<>();
    Integer transitionCount = 0;
    ArrayList<Integer> numbersList = new ArrayList<>();
    ArrayList<String> answer_playerList = new ArrayList<>();
    ArrayList<String> answerArrayList = new ArrayList<>();
    String answerMessage = "";

    public void restart(){
        transitionCount = 0;
        answerMessage = "";
        answerArrayList.clear();
        numbersList.clear();
        answer_playerList.clear();
        map_answer.clear();

    }

    public void makeAnswer(){
        for(Integer i = 0; i < map_number_playerName.size(); i++){
            map_answer.put(numbersList.get(i), map_number_playerName.get(i));
        }

        Collections.sort(numbersList);

        for(Integer key :numbersList){
            answer_playerList.add(map_answer.get(key));
            answerArrayList.add(String.format("%s : %d",map_answer.get(key),key));

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            answerMessage = String.join("\n",answerArrayList);
        }else {
            answerMessage = getResources().getString(R.string.eerror_massage);
        }


    }
}
