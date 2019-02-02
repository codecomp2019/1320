package com.example.memecreator;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme); // Changing theme back to default app theme
        // Adding audio cue, to let know app started
        //Welcome Audio and Double Tap options
       mp = MediaPlayer.create(getBaseContext(),R.raw.welcome_message);

        // Start Audio
        mp.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mp.isPlaying()){ //Must check if it's playing, otherwise it may be a NPE
            mp.pause(); //Pauses the sound
            mp.stop();
        }
    }

}
