/** package com.example.memecreator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import android.net.Uri;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
// </editor-fold>

public class ImageToText extends AsyncTask<String, Void, String>{

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     *

    public ImageToText(){

    }

    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace <Subscription Key> with your valid subscription key.
    private static final String subscriptionKey = "5096060277cd47dcb1eee6f818c267e7";

    // You must use the same Azure region in your REST API method as you used to
    // get your subscription keys. For example, if you got your subscription keys
    // from the West US region, replace "westcentralus" in the URL
    // below with "westus".
    //
    // Free trial subscription keys are generated in the "westus" region.
    // If you use a free trial subscription key, you shouldn't need to change
    // this region.
    private static final String sendImageToAnalyze =
            "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze?visualFeatures=Description&language=en";

    private static final String sendImageToAnalyzeText =
            "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";


    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(sendImageToAnalyze);

            // Request parameters. All of them are optional.
            builder.setParameter("visualFeatures", "Categories,Description,Color");
            builder.setParameter("language", "en");

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            StringEntity requestEntity =
                    new StringEntity("{\"url\":\"" + url + "\"}");
            request.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);

                String caption = "";

                if (json.has("description") && json.getJSONObject("description").has("captions")) {

                    JSONObject jsonCaption = json.getJSONObject("description").getJSONArray("captions").getJSONObject(0);

                    if (jsonCaption.has("text") && jsonCaption.has("confidence")) {

                        caption = "Caption: " + jsonCaption.getString("text") +
                                " (confidence: " + jsonCaption.getDouble("confidence") + ").";
                    }
                }

                return caption;
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
 **/