package com.example.shopdrobe;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class History extends AppCompatActivity {
    private DatabaseJava db;
    AdapterHistory adapter;
    RecyclerView recyclerView;
    ImageButton back;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    ArrayList<String> username, id, name, size, price;
    private String ids, names, sizes, prices;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclehistory);
        db = new DatabaseJava(this);
        username = new ArrayList<>();
        id = new ArrayList<>();
        name = new ArrayList<>();
        size = new ArrayList<>();
        price = new ArrayList<>();

        back = (ImageButton) findViewById(R.id.buttonBack);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new AdapterHistory(this, id, name, price, size);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

        Bundle loginn = this.getIntent().getExtras();
        String uname = (String) loginn.get("username");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), Dashboard.class);
                back.putExtra("username", uname);
                startActivity(back);
            }
        });

    }

    private void displaydata(){
        Bundle loginn = this.getIntent().getExtras();
        String uname = (String) loginn.get("username");

        Cursor cursor = db.searchHistory(uname);
        if (cursor.getCount()==0){
            Toast.makeText(History.this, "No Information can be shown!", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                size.add(cursor.getString(2));
                price.add(cursor.getString(3));
            }
        }
    }

}
