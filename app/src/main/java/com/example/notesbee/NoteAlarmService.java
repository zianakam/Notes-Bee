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

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_NOTIFY = "com.example.notesbee.action.NOTIFY";

    // TODO: Rename parameters
    private static final String EXTRA_BODY = "com.example.notesbee.extra.BODY";

    public NoteAlarmService() {
        super("NoteAlarmService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, NoteAlarmService.class);
        intent.setAction(ACTION_NOTIFY);
        intent.putExtra(EXTRA_BODY, param1);
        context.startService(intent);
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
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionNotify(String body) {
        // TODO: Handle notification
        throw new UnsupportedOperationException("Not yet implemented");
    }
}