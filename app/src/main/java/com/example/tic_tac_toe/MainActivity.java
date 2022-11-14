package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 // variable
    Button one_player ,two_player,online;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // assign view to variable
        one_player=findViewById(R.id.btn_oneplayer);
        two_player=findViewById(R.id.btn_twoplayer);
        online=findViewById(R.id.btn_online);

        one_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this,One_player.class);
                  intent.putExtra("which",0);
                  startActivity(intent);
            }
        });
        two_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,One_player.class);
                intent.putExtra("which",1);
                startActivity(intent);
            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Loading............", Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Pls Cheack Your internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}