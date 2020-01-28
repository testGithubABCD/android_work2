package to.msn.wings.intentdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent i = getIntent();
        String txtName = i.getStringExtra("txtName");
        Toast.makeText(this,
                String.format("こんにちは、%sさん！", txtName),
                Toast.LENGTH_SHORT).show();
    }

    public void btnBack_onClick(View v) {
        finish();
    }
}



