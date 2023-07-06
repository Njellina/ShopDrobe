package com.example.shopdrobe;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private DatabaseJava db;
    String username;
    String password;
    String address;
    EditText Username;
    EditText Password;
    EditText Address;
    ImageButton register;
    ImageButton login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        db = new DatabaseJava(this);
        Username = findViewById(R.id.textUsername);
        Password = findViewById(R.id.textPassword);
        Address = findViewById(R.id.textAddress);
        register = (ImageButton) findViewById(R.id.buttonRegister);
        login = (ImageButton) findViewById(R.id.buttonLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = Username.getText().toString();
                password = Password.getText().toString();
                address = Address.getText().toString();

                if (username.isEmpty()) {
                    Username.setError("Username is required!");
                    Username.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    Password.setError("Password is required!");
                    Password.requestFocus();
                    return;
                }
                if (address.isEmpty()) {
                    Address.setError("Address is required!");
                    Address.requestFocus();
                    return;
                }
                else{
                    Boolean checkuser = db.checkUsername(username);
                    if (checkuser == false){
                        Boolean insert = db.insertUser(username, password, address);
                        if (insert == true){
                            Toast.makeText(getApplicationContext(), "Register Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Username already exists! Please Log in!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
