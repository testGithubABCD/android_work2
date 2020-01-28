package to.msn.wings.widgetswebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_onclick(View view) {
        WebView wv = findViewById(R.id.wv);
        switch (view.getId()) {
            case R.id.btnHome:
                wv.loadUrl("https://wings.msn.to/");
                break;
            case R.id.btnEnv:
                wv.loadUrl("https://wings.msn.to/index.php/-/B-08/");
                break;
            case R.id.btnPub:
                wv.loadUrl("https://www.shuwasystem.co.jp/smp/");
                break;
            default:
                break;
        }
    }
}
