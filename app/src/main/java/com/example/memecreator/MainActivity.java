package com.example.memecreator;
import android.app.Activity;
import android.app.Application;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;

import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.KeyEvent.KEYCODE_SPACE;

public class MainActivity extends Activity {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private static final String[] IMG_URLS = {
            "https://cdn-images-1.medium.com/max/1400/1*-NHDvi7ZldIhYUkDI3e1sQ.jpeg",
            "https://pbs.twimg.com/media/DVwvSLBWAAAYvft.jpg",
            "http://www.wpromote.com/blog/wp-content/uploads/2015/06/doge-meme.jpg",
            "https://d35w6hwqhdq0in.cloudfront.net/7a2bb6fe445ce35c5c084f9e18a8a60c.png",
            "https://d35w6hwqhdq0in.cloudfront.net/d10c9fac30f0d0fcc0360f5bd60df4e9.png",
            "https://famufsushpe.files.wordpress.com/2016/07/main-qimg-40c5c6bafdcd821c937d9f3f6e9d54f5-c.jpg"
    };

    private static final String[] IMG_CAPTIONS = {"" +
            "Image Description: a person wearing a suit and tie. Caption: Interviewer: So why do you want this job? Well, I've always been really passionate about not starving to death.",
            "Image Description: a cat lying on a bed. Caption: I hate that part of the morning where I have to get out of bed.",
            "Image Description: a dog looking at the camera. Caption: wow, such meme, very marketing, many cool, amazing",
            "Image Description: a windmill in the background. Caption: Renewable energy? I'm a big fan",
            "Image Description: a person holding a sign. Caption: So you're telling me. I have to get experience before I get experience?",
            "Image Description: Edward Norton standing in front of a sign. Caption: Engineering. 'If you're not tired, you're not doing it right"

    };

    //for Playing the intro audio
    MediaPlayer mp;
    private Integer images[] = {R.drawable.img1}; // was creating a n array of images with int as pointer
    private TextView  search_result;
    TextView text_display;

    String message;
    // text To speech
    TextToSpeech textToSpeech;
    ImageView img_meme;

    //
    String currentUrl = IMG_URLS[0];
    Bitmap bitmap;

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


        // Initializer

        img_meme = findViewById(R.id.meme_img);
        text_display = findViewById(R.id.display_text);
        search_result = findViewById(R.id.searchResult);

        try{
            String caption = new ImageToText().execute(currentUrl).get();
        }catch (Exception e){

        }


        // Create an array and then compare results
        ImageButton searchButton;

        searchButton = findViewById(R.id.btnSpeak);
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

                    case MotionEvent.ACTION_DOWN:
                        v.performClick();
                        //  could open the search dialog
                        // or could use to repeat commands
                        textSpeak();
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

                    case MotionEvent.ACTION_DOWN:
                        v.performClick();
                        textSpeak();
                        //  could open the search dialog
                        // or could use to repeat commands
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


        // text to speech

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    // creating a method for data to be spoken by text anywhere they click
    public void textSpeak(){
        String data = text_display.getText().toString();
        Log.i("TTS", "button clicked: " + data);
        int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);

        if (speechStatus == TextToSpeech.ERROR)
        {
            Log.e("TTS", "Error in converting Text to Speech!");
        }
    }

    // Speech to text
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

    // using the setter and getter to display what I spoke in textbox

    public String getMsg(){
        return message;
    }
    public String setMessage(String message){
        return this.message  = message;

    }

    // getting the Value of text
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    search_result.setText(result.get(0));
                    String msg = result.get(0);
                    if(msg.contains("next")){
                        // Change to Next Image

                    }
                    else if(msg.contains("previous"))
                    { // Change to previous image}

                    }
                    else if(msg.contains("quit"))
                    {
                        // Quit Application
                    }
                    else if(msg.contains("analyze | image"))
                    {
                        // Analyze Image
                        try{
                            String caption = new ImageToText().execute(currentUrl).get();
                            text_display.setText(caption);

                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, caption, duration);
                            toast.show();

                        }catch (InterruptedException e){

                        }catch (ExecutionException e){

                        }
                    }
                    else if(msg.contains("read image"))
                    {
                        // Read Image
                        try{
                            bitmap = new ImageConverter().execute(currentUrl).get();
                            img_meme.setImageBitmap(bitmap);
                        }catch (InterruptedException e){

                        }catch (ExecutionException e){

                        }
                    }

                }
                break;
            }

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mp.isPlaying()){ //Must check if it's playing, otherwise it may be a NPE
            mp.pause(); //Pauses the sound
            mp.stop();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

}