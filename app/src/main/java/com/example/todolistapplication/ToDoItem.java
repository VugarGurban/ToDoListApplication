package com.example.todolistapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_items")
public class ToDoItem {
    @ColumnInfo(name = "item_id")
    @PrimaryKey(autoGenerate = true)
    int item_id;

    @ColumnInfo(name = "text")
    String text;

    public ToDoItem(String text) {
        this.text = text;
    }
}
