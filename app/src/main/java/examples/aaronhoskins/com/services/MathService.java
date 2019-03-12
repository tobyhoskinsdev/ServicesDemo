package examples.aaronhoskins.com.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;

public class MathService extends Service {
    private int timeToWait;
    public MathService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            try {
                timeToWait = intent.getIntExtra("time", 1);
                int passedCounter = intent.getIntExtra("counter", 0);
                double result = 1;
                Thread.sleep(timeToWait);
                for(int i = 0 ; i < passedCounter ; i++) {
                    result = result + result;
                }
                EventBus.getDefault().post(new MathResult(result));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
