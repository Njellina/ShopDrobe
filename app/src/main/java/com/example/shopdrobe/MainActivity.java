package com.example.shopdrobe;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DatabaseJava db;
    String username;
    String password;
    EditText Username;
    EditText Password;
    ImageButton login;
    ImageButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseJava(this);
        Username = findViewById(R.id.textUsername);
        Password = findViewById(R.id.textPassword);
        login = (ImageButton) findViewById(R.id.buttonLogin);
        register = (ImageButton) findViewById(R.id.buttonRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = Username.getText().toString();
                password = Password.getText().toString();

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Field Cannot be Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checklogin = db.checkLogin(username, password);
                    if (checklogin == true){
                        Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(getApplicationContext(), Dashboard.class);
                        login.putExtra("username", username);
                        startActivity(login);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

    }
}