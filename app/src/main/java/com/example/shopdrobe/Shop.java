package com.example.shopdrobe;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Shop extends AppCompatActivity {
    ImageButton sunnydress;
    ImageButton redshirt;
    ImageButton cart;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        sunnydress = (ImageButton) findViewById(R.id.buttonSunny);
        redshirt = (ImageButton) findViewById(R.id.buttonSolo);
        cart = (ImageButton) findViewById(R.id.buttonCart);
        back = (ImageButton) findViewById(R.id.buttonBack);

        Bundle login = this.getIntent().getExtras();
        String uname = (String) login.get("username");

        sunnydress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sunny = new Intent(getApplicationContext(), SunnySize.class);
                sunny.putExtra("username", uname);
                startActivity(sunny);
            }
        });

        redshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent red = new Intent(getApplicationContext(), SoloSize.class);
                red.putExtra("username", uname);
                startActivity(red);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(getApplicationContext(), Cart.class);
                cart.putExtra("username", uname);
                startActivity(cart);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), Dashboard.class);
                back.putExtra("username", uname);
                startActivity(back);
            }
        });


    }
}
