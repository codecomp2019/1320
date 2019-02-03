package com.example.memecreator;


import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    MediaPlayer mp ;
    private Integer images[] = {R.drawable.pic1};
    private int currImage = 0;
    private ImageToText imageToText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme); // Changing theme back to default app theme
        // Adding audio cue, to let know app started
        //Welcome Audio and Double Tap options
        mp = MediaPlayer.create(getBaseContext(), R.raw.welcome_message);

        // Start Audio
        mp.start();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img_meme;

        final TextView text_display;
        LinearLayout linearLayout;
        // Initializer
        img_meme = findViewById(R.id.meme_img);
        linearLayout = (LinearLayout) findViewById(R.id.base_layout);
        text_display = findViewById(R.id.display_text);

        //setInitialImage();
        switchImage();


        // experimenting touch
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    /* case MotionEvent.ACTION_DOWN:
                        v.performClick();
                        Toast.makeText(getApplicationContext(),"ACTION DOWN",Toast.LENGTH_SHORT).show();
                        break; */
                    case MotionEvent.ACTION_DOWN:
                        v.performClick();
                        Toast.makeText(getBaseContext(), "ACTION UP", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        text_display.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    /* case MotionEvent.ACTION_DOWN:
                        v.performClick();
                        Toast.makeText(getApplicationContext(),"ACTION DOWN",Toast.LENGTH_SHORT).show();
                        break; */
                    case MotionEvent.ACTION_DOWN:
                        v.performClick();
                        Toast.makeText(getBaseContext(), "ACTION UP", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void switchImage(){

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mp.isPlaying()){ //Must check if it's playing, otherwise it may be a NPE
            mp.pause(); //Pauses the sound
            mp.stop();
        }
    }

}