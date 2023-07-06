package com.example.shopdrobe;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;


public class Location extends AppCompatActivity{
    ImageButton outletA;
    ImageButton outletB;
    ImageButton outletC;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        outletA = (ImageButton) findViewById(R.id.buttonOutletA);
        outletB = (ImageButton) findViewById(R.id.buttonOutletB);
        outletC = (ImageButton) findViewById(R.id.buttonOutletC);
        back = (ImageButton) findViewById(R.id.buttonBack);

        outletA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OutletA.class));
            }
        });

        outletB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OutletB.class));
            }
        });

        outletC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OutletC.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });

    }
}
