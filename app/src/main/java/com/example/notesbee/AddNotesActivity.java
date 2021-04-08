package com.example.notesbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNotesActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private String dateTime;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private boolean startedVoiceRecognition = false;
    private static WeakReference<AddNotesActivity> weakActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        title=findViewById(R.id.notesTitle);
        content=findViewById(R.id.notesContent);
        weakActivity = new WeakReference<>(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        addDataToDatabase();
    }

    @Override
    protected void onStop() {
        super.onStop();
        addDataToDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addDataToDatabase();
    }

    private void addDataToDatabase(){
        // We will save all data in a note class then serialize it
        Note note = new Note();
        note.title = title.getText().toString();
        note.memo = content.getText().toString();
        String serial = note.serialize();

        // TODO: Serialize and add to database
        // get time and date when the note is created
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss aaa z");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();

        // save all three variables to the database
    }

    public void startVoiceRecognition (View view) {
        Intent intent = new Intent(this, VoiceRecognition.class);
        startedVoiceRecognition = true;
        startActivity(intent);
    }

    public static AddNotesActivity getInstanceActivity() {
        return weakActivity.get();
    }

    public void setText(String text) {
        TextView textView = (TextView)findViewById(R.id.caption_text);
        textView.setText(text);
    }


}