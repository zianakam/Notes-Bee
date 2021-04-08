package com.example.notesbee.ui.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesbee.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    List<String> titles, content;
    List<Boolean> alarmSet;

    public NotesAdapter(List<String> title, List<String> content, List<Boolean> alarmSet){
        this.titles=title;
        this.content=content;
        this.alarmSet=alarmSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.noteTitle.setText(titles.get(position));
        holder.noteContent.setText(content.get(position));
        if(alarmSet.get(position))
            holder.alarmSetIcon.setBackgroundResource(R.drawable.bell_icon);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "The cardview is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent;
        ImageButton alarmSetIcon;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle= itemView.findViewById(R.id.carview_title);
            noteContent=itemView.findViewById(R.id.cardview_content);
            alarmSetIcon=itemView.findViewById(R.id.cardview_alarm_btn);
            view= itemView;
        }
    }
}
