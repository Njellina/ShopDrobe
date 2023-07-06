package com.example.shopdrobe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SoloSize extends AppCompatActivity {
    private DatabaseJava db;
    ImageButton addcart;
    ImageButton back;
    RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solosize);

        db = new DatabaseJava(this);
        addcart = (ImageButton) findViewById(R.id.buttonAddCart);
        back = (ImageButton) findViewById(R.id.buttonBack);
        radio = findViewById(R.id.radiogroup);

        Bundle login = this.getIntent().getExtras();
        String uname = (String) login.get("username");

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.radioS:
                        addcart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean insert = db.insertCart(uname, "Solo Red Shirt", "IDR 50.000", "S");
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Insert Successful!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Shop.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Insert Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;

                    case R.id.radioM:
                        addcart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean insert = db.insertCart(uname, "Solo Red Shirt", "IDR 50.000", "M");
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Insert Successful!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Shop.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Insert Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;

                    case R.id.radioL:
                        addcart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean insert = db.insertCart(uname, "Solo Red Shirt", "IDR 50.000", "L");
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Insert Successful!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Shop.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Insert Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;

                    case R.id.radioXL:
                        addcart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean insert = db.insertCart(uname, "Solo Red Shirt", "IDR 50.000", "XL");
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Insert Successful!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Shop.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Insert Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), Shop.class);
                back.putExtra("username", uname);
                startActivity(back);
            }
        });


    }
}
