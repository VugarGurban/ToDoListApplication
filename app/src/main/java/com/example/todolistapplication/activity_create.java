package com.example.todolistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_create extends AppCompatActivity {

    EditText etenteritem;
    Button btnadd;
    AppDatabase todoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        etenteritem = findViewById(R.id.etenteritem);
        btnadd = findViewById(R.id.btnadd);
        todoDatabase = AppDatabase.getINSTANCE(this);

        btnadd.setOnClickListener(view -> {
            String itemText = etenteritem.getText().toString();

            if (!itemText.isEmpty()) {
                new Thread(()->{
                    ToDoItem item = new ToDoItem(itemText);
                    todoDatabase.todoDao().insert(item);
                    Intent intent = new Intent(activity_create.this, MainActivity.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);

                            finish();
                        }
                    });

                }).start();

            }else{
                Toast.makeText(this, "Task cannot be empty!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}