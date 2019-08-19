package com.kenny.roekasa.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kenny.roekasa.weatherapp.common.Common;
import com.kenny.roekasa.weatherapp.helper.Helper;
import com.kenny.roekasa.weatherapp.model.OpenWeatherMap;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {


    OpenWeatherMap openWeatherMap = new OpenWeatherMap();
    int MY_PERMISSION = 0;
    EditText textBox;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBox = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        text = findViewById(R.id.text);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{

            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE
        },MY_PERMISSION);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetWeather().execute(Common.apiRequest(textBox.getText().toString()));




            }
        });
    }

    private class GetWeather extends AsyncTask<String,Void,String>{
        ProgressDialog pd = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
           String stream = null;
           String urlString = strings[0];
            Helper http = new Helper();
            stream = http.getHTTPData(urlString);
            return stream;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.contains("Error: Not found city")) {
                pd.dismiss();
                return;


            }

            Gson gson = new Gson();
            Type mType = new TypeToken<OpenWeatherMap>(){}.getType();
            openWeatherMap = gson.fromJson(s, mType);
            pd.dismiss();

            text.setText(String.format("%fC", openWeatherMap.getMain().getTemp()));

        }




    }

}
