package to.msn.wings.servicetimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartClick(View view) {
        Intent i = new Intent(this, to.msn.wings.servicetimer.SimpleService.class);
        startService(i);
    }

    public void onStopClick(View view) {
        Intent i = new Intent(this, to.msn.wings.servicetimer.SimpleService.class);
        stopService(i);
    }
}

