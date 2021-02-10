package com.example.a2201783583_uas_mobprog;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBooksFragment extends Fragment {

    // inisiaslisi variable
    private int id;
    private Cart cart;
    ImageView ivDetailImg;
    TextView tvDetailName, tvDetailDesc, tvDetailPrice, tvDetailCategory, tvDetailAuthor, tvDetailType;
    Button addtocartBtn;

    // create method untuk men set id yang di kirim dari books adapter
    public void setId(int id) {
        this.id = id;
    }

    // impelment method bawaan
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_detail_books, container, false);
        return fragmentLayout;
    }

    // get data API untuk detail buku
    private void retrieveData() {
        API.apiEndpoint().getRawBooks("2201783583","Hanif Rama Zhaky").enqueue(new Callback<RawBooks>() {
            @Override
            public void onResponse(Call<RawBooks> call, Response<RawBooks> response) {
                if (!response.isSuccessful()) return;
                ArrayList<Book> books = response.body().getBooks();

                for (int i = 0; i < books.size(); i++) {
                    if(books.get(i).getId() == id){
                        cart = new Cart(books.get(i).getId(),books.get(i).getPrice(),books.get(i).getName(),books.get(i).getCategory(),books.get(i).getDesc(),books.get(i).getAuthor(),books.get(i).getType(),books.get(i).getImg(),0);
                        Glide.with(requireContext()).load(books.get(i).getImg()).into(ivDetailImg);
                        tvDetailName.setText(books.get(i).getName());
                        tvDetailDesc.setText(books.get(i).getDesc());
                        tvDetailPrice.setText(Double.toString(books.get(i).getPrice()));
                        tvDetailCategory.setText(books.get(i).getCategory());
                        tvDetailAuthor.setText(books.get(i).getAuthor());
                        tvDetailType.setText(books.get(i).getType());
                    }
                }
            }

            @Override
            public void onFailure(Call<RawBooks> call, Throwable t) {
                Log.d("err",t.getMessage());
            }
        });
    }

    // inisialisasi text view dan kawan kawannya
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivDetailImg = (ImageView) view.findViewById(R.id.ivDetailImg);
        tvDetailName = (TextView) view.findViewById(R.id.tvDetailName);
        tvDetailDesc = (TextView) view.findViewById(R.id.tvDetailDesc);
        tvDetailPrice = (TextView) view.findViewById(R.id.tvDetailPrice);
        tvDetailCategory = (TextView) view.findViewById(R.id.tvDetailCategory);
        tvDetailAuthor = (TextView) view.findViewById(R.id.tvDetailAuthor);
        tvDetailType = (TextView) view.findViewById(R.id.tvDetailType);
        addtocartBtn = (Button) view.findViewById(R.id.addtocartBtn);
        addtocartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inisiasliasi SQLite database
                SQLiteHelper db = new SQLiteHelper(requireContext());
                Cursor cursor = db.readById(cart.getBookId());

                // menyimpan data ke db
                if (cursor.getCount() != 0){
                    if (cursor.moveToFirst()) {
                        if (cursor.getInt(1) == cart.getBookId()) {
                            db.updateQty(cursor.getInt(1),(cursor.getInt(9) + 1));
                        }
                    }
                } else
                    db.addToCart(cart.getBookId(),cart.getName(),cart.getDesc(),cart.getPrice(),cart.getAuthor(),cart.getType(),cart.getImg(),cart.getCategory(),1);

                Toast.makeText(requireContext(), "Success add to cart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        retrieveData();
    }
}
