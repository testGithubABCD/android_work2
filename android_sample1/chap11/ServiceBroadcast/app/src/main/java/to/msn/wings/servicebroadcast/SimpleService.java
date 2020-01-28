package to.msn.wings.servicebroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleService extends Service {
    private final String TAG = "SimpleService";
    private ScheduledExecutorService schedule;
    public static final String ACTION = "SimpleService Action";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(ACTION);
                i.putExtra("message", (new Date()).toString());
                sendBroadcast(i);
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        schedule.shutdown();
    }
}

