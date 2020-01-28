package to.msn.wings.servicestatus;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleService extends Service {
    private static final String TAG = "SimpleService";
    private static final int NOTIFY_ID = 0;
    private NotificationManager manager;
    private NotificationChannel channel;
    private ScheduledExecutorService schedule;

    @Override
    public void onCreate() {
        super.onCreate();
        channel = new NotificationChannel(
                "service_status", "サービス状況",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        channel.setVibrationPattern(new long[]{1000, 500, 1000, 500, 2000, 500});
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notif = new NotificationCompat.Builder(this, "service_status")
            .setContentTitle("SimpleService")
            .setContentText("サービスは起動中です。")
            .setSmallIcon(R.drawable.wings_logo)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(
                    PendingIntent.getActivity(this, MainActivity.ACTIVITY_ID,
                            new Intent(this, MainActivity.class),
                            PendingIntent.FLAG_CANCEL_CURRENT)
            )
            .build();

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(NOTIFY_ID, notif);

        schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onStartCommand");
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
        manager.cancel(NOTIFY_ID);
        Log.i(TAG, "onDestroy");
        schedule.shutdown();
    }
}

