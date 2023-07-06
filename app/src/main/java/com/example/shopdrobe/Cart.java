package com.example.shopdrobe;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Cart extends AppCompatActivity {
    private DatabaseJava db;
    AdapterCart adapter;
    RecyclerView recyclerView;
    ImageButton back;
    ImageButton purchase;
    ImageButton delete;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    ArrayList<String> username, name, size, price;
    private String names, sizes, prices;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclecart);
        db = new DatabaseJava(this);
        username = new ArrayList<>();
        name = new ArrayList<>();
        size = new ArrayList<>();
        price = new ArrayList<>();

        back = (ImageButton) findViewById(R.id.buttonBack);
        purchase = (ImageButton) findViewById(R.id.buttonPurchase);
        delete = (ImageButton) findViewById(R.id.buttonTrash);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new AdapterCart(this, name, price, size);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

        Bundle loginn = this.getIntent().getExtras();
        String uname = (String) loginn.get("username");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), Shop.class);
                back.putExtra("username", uname);
                startActivity(back);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(Cart.this);
                builder.setTitle("Delete all item");
                builder.setMessage("Do you really want to delete all item?");
                builder.setCancelable(true);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteAllCart(uname);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.searchCart(uname);
                if (cursor.moveToFirst()){
                    names = (cursor.getString(0));
                    sizes = (cursor.getString(1));
                    prices = (cursor.getString(2));
                }
                db.insertHistory(uname, names, sizes, prices);
                db.deleteDataCart(uname, names, sizes);
                Toast.makeText(getApplicationContext(), "Purchase Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Shop.class));
            }
        });

    }

    private void displaydata(){
        Bundle loginn = this.getIntent().getExtras();
        String uname = (String) loginn.get("username");

        Cursor cursor = db.searchCart(uname);
        if (cursor.getCount()==0){
            Toast.makeText(Cart.this, "No Information can be shown!", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                name.add(cursor.getString(0));
                size.add(cursor.getString(1));
                price.add(cursor.getString(2));
            }
        }
    }

}
