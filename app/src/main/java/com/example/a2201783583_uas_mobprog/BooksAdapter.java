package com.example.a2201783583_uas_mobprog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    // inisialisasi arraylist book
    private ArrayList<Book> books = new ArrayList<>();
    private String screen;
//    private final AdapterConnector listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // inisialisasi komponen view dan context
        public TextView bookName, bookPrice, bookAuthor;
        public ImageView bookImg;
        private Context context;

//        private WeakReference<AdapterConnector> listenerRef;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            // inisialiasasi view
            bookName = (TextView) itemView.findViewById(R.id.tvBooksName);
            bookPrice = (TextView) itemView.findViewById(R.id.tvBooksPrice);
            bookAuthor = (TextView) itemView.findViewById(R.id.tvBooksAuthor);
            bookImg = (ImageView) itemView.findViewById(R.id.ivBooks);
//            listenerRef = new WeakReference<>(listener);

            this.context = context;
            itemView.setOnClickListener(this);
        }

        public void glideImg(String uri) {
            Glide.with(context).load(uri).into(bookImg);
        }

        // method on click pada row recycler view
        @Override
        public void onClick(View v) {
//            listenerRef.get().onPositionClicked(getAdapterPosition());
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
//                System.out.println(books.get(position).getId());
//                System.out.println(screen);

//                apabila screen hp maka akan masuk ke if ini
                if(screen.equals("hp")){
                    Intent intent = new Intent(context, DetailBooksActivity.class);
                    intent.putExtra("id", books.get(position).getId());
                    context.startActivity(intent);
                } else if(screen.equals("tablet")) {
//                    DetailBooksFragment detailBooksFragment = new DetailBooksFragment();
//                    detailBooksFragment.setId(books.get(position).getId());
//                    getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, detailBooksFragment).commit();
                }

            }
        }
    }

    // constructor untuk adapter
    public BooksAdapter(ArrayList<Book> books, String screen) {
        this.books = books;
//        this.listener = listener;
        this.screen = screen;
    }

    // impelement method bawaan recycler view
    @NonNull
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View menuView = LayoutInflater.from(context).inflate(R.layout.rv_books_layout, parent, false);

        return new BooksAdapter.ViewHolder(context, menuView);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);

        holder.bookName.setText(book.getName());
        holder.bookPrice.setText("USD$ " + Double.toString(book.getPrice()));
        holder.bookAuthor.setText(book.getAuthor());
        holder.glideImg(book.getImg());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
