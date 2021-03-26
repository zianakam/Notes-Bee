package com.example.notesbee;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;

import static android.widget.Toast.makeText;
import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;

public class VoiceRecognition extends Activity implements
        RecognitionListener {

    private static final String KWS_SEARCH = "";
    private static final String FORECAST_SEARCH = "";
    private static final String DIGITS_SEARCH = "";
    private static final String PHONE_SEARCH = "";
    private static final String MENU_SEARCH = "";

    private static final String KEYPHRASE = "";

    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;

    private SpeechRecognizer recognizer;
    private HashMap<String, Integer> captions;

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
        //TO DO:
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //TO DO:
    }

    private void switchSearch(String searchName) {
        //TO DO:
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
