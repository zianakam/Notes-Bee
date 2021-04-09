package com.example.notesbee.ui.notes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesbee.MainActivity;
import com.example.notesbee.Note;
import com.example.notesbee.NotesbeeApplication;
import com.example.notesbee.R;
import com.example.notesbee.ui.NoteList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.richeditor.RichEditor;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    List<String> titles, content;
    List<Boolean> alarmSet;
    List<Integer> index;

    public NotesAdapter(List<String> title, List<String> content, List<Boolean> alarmSet, List<Integer> index){
        this.titles=title;
        this.content=content;
        this.alarmSet=alarmSet;
        this.index=index;
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
        holder.noteContent.setHtml(content.get(position));
        if(alarmSet.get(position))
            holder.alarmSetIcon.setBackgroundResource(R.drawable.bell_icon);

        holder.deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code for deleting data for the passed index
                Integer i= index.get(position);    //to get the passed index


                Intent intent=new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), UpdateNotesActivity.class);
                intent.putExtra("title",titles.get(position));
                intent.putExtra("content", content.get(position));
                intent.putExtra("alarmSet", alarmSet.get(position));
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "The cardview is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle;
        RichEditor noteContent;
        ImageButton alarmSetIcon;
        ImageButton deleteNote;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle= itemView.findViewById(R.id.carview_title);
            noteContent=itemView.findViewById(R.id.cardview_content);
            alarmSetIcon=itemView.findViewById(R.id.cardview_alarm_btn);
            deleteNote=itemView.findViewById(R.id.cardview_delete_btn);
            view= itemView;
        }
    }
}
