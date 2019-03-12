package examples.aaronhoskins.com.services;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    TextView tvDisplayOne;
    TextView tvDisplayTwo;
    TextView tvDisplayThree;
    int controlSwitch = 0;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind views
        tvDisplayOne = findViewById(R.id.tvDisplayOne);
        tvDisplayTwo = findViewById(R.id.tvDisplayTwo);
        tvDisplayThree = findViewById(R.id.tvDisplayThree);


//        Intent serviceIntent = new Intent(this, MathService.class);
//        serviceIntent.putExtra("counter", 5);
//        serviceIntent.putExtra("time", (3000));
//        //Starting Background Service
//        startService(serviceIntent);
//        //Starting a foreground service
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(serviceIntent);
//        }
//
//        //starting intent services


        Intent helloIntent = new Intent(this, MyIntentService.class);
        helloIntent.setAction(MyIntentService.ACTION_SAY_HELLO);
        startService(helloIntent);

        Intent goodbyeIntent = new Intent(this, MyIntentService.class);
        goodbyeIntent.setAction(MyIntentService.ACTION_SAY_GOODBYE);
        startService(goodbyeIntent);


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void intentServiceEvent(IntentServiceEvent event) {
        tvDisplayThree.setText(event.getMessage());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resultEvent(MathResult mathResult) {
        switch (controlSwitch) {
            case 0:
                tvDisplayOne.setText(String.valueOf(mathResult.getResult()));
                break;
            case 1:
                tvDisplayTwo.setText(String.valueOf(mathResult.getResult()));
                break;
            case 2:
                //tvDisplayThree.setText(String.valueOf(mathResult.getResult()));
                break;
        }
        controlSwitch++;
    }



    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyBoundService.MyBinder binder = (MyBoundService.MyBinder)service;
        binder.getService();
        binder.getService().startCount();
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isBound = false;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBindToService:
                Intent boundServiceIntent = new Intent(this, MyBoundService.class);
                bindService(boundServiceIntent, this, Context.BIND_AUTO_CREATE);

                break;

            case R.id.btnUnBindFromService:
                if(isBound) {
                    unbindService(this);
                }
                break;
        }
    }
}
