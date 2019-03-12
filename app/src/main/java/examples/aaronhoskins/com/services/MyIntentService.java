package examples.aaronhoskins.com.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("");

    }
    public static final String ACTION_SAY_HELLO = " examples.aaronhoskins.com.services.MyIntentService.action_hello";
    public static final String ACTION_SAY_GOODBYE = "action_goodbye";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null) {
            if(intent.getAction().equalsIgnoreCase(ACTION_SAY_HELLO)) {
                EventBus.getDefault().post(new IntentServiceEvent("HELLO"));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if(intent.getAction().equalsIgnoreCase(ACTION_SAY_GOODBYE)) {
                EventBus.getDefault().post(new IntentServiceEvent("GOODBYE"));
            }

        }

    }
}
