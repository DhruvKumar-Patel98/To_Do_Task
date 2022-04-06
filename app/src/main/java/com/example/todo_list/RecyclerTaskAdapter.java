package com.example.todo_list;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTaskAdapter extends RecyclerView.Adapter<RecyclerTaskAdapter.ViewHolder> {
    Context context;
    List<TaskModel> arrTask;
    RecyclerTaskAdapter(Context context, List<TaskModel>arrTask){
        this.context=context;
        this.arrTask=arrTask;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.task_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.taskName.setText(arrTask.get(position).taskName);
        holder.taskInitialValue.setText(String.valueOf(arrTask.get(position).initialValue));
        holder.taskFinalValue.setText(String.valueOf(arrTask.get(position).finalValue));
        holder.pb.setMax(Integer.parseInt(String.valueOf(arrTask.get(position).finalValue)));
        holder.pb.setProgress(Integer.parseInt(String.valueOf(arrTask.get(position).initialValue)));
        holder.rlRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Dialog dialog=new Dialog(context);
               dialog.setContentView(R.layout.custom_update_layout);
                FloatingActionButton fabdel=dialog.findViewById(R.id.fabDel);
                FloatingActionButton fabMinus=dialog.findViewById(R.id.fabMinus);
                FloatingActionButton fabPlus=dialog.findViewById(R.id.fabPlus);

                fabPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppDatabase db= Room.databaseBuilder(holder.taskName.getContext(),AppDatabase.class,"Task").allowMainThreadQueries().build();
                        TaskData taskData= db.taskData();
                        int count= Integer.parseInt(holder.taskInitialValue.getText().toString());
                        String name=holder.taskName.getText().toString();
                        int finalValue=Integer.parseInt(holder.taskFinalValue.getText().toString());
                        count++;
                        if(count>=0&&count<=finalValue) {
                            taskData.update(count, name);
                            arrTask.set(position, new TaskModel(name, count, finalValue));
                            notifyItemChanged(position);
                            dialog.dismiss();
                        }
                    }
                });
                fabMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppDatabase db= Room.databaseBuilder(holder.taskName.getContext(),AppDatabase.class,"Task").allowMainThreadQueries().build();
                        TaskData taskData= db.taskData();
                        int count= Integer.parseInt(holder.taskInitialValue.getText().toString());
                        String name=holder.taskName.getText().toString();
                        int finalValue=Integer.parseInt(holder.taskFinalValue.getText().toString());
                        count--;
                        if(count>=0&&count<=finalValue) {
                            taskData.update(count, name);
                            arrTask.set(position, new TaskModel(name, count, finalValue));
                            notifyItemChanged(position);
                            dialog.dismiss();
                        }
                    }
                });
                fabdel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AppDatabase db= Room.databaseBuilder(holder.taskName.getContext(),AppDatabase.class,"Task").allowMainThreadQueries().build();
                        TaskData taskData= db.taskData();
                        taskData.delete(arrTask.get(position).taskName);
                        arrTask.remove(position);
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrTask.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView taskName,taskInitialValue,taskFinalValue;
        ProgressBar pb;
        RelativeLayout rlRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName=itemView.findViewById(R.id.taskName);
            taskInitialValue=itemView.findViewById(R.id.taskInitialValue);
            taskFinalValue=itemView.findViewById(R.id.taskFinalValue);
            pb=itemView.findViewById(R.id.progressBar);
            rlRow=itemView.findViewById(R.id.rlRow);
        }
    }
}
