package com.example.memecreator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.URL;

public class ImageConverter extends AsyncTask<String, Void, Bitmap> {

    private Bitmap bmp;

    public ImageConverter(){

    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        URL url;
        try{
            url = new URL(strings[0]);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch (Exception e){

        }

        return bmp;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
