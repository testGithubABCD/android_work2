package to.msn.wings.networkpost;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AsyncNetworkTask extends AsyncTask<String, Integer, String> {
    private WeakReference<TextView> txtResult;
    private WeakReference<ProgressBar> progress;

    AsyncNetworkTask(Context context) {
        super();
        MainActivity activity = (MainActivity)context;
        txtResult = new WeakReference<>((TextView)activity.findViewById(R.id.txtResult));
        progress = new WeakReference<>((ProgressBar)activity.findViewById(R.id.progress));
    }

    @Override
    protected String doInBackground(String... params) {
        publishProgress(30);
        SystemClock.sleep(3000);
        publishProgress(60);
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.print(params[1]);
            ps.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        publishProgress(100);
        return builder.toString();
    }

    @Override
    protected void onPreExecute() {
        progress.get().setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progress.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        txtResult.get().setText(result);
        progress.get().setVisibility(ProgressBar.GONE);
    }

    @Override
    protected void onCancelled() {
        txtResult.get().setText("キャンセルされました。");
        progress.get().setVisibility(ProgressBar.GONE);
    }

}
