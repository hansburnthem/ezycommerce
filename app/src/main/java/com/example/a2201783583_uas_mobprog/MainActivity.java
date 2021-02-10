package com.example.a2201783583_uas_mobprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private ArrayList<String> categories = new ArrayList<>();
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // menginisalisasi toolbar yang terdapat di paling atas layar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tvUsername = (TextView) toolbar.findViewById(R.id.toolbar_username);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // menginisialisasi recycler view beserta adapternya
        RecyclerView rvCategories = (RecyclerView) findViewById(R.id.rvCategories);
        categoriesAdapter = new CategoriesAdapter(categories, new AdapterConnector() {
            @Override
            public void onPositionClicked(int position) {
                System.out.println(categories.get(position));
                if(findViewById(R.id.detail_fragment_container) != null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, initBooksFragment(categories.get(position),"tablet")).commit();
                else
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, initBooksFragment(categories.get(position),"hp")).commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, initBooksFragment(categories.get(position),"")).commit();
            }
        });
        rvCategories.setAdapter(categoriesAdapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // untuk mengecek apakah layout yang sedang aktif adalah layout hp atau tablet
        if(findViewById(R.id.detail_fragment_container) != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, initBooksFragment("All","tablet")).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, initBooksFragment("All","hp")).commit();

        retrieveData(tvUsername);
    }

    // membundle argument yang akan di kirim kan ke dalam fragment
    public static BooksFragment initBooksFragment(String category, String screen) {
        BooksFragment booksFragment = new BooksFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category",category);
        bundle.putString("screen",screen);
        booksFragment.setArguments(bundle);
        return booksFragment;
    }

    // method ini untuk menerima data dari API
    public void retrieveData(TextView tvUsername){
        API.apiEndpoint().getRawBooks("2201783583","Hanif Rama Zhaky").enqueue(new Callback<RawBooks>() {
            @Override
            public void onResponse(Call<RawBooks> call, Response<RawBooks> response) {
                if(!response.isSuccessful()) return;
                RawBooks rawBooks = response.body();
                categories.add("All");
                for (Book book : rawBooks.getBooks()) {
                    if(!categories.contains(book.getCategory())) categories.add(book.getCategory());
                }
                categoriesAdapter.notifyDataSetChanged();
                tvUsername.setText(rawBooks.getName());
            }

            @Override
            public void onFailure(Call<RawBooks> call, Throwable t) {
                Log.d("err",t.getMessage());
            }
        });
    }

    // implement method bawaan option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.myorder_btn:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}