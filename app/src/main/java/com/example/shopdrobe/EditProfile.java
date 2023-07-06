package com.example.shopdrobe;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {
    private DatabaseJava db;
    EditText Username;
    EditText Password;
    EditText Address;
    String username;
    String password;
    String address;
    ImageButton back;
    ImageButton check;
    ImageButton confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        db = new DatabaseJava(this);
        Username = findViewById(R.id.textUsername);
        Password = findViewById(R.id.textPassword);
        Address = findViewById(R.id.textAddress);
        back = (ImageButton) findViewById(R.id.buttonBack);
        check = (ImageButton) findViewById(R.id.buttonCheck);
        confirm = (ImageButton) findViewById(R.id.buttonConfirm);
        Bundle login = this.getIntent().getExtras();
        String uname = (String) login.get("username");

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = Username.getText().toString();
                password = Password.getText().toString();
                address = Address.getText().toString();

                if (username.isEmpty()) {
                    Username.setError("Username is required!");
                    Username.requestFocus();
                    return;
                }
                else{
                    try{
                        Cursor cursor = db.UserProfile(username);
                        if (cursor.moveToFirst()){
                            Password.setText(cursor.getString(1));
                            Address.setText(cursor.getString(2));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Username not found!", Toast.LENGTH_SHORT).show();
                            Username.setText("Username not found!");
                        }
                        cursor.close();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = Username.getText().toString();
                password = Password.getText().toString();
                address = Address.getText().toString();

                if (username.isEmpty()) {
                    Username.setError("Username is required!");
                    Username.requestFocus();
                    return;
                }
                else{
                    Boolean check = db.checkUsername(username);
                    if (check == true){
                        Boolean checkuser = db.checkUsername(username);
                        if (checkuser == false){
                            Toast.makeText(getApplicationContext(), "Wrong Username!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            try{
                                db.updateProfile(username, password, address);
                                Toast.makeText(getApplicationContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            Intent done = new Intent(getApplicationContext(), Profile.class);
                            done.putExtra("username", uname);
                            startActivity(done);
                        }
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), Dashboard.class);
                back.putExtra("username", uname);
                startActivity(back);
            }
        });

    }
}
