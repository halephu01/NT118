package com.example.lab5_4;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class PlayAudio extends AsyncTask<String, Long, Void> {
    private MediaPlayer mediaPlayer;
    private Context context;
    private Long startTime;
    private TextView tvStatus;
    private ProgressDialog pbWaiting;

    public PlayAudio(Context context, TextView tvStatus, MediaPlayer mediaPlayer) {
        this.context = context;
        this.tvStatus = tvStatus;
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
    protected Void doInBackground(String... params) {
        try{
            for(Long i = 0L; i < 3l ; i++){
                Thread.sleep(1000);
                publishProgress((Long) i);
            }
        }catch (Exception e){
            Log.e("Play",e.getMessage());
        }

        play();
        return null;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        // Update UI if needed
        tvStatus.append("\nWorking..."+values[0]);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if(pbWaiting.isShowing())
            pbWaiting.dismiss();

        tvStatus.append("\nEnd Time: "+ System.currentTimeMillis());
        tvStatus.append("\nPlay the song");


    }

    private void play() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.music);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        mediaPlayer.start();
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void startPauseTask() {
        PauseAudio pauseAudioTask = new PauseAudio(context, tvStatus, mediaPlayer);
        pauseAudioTask.execute();
    }


}
