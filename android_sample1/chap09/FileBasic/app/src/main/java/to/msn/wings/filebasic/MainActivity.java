package to.msn.wings.filebasic;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {
    EditText txtMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringBuilder str = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
            openFileInput("memo.dat")))){
            String line;
            while ((line = reader.readLine()) != null) {
                str.append(line);
                str.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        txtMemo = findViewById(R.id.txtMemo);
        txtMemo.setText(str.toString());
    }

    public void btnSave_onClick(View view) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
            openFileOutput("memo.dat", Context.MODE_PRIVATE)))){
            writer.write(txtMemo.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
