package com.example.todolistapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ToDoDao {
    @Insert
    void insert(ToDoItem item);

    @Query("SELECT * FROM todo_items")
    LiveData<ToDoItem> getAllItems();

    @Delete
    void delete(ToDoItem item);

}
