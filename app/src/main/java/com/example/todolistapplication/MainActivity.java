package com.example.todolistapplication;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    LinearLayout itemContainer;
    FloatingActionButton btnadd;
    AppDatabase todoDatabase;
    TextView noitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemContainer = findViewById(R.id.itemcontainer);
        btnadd = findViewById(R.id.btnadd);
        noitems = findViewById(R.id.tvnoitems);
        todoDatabase = AppDatabase.getINSTANCE(this);

    }
}