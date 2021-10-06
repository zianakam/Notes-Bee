package com.example.notesbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import jp.wasabeef.richeditor.RichEditor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {
    private EditText title;
    private RichEditor notesContent;
    //private String dateTime;
    //private Calendar calendar;
    //private SimpleDateFormat simpleDateFormat;
    private Note note; // Local note used to create alarms and build the save/load with

    private SpeechRecognizer speechRecognizer;
    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        title=findViewById(R.id.notesTitle);
        ImageButton savenotes= findViewById(R.id.save_note_btn);
        savenotes.setOnClickListener(view -> addDataToDatabase());

        // Pull note from the database
        note = ((NotesbeeApplication)getApplication()).getDatabase().getNote(((NotesbeeApplication)getApplication()).getSelectedNoteIndex());

        // Save data to the database on clicking the save button
        findViewById(R.id.save_note_btn).setOnClickListener(view -> addDataToDatabase());

        // Set a reminder for the note
        findViewById(R.id.reminder_btn).setOnClickListener(this::setAlarm);

        // On clicked starts voice-to-text
        findViewById(R.id.vtt_btn).setOnClickListener(this::startVoiceToText);

        //Or if long pressed start voice-to_text
        //findViewById(R.id.vtt_btn).setOnLongClickListener(view -> startVoiceToText(view));


        notesContent = findViewById(R.id.notesContent);

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
     * Flushes all currently stored text and such to the note
     */
    private void flushNote() {
        note.title = title.getText().toString();
        note.memo = notesContent.getHtml();
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
     * Adds the message currently written to the database, called whenever something would
     * cause this message screen to close.
     */
    private void addDataToDatabase(){
        flushNote();
        ((NotesbeeApplication)getApplication()).getDatabase().flush(getString(R.string.database_file));
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * Begins voice recognition, this is called when the microphone icon is pressed.
     * @param view
     */
    public void startVoiceToText(View view) {
        requestUserPermission();

        if(SpeechRecognizer.isRecognitionAvailable(this)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        } else {
            setVoiceCaptionText("Error: No speech recognizer service available");
        }

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS , 1000000);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                setVoiceCaptionText("Ready for speech");
            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
                speechRecognizer.stopListening();
                removeVoiceToTextCaption();
            }

            @Override
            public void onError(int i) {
                setVoiceCaptionText("Error occurred(" + i + ")"); //if error code 9 allow permissions for google
                removeVoiceToTextCaption();
            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                notesContent.setHtml(data.get(0)); //update html?
            }

            @Override
            public void onPartialResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                notesContent.setHtml(data.get(0));
            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        setVoiceCaptionText("Listening...");
        speechRecognizer.startListening(speechRecognizerIntent);

    }

    /**
     * Helper method to update voice-to-text caption in notes from a different class
     * @param text the new text to update the caption with
     */
    public void setVoiceCaptionText(String text) {
                TextView textView = (TextView)findViewById(R.id.caption_text);
                textView.setText(text);
    }

    /**
     * Requests user audio permissions
     */
    private void requestUserPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) { //if permission never granted, request
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    PERMISSIONS_REQUEST_RECORD_AUDIO);
        }
    }

    /**
     * Catches and processes permission results
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, //only applies if user responds to permission query
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) { //catch request
            case PERMISSIONS_REQUEST_RECORD_AUDIO:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) { //if request granted
                } else {
                    finish();
                }
        }
    }

    /**
     * Removes text caption on a timer
     */
    public void removeVoiceToTextCaption () {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                setVoiceCaptionText(" ");
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }


}