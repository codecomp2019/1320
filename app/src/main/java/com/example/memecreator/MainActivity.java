package com.example.memecreator;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String[] IMG_URLS = {
            "https://cdn-images-1.medium.com/max/1400/1*-NHDvi7ZldIhYUkDI3e1sQ.jpeg",
            "https://pbs.twimg.com/media/DVwvSLBWAAAYvft.jpg",
            "http://www.wpromote.com/blog/wp-content/uploads/2015/06/doge-meme.jpg",
            "https://d35w6hwqhdq0in.cloudfront.net/7a2bb6fe445ce35c5c084f9e18a8a60c.png",
            "https://d35w6hwqhdq0in.cloudfront.net/d10c9fac30f0d0fcc0360f5bd60df4e9.png",
            "https://famufsushpe.files.wordpress.com/2016/07/main-qimg-40c5c6bafdcd821c937d9f3f6e9d54f5-c.jpg"
    };

    MediaPlayer mp ;
    private Integer images[] = {R.drawable.pic1};
    private int currImage = 0;
    private ImageToText imageToText;
    private String currentURL;
    private ImageConverter imageConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variables
        Bitmap bitmapFromURL = null;
        String responseRequest = "";

        setTheme(R.style.AppTheme); // Changing theme back to default app theme
        // Adding audio cue, to let know app started
        //Welcome Audio and Double Tap options
        mp = MediaPlayer.create(getBaseContext(), R.raw.welcome_message);

        // Start Audio
        mp.start();

        // Keep track of current URL Here

        currentURL = IMG_URLS[0];


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img_meme;
        img_meme = findViewById(R.id.meme_img);

        // Async Task for Image Conversion from URL to Bitmap
        try{
            bitmapFromURL = new ImageConverter().execute(currentURL).get();
            responseRequest = new ImageToText().execute(currentURL).get();
        }catch (Exception e){

        }
        img_meme.setImageBitmap(bitmapFromURL);
        TextView text_display;
        LinearLayout linearLayout;
        // Initializer
        linearLayout = (LinearLayout) findViewById(R.id.base_layout);
        text_display = findViewById(R.id.display_text);

        // Analyze Image
        text_display.setText(responseRequest);


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

    @Override
    protected void onPause() {
        super.onPause();
        if(mp.isPlaying()){ //Must check if it's playing, otherwise it may be a NPE
            mp.pause(); //Pauses the sound
            mp.stop();
        }
    }

}