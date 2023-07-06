package com.example.shopdrobe;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    private DatabaseJava db;
    TextView Username;
    TextView Password;
    TextView Address;
    ImageButton Profile;
    ImageButton EditProfile;
    ImageButton Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        db = new DatabaseJava(this);
        Username = findViewById(R.id.textUsername);
        Password = findViewById(R.id.textPassword);
        Address = findViewById(R.id.textAddress);
        EditProfile = (ImageButton) findViewById(R.id.buttonEditProfile);
        Profile = (ImageButton) findViewById(R.id.buttonProfile);
        Logout = (ImageButton) findViewById(R.id.buttonLogout);

        Bundle login = this.getIntent().getExtras();
        if(login != null){
            String uname = (String) login.get("username");
            Cursor cursor = db.UserProfile(uname);
            if (cursor.moveToFirst()){
                Username.setText(cursor.getString(0));
                Password.setText(cursor.getString(1));
                Address.setText(cursor.getString(2));
            }

            Profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent back = new Intent(getApplicationContext(), Dashboard.class);
                    back.putExtra("username", uname);
                    startActivity(back);
                }
            });

            EditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent change = new Intent(getApplicationContext(), EditProfile.class);
                    change.putExtra("username", uname);
                    startActivity(change);
                }
            });

            Logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(logout);
                }
            });

        }
    }
}
