package com.example.todo_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewTask extends AppCompatActivity {
    EditText userTaskName,userInitialValue,userFinaleVale;
    Button saveTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        userTaskName=findViewById(R.id.userTaskName);
        userInitialValue=findViewById(R.id.userInitalvale);
        userFinaleVale=findViewById(R.id.userFinalVale);
        saveTask=findViewById(R.id.saveTask);
        AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Task").allowMainThreadQueries().build();
        saveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "";
                int initialValue = 0, finalValue = 0;
                if (!userTaskName.getText().toString().equals("")) {
                    name = userTaskName.getText().toString();
                    initialValue = Integer.parseInt(userInitialValue.getText().toString());
                    finalValue = Integer.parseInt(userFinaleVale.getText().toString());
                    if(initialValue<0||finalValue<0){
                        Toast.makeText(AddNewTask.this,"Initial value or final value can't be negative",Toast.LENGTH_LONG).show();

                    }
                    if(initialValue>finalValue){
                        Toast.makeText(AddNewTask.this,"Initial value can't be greater than final value",Toast.LENGTH_LONG).show();
                    }
                    else {
                        db.taskData().insertAll(new TaskModel(name, initialValue, finalValue));
                        Intent intent = new Intent(AddNewTask.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                }
            }
        });

    }
}
