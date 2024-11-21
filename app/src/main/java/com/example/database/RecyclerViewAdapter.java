package com.example.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Book> bookArrayList;
    private final DataBaseHelper dbHelper;

    public RecyclerViewAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
        this.dbHelper = new DataBaseHelper(context);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Book book = bookArrayList.get(position);

        holder.bookName.setText(book.getName_Book());
        holder.bookAuthor.setText(book.getAuthor_Book());

        // Переход на экран с подробностями
        holder.itemView.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(context, BookDetail.class);
                intent.putExtra("BOOK_ID", book.getID_Book());
                intent.putExtra("BOOK_NAME", book.getName_Book());
                intent.putExtra("BOOK_AUTHOR", book.getAuthor_Book());
                context.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(context, "Ошибка открытия деталей книги", Toast.LENGTH_SHORT).show();
            }
        });

        // Удаление элемента
        holder.deleteButton.setOnClickListener(v -> {
            bookArrayList.remove(position); // Удаление из списка
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, bookArrayList.size());

            dbHelper.deleteBookById(book.getID_Book()); // Удаление из БД
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button deleteButton;
        TextView bookName;
        TextView bookAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.b_name);
            bookAuthor = itemView.findViewById(R.id.b_author);
            deleteButton = itemView.findViewById(R.id.delete_product);
        }
    }
}
