package to.msn.wings.soundbasic;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    Button btnPlay;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        mp = MediaPlayer.create(this, R.raw.sound);
        mp.setOnCompletionListener(
            new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    btnPlay.setText("再生");
                }
            }
        );
    }

    public void btnPlay_onClick(View view) {
        if (!mp.isPlaying()) {
            mp.start();
            btnPlay.setText("停止");
        } else {
            try {
                mp.stop();
                mp.prepare();
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            btnPlay.setText("再生");
        }
    }
}
