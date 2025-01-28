package com.example.todolistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ToDoItemListener{
    FloatingActionButton fabadd;
    AppDatabase todoDatabase;
    TextView noitems;
    RecyclerView recyclerView;
    List<ToDoItem> toDoItemList = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        noitems = findViewById(R.id.tvnoitems);
        fabadd = findViewById(R.id.fabadd);
        todoDatabase = AppDatabase.getINSTANCE(this);
        adapter = new RecyclerViewAdapter(toDoItemList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter);

        loaddata();


        fabadd.setOnClickListener(view -> {
            Intent intent = new Intent(this, activity_create.class);
            startActivity(intent);
        });


    }

    private void loaddata(){
        new Thread(()-> {
            toDoItemList.clear();
            toDoItemList = (todoDatabase.todoDao().getAllItems());
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (toDoItemList.size() >= 0) {
                        adapter.updateData(toDoItemList);
                        noitems.setVisibility(toDoItemList.isEmpty() ? View.VISIBLE : View.GONE);
                    }
                }
            });
        }).start();

    }


    @Override
    public void onItemDeleted(ToDoItem item) {
        new Thread(()->{
            todoDatabase.todoDao().delete(item);
            loaddata();
        }).start();
    }
}