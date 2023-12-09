package com.example.lab5_4;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PauseAudio extends AsyncTask<Void, Long, Void> {
    private MediaPlayer mediaPlayer;
    private Context context;
    private Long startTime;
    private TextView tvStatus;
    private ProgressDialog pbWaiting;

    public PauseAudio(Context context, TextView tvStatus, MediaPlayer mediaPlayer) {
        this.context = context;
        this.tvStatus = tvStatus;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //We can use UI thread here
        pbWaiting = new ProgressDialog(context);
        startTime = System.currentTimeMillis();
        tvStatus.setText("Start time: " + startTime);
        pbWaiting.setMessage(context.getString(R.string.please_wait));
        pbWaiting.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            for (Long i = 0L; i < 3L; i++) {
                Thread.sleep(1000);
                publishProgress(i);
            }
        } catch (Exception e) {
            Log.e("Pause", e.getMessage());
        }

        pause();
        return null;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        // Update UI if needed
        tvStatus.append("\nWorking..." + values[0]);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (pbWaiting.isShowing())
            pbWaiting.dismiss();

        tvStatus.append("\nEnd Time: " + System.currentTimeMillis());
        tvStatus.append("\nPause the song");
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
}
