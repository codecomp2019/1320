package com.example.memecreator;


import android.app.Activity;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;

import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.view.KeyEvent.ACTION_DOWN;

public class MainActivity extends Activity   {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    MediaPlayer mp ;
    private Integer images[] = {R.drawable.pic1};
    private int currImage = 0;

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
        SearchView search;
        final TextView text_display,search_result;
        RelativeLayout linearLayout;
        // Initializer
        img_meme = findViewById(R.id.meme_img);

        text_display = findViewById(R.id.display_text);
        search_result = findViewById(R.id.searchResult);

        // Create an array and then compare results
        ImageButton searchButton;
        searchButton = (ImageButton) findViewById(R.id.btnSpeak);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        // experimenting touch
        img_meme.setOnTouchListener(new View.OnTouchListener() {
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


    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
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
