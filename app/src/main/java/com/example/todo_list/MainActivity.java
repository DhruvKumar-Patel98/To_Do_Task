package com.example.todo_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<TaskModel>arrTask=new ArrayList<>();
    RecyclerTaskAdapter adapter;
    RecyclerView recyclerView;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.add);

        recyclerView=findViewById(R.id.recyclerList);
        AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Task").allowMainThreadQueries().build();
        arrTask=db.taskData().getAllTask();
        recyclerView.setLayoutManager(new WrapContent(this,LinearLayoutManager.VERTICAL,false));

        adapter=new RecyclerTaskAdapter(this,arrTask);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,AddNewTask.class));
            }
        });
    }
}