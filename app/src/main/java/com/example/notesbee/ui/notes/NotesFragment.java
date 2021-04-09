package com.example.notesbee.ui.notes;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notesbee.AddNotesActivity;
import com.example.notesbee.Note;
import com.example.notesbee.NotesbeeApplication;
import com.example.notesbee.R;
import com.example.notesbee.ui.NoteList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {
    private RecyclerView notesRecyclerView;
    private NotesAdapter notesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotesViewModel notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notes, container, false);

        notesRecyclerView = root.findViewById(R.id.notes_recycler_view);

        List<String> titles= new ArrayList<>();
        List<String> content= new ArrayList<>();
        List<Boolean> alarmSet=new ArrayList<>();
        List<Integer> index=new ArrayList<>();

        NoteList db = ((NotesbeeApplication)((Activity)getContext()).getApplication()).getDatabase();
        for (int i = 0; i < db.getNoteCount(); i++) {
            Note note = db.getNote(i);
            index.add(i);
            titles.add(note.title);
            content.add(note.memo);
            alarmSet.add(note.alarm.getTimeSet());
        }

//        titles.add("First Note Title");
//        titles.add("Secont notes Title");
//        titles.add("Second notes Content");
//        content.add("First Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt");
//        content.add("Second Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt");
//        content.add("Third Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt");
//        alarmSet.add(true);
//        alarmSet.add(false);
//        alarmSet.add(true);

        notesAdapter= new NotesAdapter(titles, content, alarmSet, index );
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesRecyclerView.setAdapter(notesAdapter);


        final FloatingActionButton addNotes = root.findViewById(R.id.add_notes_button);
        addNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNotesActivity.class);
                startActivity(intent);
            }
        });

        notesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}