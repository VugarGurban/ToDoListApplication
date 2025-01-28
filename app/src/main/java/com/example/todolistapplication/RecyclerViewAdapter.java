package com.example.todolistapplication;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Handler;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final List<ToDoItem> toDoList;
    private final ToDoItemListener listener;

    public RecyclerViewAdapter(List<ToDoItem> toDoList,ToDoItemListener listener){
        this.toDoList = toDoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        ToDoItem item = toDoList.get(position);
        holder.taskTextView.setText(item.text);
        holder.deleteButton.setOnClickListener(view -> {
            listener.onItemDeleted(item);
        });
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTextView;
        ImageButton deleteButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            deleteButton = itemView.findViewById(R.id.btndelete);
        }

    }

    public void updateData(List<ToDoItem> newToDoList) {
        toDoList.clear();
        toDoList.addAll(newToDoList);
        notifyDataSetChanged();
    }
}
