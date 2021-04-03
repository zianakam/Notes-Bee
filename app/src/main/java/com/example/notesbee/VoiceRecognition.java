package com.example.notesbee;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

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

        captions = new HashMap<>();
        //captions.put(KWS_SEARCH, R.string.kws_caption);

        setupTask(VoiceRecognition.this);
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

        if (searchName.equals(KWS_SEARCH))
            recognizer.startListening(searchName);

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
