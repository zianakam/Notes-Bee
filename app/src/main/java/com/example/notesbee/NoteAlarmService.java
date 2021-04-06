package com.example.notesbee;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread. Will display a notification for a note
 * with a bound alarm.
 */
public class NoteAlarmService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_NOTIFY = "com.example.notesbee.action.NOTIFY";

    public static final String EXTRA_BODY = "com.example.notesbee.extra.BODY";

    public NoteAlarmService() {
        super("NoteAlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_NOTIFY.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_BODY);
                handleActionNotify(param1);
            }
        }
    }

    /**
     * Handle action notify in the provided background thread with the provided
     * parameters.
     */
    private void handleActionNotify(String body) {
        // TODO: Handle notification
        throw new UnsupportedOperationException("Not yet implemented");
    }
}