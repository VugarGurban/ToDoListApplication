package com.example.todolistapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDoItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ToDoDao todoDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getINSTANCE(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "todo.db")
                    .build();
        }
        return INSTANCE;
    }
}
