package com.example.notesbee;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;

import static android.widget.Toast.makeText;
import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;
import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;

public class VoiceRecognition extends Activity implements
        RecognitionListener {

    private static final String KWS_SEARCH = "";
    private static final String FORECAST_SEARCH = "";
    private static final String DIGITS_SEARCH = "";
    private static final String PHONE_SEARCH = "";
    private static final String MENU_SEARCH = "";

    //Activates menu
    private static final String KEYPHRASE = "";

    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;

    private SpeechRecognizer recognizer;
    private HashMap<String, Integer> captions;
    private WeakReference<VoiceRecognition> activityReference;

    private void setupRecognizer(File assetsDir) throws IOException {

        recognizer = defaultSetup()
                .setAcousticModel(new File(assetsDir, "en-us-ptm"))
                .setDictionary(new File(assetsDir, "cmudict-en-us.dict"))
                .getRecognizer();
        recognizer.addListener(this);

        recognizer.addKeyphraseSearch(KWS_SEARCH, KEYPHRASE);

        File menuGrammar = new File(assetsDir, "menu.gram");
        recognizer.addGrammarSearch(MENU_SEARCH, menuGrammar);

        File digitsGrammar = new File(assetsDir, "digits.gram");
        recognizer.addGrammarSearch(DIGITS_SEARCH, digitsGrammar);

        File languageModel = new File(assetsDir, "weather.dmp");
        recognizer.addNgramSearch(FORECAST_SEARCH, languageModel);

        File phoneticModel = new File(assetsDir, "en-phone.dmp");
        recognizer.addAllphoneSearch(PHONE_SEARCH, phoneticModel);
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        String text = "Updated";
        AddNotesActivity.getInstanceActivity().setText(text);

        requestUserPermission();
        //setupTask(this);
    }

    private void requestUserPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) { //if permission never granted, request
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    PERMISSIONS_REQUEST_RECORD_AUDIO);
        } else { //else continue
            finish();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, //only applies if user responds to permission query
                                           int[] grantResults) {
        switch (requestCode) { //catch request
            case PERMISSIONS_REQUEST_RECORD_AUDIO:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) { //if request granted
                    setupTask(this);
                }  else { //else need to return to addnotes (setup onDestroy)
                    finish();
                }
        }
    }


    public void setupTask(VoiceRecognition activity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        this.activityReference = new WeakReference<>(activity);

        executor.execute(() -> {
            try {
                Assets assets = new Assets(activityReference.get());
                File assetDir = assets.syncAssets();
                activityReference.get().setupRecognizer(assetDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                activityReference.get().switchSearch(KWS_SEARCH);
            });
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //TO DO:
    }

    private void switchSearch(String searchName) {
        recognizer.stop();

        //if (searchName.equals(KWS_SEARCH))
        //recognizer.startListening(searchName, 10000);
        //String caption = getResources().getString(captions.get(searchName));
        //((TextView) findViewById(R.id.caption_text)).setText(caption);
    }

    @Override
    public void onResult(Hypothesis hypothesis) {
        //TO DO:
    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        //TO DO:
    }

    @Override
    public void onBeginningOfSpeech() {
        //TO DO:
    }

    @Override
    public void onEndOfSpeech() {
        //TO DO:
    }

    @Override
    public void onTimeout() {
        //TO DO:
    }

    @Override
    public void onError(Exception error) {
        //TO DO:
    }

}
