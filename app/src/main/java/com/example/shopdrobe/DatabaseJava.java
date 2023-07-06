package com.example.shopdrobe;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseJava extends SQLiteOpenHelper {
    Context context;
    public static final String Table_User = "User";
    public static final String Table_Cart = "Cart";
    public static final String Table_History = "History";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ADDRESS = "address";
    public static final String NAME = "name";
    public static final String SIZE = "size";
    public static final String PRICE = "price";

    public DatabaseJava(Context context) {
        super(context, "shopdrobe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table User(id INTEGER primary key autoincrement, username TEXT, password TEXT, address TEXT)");
        MyDB.execSQL("create Table Cart(id INTEGER primary key autoincrement, username TEXT, name TEXT, price TEXT, size TEXT)");
        MyDB.execSQL("create Table History(id INTEGER primary key autoincrement, username TEXT, name TEXT, price TEXT, size TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists User");
        MyDB.execSQL("drop Table if exists Cart");
        MyDB.execSQL("drop Table if exists History");
        onCreate(MyDB);
    }

    public Boolean insertUser(String username, String password, String address) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("address", address);
        long result = MyDB.insert("User", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean insertCart(String username, String name, String price, String size) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("size", size);
        long result = MyDB.insert("Cart", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean insertHistory(String username, String name, String price, String size) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("size", size);
        long result = MyDB.insert("History", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public void updateProfile(String username, String password, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD, password);
        values.put(ADDRESS, address);

        long result = db.update(Table_User, values, "USERNAME = ?", new String[]{username});
        if (result == -1){
            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Update Successful!", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where username = ?", new String[] {username});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public Cursor UserProfile(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] {USERNAME, PASSWORD, ADDRESS};
        Cursor cursor = db.query(Table_User, columns, "username = ?", new String[] {username}, null, null, null, null);
        return cursor;
    }

    public Cursor searchCart(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] {NAME, SIZE, PRICE};
        Cursor cursor = db.query(Table_Cart, columns, "username = ?", new String[] {username}, null, null, null, null);
        return cursor;
    }

    public Cursor searchHistory(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] {ID, NAME, SIZE, PRICE};
        Cursor cursor = db.query(Table_History, columns, "username = ?", new String[] {username}, null, null, null, null);
        return cursor;
    }

    void deleteAllCart(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Table_Cart, "username = ?", new String[] {username});

        if (result == -1){
            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Delete Successful!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteDataCart(String username, String name, String size){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Table_Cart, "username = ? AND name = ? AND size = ?", new String[] {username, name, size});

        if (result == -1){
            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Delete Successful!", Toast.LENGTH_SHORT).show();
        }
    }


}