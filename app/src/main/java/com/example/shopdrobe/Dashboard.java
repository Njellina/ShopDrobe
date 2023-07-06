package com.example.shopdrobe;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    ImageButton profile;
    ImageButton location;
    ImageButton history;
    ImageButton shop;
    ImageButton support;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        name = findViewById(R.id.textUsername);
        profile = (ImageButton) findViewById(R.id.buttonProfile);
        location = (ImageButton) findViewById(R.id.buttonLocation);
        history = (ImageButton) findViewById(R.id.buttonHistory);
        shop = (ImageButton) findViewById(R.id.buttonShop);
        support = (ImageButton) findViewById(R.id.buttonSupport);

        Bundle login = this.getIntent().getExtras();
        String uname = (String) login.get("username");
        name.setText(uname);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(getApplicationContext(), Profile.class);
                profile.putExtra("username", uname);
                startActivity(profile);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Location.class));
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(getApplicationContext(), History.class);
                history.putExtra("username", uname);
                startActivity(history);
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shop = new Intent(getApplicationContext(), Shop.class);
                shop.putExtra("username", uname);
                startActivity(shop);
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Support.class));
            }
        });

    }
}
