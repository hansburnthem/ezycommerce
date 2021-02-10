package com.example.a2201783583_uas_mobprog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksFragment extends Fragment {

    // inisialisasi variable
    private ArrayList<Book> books = new ArrayList<>();
    private BooksAdapter booksAdapter;

    // impelement method bawaan fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_books, container, false);

        //menerima data dari activity
        Bundle bundle = getArguments();
        String category = bundle.getString("category","");
        String screen = bundle.getString("screen","");

        // memanggil method
        retrieveData(category.isEmpty() ? "" : category);

        // recycler view dan adapter
        RecyclerView rvBooks = fragmentLayout.findViewById(R.id.rvBooks);
        booksAdapter =  new BooksAdapter(books, screen);
        rvBooks.setAdapter(booksAdapter);
        rvBooks.setLayoutManager(new LinearLayoutManager(fragmentLayout.getContext()));

        return fragmentLayout;
    }

    // get data dari API dan menfilter category
    private void retrieveData(String category) {
        API.apiEndpoint().getRawBooks("2201783583","Hanif Rama Zhaky").enqueue(new Callback<RawBooks>() {
            @Override
            public void onResponse(Call<RawBooks> call, Response<RawBooks> response) {
                if (!response.isSuccessful()) return;
                for (Book book : response.body().getBooks()) {
                    if (book.getCategory().equals(category) || category.equals("All"))
                    books.add(new Book(book.getId(),book.getPrice(),book.getName(),book.getCategory(),book.getDesc(),book.getAuthor(),book.getType(),book.getImg()));
                }
                booksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RawBooks> call, Throwable t) {
                Log.d("err",t.getMessage());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
