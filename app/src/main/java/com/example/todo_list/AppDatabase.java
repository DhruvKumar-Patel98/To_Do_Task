package com.example.todo_list;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {TaskModel.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskData taskData();

}
