package com.example.a2201783583_uas_mobprog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_books);

        Integer id = getIntent().getIntExtra("id", 0);
        DetailBooksFragment detailBooksFragment = (DetailBooksFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);
        detailBooksFragment.setId(id);
    }
}