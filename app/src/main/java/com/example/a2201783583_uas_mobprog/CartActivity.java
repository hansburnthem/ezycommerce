package com.example.a2201783583_uas_mobprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    SQLiteHelper db;
    ArrayList<Cart> carts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView tvTotal = (TextView) findViewById(R.id.tvTotalPrice);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tvUsername = (TextView) toolbar.findViewById(R.id.toolbar_username);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        retrieveData(tvUsername);

        db = new SQLiteHelper(this);
        Cursor cursor = db.readAllData();

        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                carts.add(new Cart(
                        cursor.getInt(1),cursor.getDouble(4),
                        cursor.getString(2),cursor.getString(8),
                        cursor.getString(3),cursor.getString(5),
                        cursor.getString(6),cursor.getString(7),
                        cursor.getInt(9))
                );
            }
        }

        RecyclerView rvCarts = (RecyclerView) findViewById(R.id.rvCarts);
        CartAdapter cartAdapter = new CartAdapter(carts, new CartAdapter.ClickListener() {
            @Override
            public void onPositionClicked(int position) {

            }
        });
        rvCarts.setAdapter(cartAdapter);
        rvCarts.setLayoutManager(new LinearLayoutManager(this));

        tvTotal.setText("Total Price: USD " + Double.toString(calculateTotalPrice()));
    }

    private double calculateTotalPrice() {
        double total=0;
        for (int i = 0; i <carts.size() ; i++) {
            total += (carts.get(i).getQty() * carts.get(i).getPrice());
        }
        return total;
    }

    public void retrieveData(TextView tvUsername){
        API.apiEndpoint().getRawBooks("2201783583","Hanif Rama Zhaky").enqueue(new Callback<RawBooks>() {
            @Override
            public void onResponse(Call<RawBooks> call, Response<RawBooks> response) {
                if(!response.isSuccessful()) return;
                RawBooks rawBooks = response.body();
                tvUsername.setText(rawBooks.getName());
            }

            @Override
            public void onFailure(Call<RawBooks> call, Throwable t) {
                Log.d("err",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_btn:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}