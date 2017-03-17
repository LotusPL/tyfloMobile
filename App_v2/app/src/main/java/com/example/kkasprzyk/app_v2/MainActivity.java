package com.example.kkasprzyk.app_v2;

import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.listeners.IBeaconListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;
import com.kontakt.sdk.android.common.profile.IBeaconRegion;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private ProximityManager proximityManager;
    public ArrayList<String> beacons = new ArrayList<String>();
    public Beacon b = new Beacon();
    public String beaconName= "";
    TextToSpeech t1;
    public int lang = 1;

    public String bName = "";
    public String tmp = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        Button english = (Button) findViewById(R.id.englishLanguageButton);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lang = 1;
                //setEng();
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.UK);
                        }
                    }
                });
                lang(lang);

            }
        });
        Button german = (Button) findViewById(R.id.germanLanguageButton);
        german.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lang = 2;
                //setGer();
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.GERMAN);
                        }
                    }
                });
                lang(lang);
            }
        });
        Button french = (Button) findViewById(R.id.frenchLanguageButton);
        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lang = 3;
                //setFR();
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.FRENCH);
                        }

                    }
                });
                lang(lang);
            }
        });

        ImageButton ukImageButton = (ImageButton) findViewById(R.id.ukImageButton);
        ukImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lang = 1;
                //setEng();
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.UK);
                        }
                    }
                });
                lang(lang);
            }
        });
        ImageButton germanyImageButton = (ImageButton) findViewById(R.id.germanImageButton);
        germanyImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lang = 2;
                //setGer();
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.GERMAN);
                        }
                    }
                });
                lang(lang);
            }
        });
        ImageButton frenchImageButton = (ImageButton) findViewById(R.id.frenchImageButton);
        frenchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lang = 3;
                //setFR();
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.FRENCH);
                        }

                    }
                });
                lang(lang);
            }
        });

        KontaktSDK.initialize("kYgqBKBXvxmQLRONfZsvPUDShWHHocRM");
        proximityManager = new ProximityManager(this);
        proximityManager.setIBeaconListener(new IBeaconListener() {
            @Override
            public void onIBeaconDiscovered(IBeaconDevice iBeacon, IBeaconRegion region) {
                Log.i("Test", "Discovered beacon: " + iBeacon.getUniqueId());
                Log.i("Test", "Distance: " + iBeacon.getDistance() + " m");
                Log.i("Test", "Battery level: " + iBeacon.getBatteryPower() + " %");
                Log.i("Test", "------Kolejny beacon------");
                addBeacons(iBeacon.getUniqueId().toString());
                Log.i("Test", "Lista beacon'ów: " + String.valueOf(beacons));
                Log.i("Test", "------Koniec listy beacon'ów------");

                beaconName = iBeacon.getUniqueId().toString();
                Log.i("Test4","beaconName: " + beaconName);
                if(lang == 1){
                    Log.i("Test3","Mówimy po angielsku !");
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.i("Test3","https://f16ea04f-c92b-46af-a9a6-a3898db428fe-bluemix.cloudant.com/krakaton2/" + beaconName);
                                URL restEndPoint = new URL("https://f16ea04f-c92b-46af-a9a6-a3898db428fe-bluemix.cloudant.com/krakaton2/" + beaconName);
                                HttpsURLConnection myConnection = (HttpsURLConnection) restEndPoint.openConnection();
                                if(myConnection.getResponseCode()==200){
                                    InputStream responseBody = myConnection.getInputStream();
                                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody,"UTF-8");
                                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                                    jsonReader.beginObject();
                                    while (jsonReader.hasNext()){
                                        Log.i("Test2","Do while wchodzi...");
                                        String key = jsonReader.nextName();

                                        if(key.equals("Name")){


                                            Log.i("Test3","Jestem w if'ie");
                                            String jsonName = jsonReader.nextString(); //zapisuje

                                            //t1.speak(jsonName, TextToSpeech.QUEUE_FLUSH, null, null);
                                            Log.i("Test3", "name(z nextString'a): " + jsonName);

                                            while (jsonReader.hasNext()){
                                                String key2 = jsonReader.nextName();
                                                if(key2.equals("Description")){
                                                    String jsonDesc = jsonReader.nextString();
                                                    Log.i("Test3","jsonDesc: " + jsonDesc);

                                                    while (jsonReader.hasNext()){
                                                        String key3 = jsonReader.nextName();
                                                        if(key3.equals("Type")){
                                                            String jsonType = jsonReader.nextString();
                                                            Log.i("Test3","jsonType: " + jsonType);

                                                            if(jsonType.equals("Bus")){
                                                                tmp = jsonType + jsonName + "has now arrived";
                                                            } else if(jsonType.equals("Bus stop")) {
                                                                tmp = "You are on " + jsonName + jsonType;
                                                            } else if(jsonType.equals("POI") || jsonType.equals("Building")) {
                                                                tmp = "You are close to " + jsonName;
                                                            }
                                                            t1.speak(tmp,TextToSpeech.QUEUE_FLUSH,null,null);

                                                            break;
                                                        }
                                                        else{
                                                            jsonReader.skipValue();
                                                        }
                                                    }

                                                    break;
                                                }
                                                else{
                                                    jsonReader.skipValue();
                                                }
                                            }

                                            break;
                                        }


                                        else{
                                            jsonReader.skipValue();
                                        }


                                    }
                                    jsonReader.close();
                                }
                                myConnection.disconnect();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
                if(lang == 2){
                    Log.i("Test3","Mówimy po niemiecku !");
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.i("Test3","https://f16ea04f-c92b-46af-a9a6-a3898db428fe-bluemix.cloudant.com/krakaton2/de%2F" + beaconName);
                                URL restEndPoint = new URL("https://f16ea04f-c92b-46af-a9a6-a3898db428fe-bluemix.cloudant.com/krakaton2/de%2F" + beaconName);
                                HttpsURLConnection myConnection = (HttpsURLConnection) restEndPoint.openConnection();
                                if(myConnection.getResponseCode()==200){
                                    InputStream responseBody = myConnection.getInputStream();
                                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody,"UTF-8");
                                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                                    jsonReader.beginObject();
                                    while (jsonReader.hasNext()){
                                        Log.i("Test2","Do while wchodzi...");
                                        String key = jsonReader.nextName();

                                        if(key.equals("Name")){


                                            Log.i("Test3","Jestem w if'ie");
                                            String jsonName = jsonReader.nextString(); //zapisuje

                                            //t1.speak(jsonName, TextToSpeech.QUEUE_FLUSH, null, null);
                                            Log.i("Test3", "name(z nextString'a): " + jsonName);

                                            while (jsonReader.hasNext()){
                                                String key2 = jsonReader.nextName();
                                                if(key2.equals("Description")){
                                                    String jsonDesc = jsonReader.nextString();
                                                    Log.i("Test3","jsonDesc: " + jsonDesc);

                                                    while (jsonReader.hasNext()){
                                                        String key3 = jsonReader.nextName();
                                                        if(key3.equals("Type")){
                                                            String jsonType = jsonReader.nextString();
                                                            Log.i("Test3","jsonType: " + jsonType);

                                                            if(jsonType.equals("Bus")){
                                                                tmp = "Bus Nummer " + jsonName + " ist jetzt angekommen";
                                                            } else if(jsonType.equals("Bus stop")) {
                                                                tmp = "Du bist auf " + jsonName + jsonType;
                                                            } else if(jsonType.equals("POI") || jsonType.equals("Building")) {
                                                                tmp = "Sie sind in der Nähe von " + jsonName;
                                                            }
                                                            t1.speak(tmp,TextToSpeech.QUEUE_FLUSH,null,null);

                                                            break;
                                                        }
                                                        else{
                                                            jsonReader.skipValue();
                                                        }
                                                    }

                                                    break;
                                                }
                                                else{
                                                    jsonReader.skipValue();
                                                }
                                            }

                                            break;
                                        }


                                        else{
                                            jsonReader.skipValue();
                                        }


                                    }
                                    jsonReader.close();
                                }
                                myConnection.disconnect();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
                if(lang == 3){
                    Log.i("Test3","Mówimy po francusku !");
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.i("Test3","https://f16ea04f-c92b-46af-a9a6-a3898db428fe-bluemix.cloudant.com/krakaton2/fr%2F" + beaconName);
                                URL restEndPoint = new URL("https://f16ea04f-c92b-46af-a9a6-a3898db428fe-bluemix.cloudant.com/krakaton2/fr%2F" + beaconName);
                                HttpsURLConnection myConnection = (HttpsURLConnection) restEndPoint.openConnection();
                                if(myConnection.getResponseCode()==200){
                                    InputStream responseBody = myConnection.getInputStream();
                                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody,"UTF-8");
                                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                                    jsonReader.beginObject();
                                    while (jsonReader.hasNext()){
                                        Log.i("Test2","Do while wchodzi...");
                                        String key = jsonReader.nextName();

                                        if(key.equals("Name")){
                                            Log.i("Test3","Jestem w if'ie");
                                            String jsonName = jsonReader.nextString(); //zapisuje

                                            //t1.speak(jsonName, TextToSpeech.QUEUE_FLUSH, null, null);
                                            Log.i("Test3", "name(z nextString'a): " + jsonName);

                                            while (jsonReader.hasNext()){
                                                String key2 = jsonReader.nextName();
                                                if(key2.equals("Description")){
                                                    String jsonDesc = jsonReader.nextString();
                                                    Log.i("Test3","jsonDesc: " + jsonDesc);

                                                    while (jsonReader.hasNext()){
                                                        String key3 = jsonReader.nextName();
                                                        if(key3.equals("Type")){
                                                            String jsonType = jsonReader.nextString();
                                                            Log.i("Test3","jsonType: " + jsonType);

                                                            if(jsonType.equals("Bus")){
                                                                tmp = "Le bus numéro " + jsonName + " est maintenant arrivé";
                                                            } else if(jsonType.equals("Bus stop")) {
                                                                tmp = "Vous êtes sur l'arrêt de bus " + jsonName;
                                                            } else if(jsonType.equals("POI") || jsonType.equals("Building")) {
                                                                tmp = "Vous êtes à proximité du " + jsonName;
                                                            }
                                                            t1.speak(tmp,TextToSpeech.QUEUE_FLUSH,null,null);

                                                            break;
                                                        }
                                                        else{
                                                            jsonReader.skipValue();
                                                        }
                                                    }

                                                    break;
                                                }
                                                else{
                                                    jsonReader.skipValue();
                                                }
                                            }

                                            break;
                                        }


                                        else{
                                            jsonReader.skipValue();
                                        }


                                    }
                                    jsonReader.close();
                                }
                                myConnection.disconnect();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }

            @Override
            public void onIBeaconsUpdated(List<IBeaconDevice> iBeacons, IBeaconRegion region) {

            }

            @Override
            public void onIBeaconLost(IBeaconDevice iBeacon, IBeaconRegion region) {
                Log.i("Test", "Lost beacon: " + iBeacon.getUniqueId());
                removeBeacons(iBeacon.getUniqueId().toString());
                //System.out.println(beacons);
            }
        });
    }
    public String setBeaconName(String id){
        String tmpName = id;
        return tmpName;
    }
    /*
    public void setEng(){
        lang = 1;
    }
    public void setGer(){
        lang = 2;
    }
    public void setFR(){
        lang = 3;
    }
    */
    public void lang(int i){
        TextView language = (TextView) findViewById(R.id.language);
        switch (i){
            case 1:
                language.setText("English");
                break;
            case 2:
                language.setText("German");
                break;
            case 3:
                language.setText("French");
                break;
            default:
                language.setText("English");
                break;
        }
    }

    public void addBeacons(String name) {
        boolean exist = false;
        for (int i = 0; i < beacons.size(); i++) {
            if (beacons.get(i).equals(name)) {
                exist = true;
            }
        }
        if (!exist) {
            beacons.add(name);
        }

    }

    public void removeBeacons(String name) {
        boolean exist = false;
        for (int i = 0; i < beacons.size(); i++) {
            if (beacons.get(i).equals(name)) {
                exist = true;
            }
        }
        if (exist) {
            beacons.remove(name);
        }
    }

    protected void onStart() {
        startScanning();
        super.onStart();
    }

    protected void onStop() {
        proximityManager.stopScanning();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        proximityManager.disconnect();
        proximityManager = null;
        super.onDestroy();
    }

    private void startScanning() {
        proximityManager.connect(new OnServiceReadyListener() {
            @Override
            public void onServiceReady() {
                proximityManager.startScanning();
            }
        });
    }


}
