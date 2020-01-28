package to.msn.wings.preferencebasic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MyConfigActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
            .replace(android.R.id.content, new MyConfigFragment())
            .commit();
    }
}
