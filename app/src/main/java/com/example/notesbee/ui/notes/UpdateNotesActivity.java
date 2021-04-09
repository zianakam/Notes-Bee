package com.example.notesbee.ui.notes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.notesbee.MainActivity;
import com.example.notesbee.Note;
import com.example.notesbee.NotesbeeApplication;
import com.example.notesbee.R;

import androidx.appcompat.app.AppCompatActivity;
import jp.wasabeef.richeditor.RichEditor;

public class UpdateNotesActivity extends AppCompatActivity {
    private EditText title;
    private RichEditor notesContent;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        title=findViewById(R.id.notesTitle);
        notesContent= findViewById(R.id.notesContent);

        Intent data= getIntent();
        title.setText(data.getStringExtra("title"));
        notesContent.setHtml(data.getStringExtra("content"));
        Boolean alarmSet=data.getBooleanExtra("alarmSet",false);
        Integer index=data.getIntExtra("index",0);

        // Pull note from the database
        note = ((NotesbeeApplication)getApplication()).getDatabase().getNote(((NotesbeeApplication)getApplication()).getSelectedNoteIndex());

        // Save data to the database on clicking the save button
        findViewById(R.id.save_note_btn).setOnClickListener(view -> updateDataToDatabase(index));

        // Set a reminder for the note
        findViewById(R.id.reminder_btn).setOnClickListener(this::setAlarm);

        // On clicked starts voice-to-text
        findViewById(R.id.vtt_btn).setOnClickListener(this::startVoiceToText);

        //Or if long pressed start voice-to_text
        //findViewById(R.id.vtt_btn).setOnLongClickListener(view -> startVoiceToText(view));








        findViewById(R.id.action_undo).setOnClickListener(view -> notesContent.undo());

        findViewById(R.id.action_redo).setOnClickListener(view -> notesContent.redo());

        findViewById(R.id.action_bold).setOnClickListener(view -> notesContent.setBold());

        findViewById(R.id.action_italic).setOnClickListener(view -> notesContent.setItalic());

        findViewById(R.id.action_strikethrough).setOnClickListener(view -> notesContent.setStrikeThrough());

        findViewById(R.id.action_underline).setOnClickListener(view -> notesContent.setUnderline());

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private Boolean isChanged=true;
            @Override
            public void onClick(View view) {
                notesContent.setTextColor(isChanged ? Color.BLACK: Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_insert_bullets).setOnClickListener(v -> notesContent.setBullets());

        findViewById(R.id.action_insert_numbers).setOnClickListener(v -> notesContent.setNumbers());

        findViewById(R.id.action_insert_checkbox).setOnClickListener(v -> notesContent.insertTodo());


    }
    /**
     * The function that is called when the "Alarm" button is pressed.
     * It will launch a dialogue to select a time and schedule an alarm for that time.
     */
    public void setAlarm(View view) {
        // Must update note contents before setting alarm so the contents are visible to the future notification
        flushNote();
        note.createAlarm(view.getContext());
    }

    /**
     * Begins voice recognition, this is called when the microphone icon is pressed.
     */
    public void startVoiceToText(View view) {

    }

    /**
     * Adds the message currently written to the database, called whenever something would
     * cause this message screen to close.
     */
    private void updateDataToDatabase(Integer index){
        flushNote();
        //((NotesbeeApplication)getApplication()).getDatabase().flush(getString(R.string.database_file));
        ((NotesbeeApplication)getApplication()).setSelectedNoteIndex(index);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * Flushes all currently stored text and such to the note
     */
    private void flushNote() {
        note.title = title.getText().toString();
        note.memo = notesContent.getHtml();
    }
}
