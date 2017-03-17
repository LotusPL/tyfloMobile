package com.example.kkasprzyk.app_v2;

import android.speech.tts.TextToSpeech;

/**
 * Created by KKasprzyk on 2017-03-13.
 */

public class Beacon {

    String str ="";
    double longtitude,latitude = 0;
    public String recognition(String id){
        if(id.equals("dWlT")){
            str=("Miasteczko Studenckie AGH ("+id+")");
        }
        if(id.equals("AQ1b")){
            str=("Main square ("+id+")");
        }
        if(id.equals("ITp4")){
            str=("Teatr Bagatela ("+id+")");
        }
        if(id.equals("W3op")){
            str=("Dom ("+id+")");
        }
        if(id.equals("7ZhY")){
            str=("Dom ("+id+")");
        }
        return str;
    }
}
