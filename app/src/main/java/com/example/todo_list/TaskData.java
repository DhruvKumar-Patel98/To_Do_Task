package com.example.todo_list;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface TaskData {
    @Query("SELECT*FROM TaskModel")
    List<TaskModel>getAllTask();
    @Insert
    void insertAll(TaskModel... taskModels);
    @Query("DELETE FROM TaskModel WHERE taskName=:name")
    void delete(String name);

    @Query("UPDATE TaskModel SET initialValue=:iValue WHERE taskName = :name")
    void update(int iValue,String name);
}
