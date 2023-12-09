package com.example.lab5_4;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_play, btn_pause, btn_stop;
    private TextView tvStatus;
    private PlayAudio playaudio;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();

        // Init playaudio
        playaudio = new PlayAudio(MainActivity.this,tvStatus, mediaPlayer);

        // Handle onClickListenner
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playaudio.execute();
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playaudio.startPauseTask();
            }
        });

    }

    private void findViewByIds(){
        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        tvStatus = findViewById(R.id.tv_status);
    }

}
