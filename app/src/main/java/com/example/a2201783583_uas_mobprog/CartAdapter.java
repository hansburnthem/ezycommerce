package com.example.a2201783583_uas_mobprog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    //Interface dipakai untuk membuat Custom Listener
    public interface ClickListener {
        void onPositionClicked(int position);
    }

    // inisialisasi arraylist book
    private ArrayList<Cart> carts = new ArrayList<>();
    private final CartAdapter.ClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // inisialisasi komponen view dan context
        public TextView bookName, bookPrice, bookAuthor, bookQty, bookSubTotal;
        public ImageView bookImg;
        private Context context;

        private WeakReference<ClickListener> listenerRef;

        public ViewHolder(Context context, View itemView, CartAdapter.ClickListener listener) {
            super(itemView);

            // inisialiasasi view
            bookName = (TextView) itemView.findViewById(R.id.tvBooksName);
            bookPrice = (TextView) itemView.findViewById(R.id.tvBooksPrice);
            bookAuthor = (TextView) itemView.findViewById(R.id.tvBooksAuthor);
            bookImg = (ImageView) itemView.findViewById(R.id.ivBooks);
            bookQty = (TextView) itemView.findViewById(R.id.tvQuantity);
            bookSubTotal = (TextView) itemView.findViewById(R.id.tvSubTotal);
            listenerRef = new WeakReference<>(listener);

            this.context = context;
            itemView.setOnClickListener(this);
        }

        public void glideImg(String uri) {
            Glide.with(context).load(uri).into(bookImg);
        }

        // method on click pada row recycler view
        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }

    // constructor untuk adapter
    public CartAdapter(ArrayList<Cart> carts, ClickListener listener) {
        this.carts = carts;
        this.listener = listener;
    }

    // impelement method bawaan recycler view
    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View menuView = LayoutInflater.from(context).inflate(R.layout.rv_carts, parent, false);

        return new CartAdapter.ViewHolder(context, menuView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = carts.get(position);
//        System.out.printf("%d %s %d\n",cart.getBookId(),cart.getName(),cart.getQty());
        holder.bookName.setText(cart.getName());
        holder.bookPrice.setText("USD$ " + Double.toString(cart.getPrice()));
        holder.bookAuthor.setText(cart.getAuthor());
        holder.glideImg(cart.getImg());
        holder.bookQty.setText("Quantity: " + Integer.toString(cart.getQty()));
        holder.bookSubTotal.setText("Sub Total: " + Double.toString((double) (cart.getQty() * cart.getPrice())));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
