package com.example.kkasprzyk.app_v2;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Locale;

public class Read extends AppCompatActivity {
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.UK);

                }
            }
        });
        ArrayList<String> beacons = (ArrayList<String>)getIntent().getSerializableExtra("ID");

        for(int i = 0; i<beacons.size(); i++){
            if(beacons.get(i).toString().equals("AQ1b")){
                t1.speak(beacons.get(i).toString(),TextToSpeech.QUEUE_FLUSH,null,null);
            }
        }

    }
}
