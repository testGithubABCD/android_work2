package to.msn.wings.drawableanimecode;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable img1 = Objects.requireNonNull(ContextCompat.getDrawable(
                this, R.drawable.anim1));
        Drawable img2 = Objects.requireNonNull(ContextCompat.getDrawable(
                this, R.drawable.anim2));
        Drawable img3 = Objects.requireNonNull(ContextCompat.getDrawable(
                this, R.drawable.anim3));
        Drawable img4 = Objects.requireNonNull(ContextCompat.getDrawable(
                this, R.drawable.anim4));

        AnimationDrawable anim = new AnimationDrawable();
        anim.addFrame(img1, 500);
        anim.addFrame(img2, 500);
        anim.addFrame(img3, 500);
        anim.addFrame(img4, 500);
        anim.setOneShot(false);

        ImageView img = findViewById(R.id.img);
        img.setBackground(anim);
        anim.start();
    }
}
