package com.example.myetesito;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myetesito.Basesita.BDinfo;
import com.example.myetesito.Json.Info;
import com.example.myetesito.Json.Json;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Website extends AppCompatActivity {

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        textview = findViewById(R.id.textView);

        try {

            int numArchivo = getIntent().getExtras().getInt("numArchivo");
            Json json = new Json();

            BDinfo bdinfo = new BDinfo (Website.this);
            String completoTexto = bdinfo.verInfo(numArchivo);
            Info datos = json.leerJson(completoTexto);

            textview.setText("Bienvenido, " + datos.getName());
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
            new Handler( ).postDelayed(new Runnable() {
                @Override
                public void run(){
                    Intent intent = new Intent( Website.this, ListMain.class);
                    intent.putExtra("numArchivo", numArchivo);
                    startActivity( intent );
                }
            } , 4000 );
        }catch(Exception e){}
    }
}