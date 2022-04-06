package com.example.todo_list;

import android.widget.ProgressBar;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskModel {
    @PrimaryKey(autoGenerate = true)
             int id;
    @ColumnInfo(name="taskName")
             String taskName;
    @ColumnInfo(name="finalValue")
             int finalValue;
    @ColumnInfo(name="initialValue")
            int initialValue;

    public TaskModel(String taskName,int initialValue,int finalValue){
        this.taskName=taskName;
        this.initialValue=initialValue;
        this.finalValue=finalValue;

    }


}
