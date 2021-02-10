package com.example.a2201783583_uas_mobprog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    // inisialisasi arraylist categories
    private ArrayList<String> categories = new ArrayList<>();
    private final AdapterConnector listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // inisialisasi komponen view dan context
        public Button categoriesBtn;

        private WeakReference<AdapterConnector> listenerRef;

        public ViewHolder(View itemView, AdapterConnector listener) {
            super(itemView);

            // inisialiasasi view
            listenerRef = new WeakReference<>(listener);
            categoriesBtn = (Button) itemView.findViewById(R.id.btCategoriesName);

            categoriesBtn.setOnClickListener(this);
        }

        // method on click pada button recycler view
        @Override
        public void onClick(View v) {
            // disini kita mengambil interface yang telah dibuat diatas untuk dipassing
            // position si adapter
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }

    // constructor untuk adapter
    public CategoriesAdapter(ArrayList<String> categories, AdapterConnector listener) {
        this.categories = categories;
        this.listener = listener;
    }

    // impelement method bawaan recycler view
    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View menuView = LayoutInflater.from(context).inflate(R.layout.rv_categories_layout, parent, false);

        return new CategoriesAdapter.ViewHolder(menuView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        String category = categories.get(position);

        holder.categoriesBtn.setText(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
