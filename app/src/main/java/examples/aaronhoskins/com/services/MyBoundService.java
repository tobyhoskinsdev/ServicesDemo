package examples.aaronhoskins.com.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;

public class MyBoundService extends Service {
    IBinder binder = new MyBinder();
    String sendCountString;
    int i = 0;
    public MyBoundService() {
        sendCountString = "Count";
    }

    public String setCurrentCount(int i) {
        return sendCountString + i;
    }

    public void startCount(){
        for(int i = 0 ; i < 15 ; i++) {
            sendCountString = setCurrentCount(i);
            EventBus.getDefault().post(new IntentServiceEvent(sendCountString));
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyBinder extends Binder {
        public MyBoundService getService() { return MyBoundService.this;}

    }
}
