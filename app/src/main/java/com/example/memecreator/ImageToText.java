package com.example.memecreator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
// </editor-fold>

public class ImageToText {

    private String imageToAnalyze;
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    public ImageToText(String imageToAnalyze){
        this.imageToAnalyze = imageToAnalyze;
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
    private static final String sendImageToAnalyzeImage =
            "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze";

    private static final String sendImageToAnalyzeText =
            "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";

    public String analyzeImage() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String caption = "";

        try {
            URIBuilder builder = new URIBuilder(sendImageToAnalyzeImage);

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
                    new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);

                if (json.has("description") && json.getJSONObject("description").has("captions")) {

                    JSONObject jsonCaption = json.getJSONObject("description").getJSONArray("captions").getJSONObject(0);

                    if (jsonCaption.has("text") && jsonCaption.has("confidence")) {

                        caption = "Caption: " + jsonCaption.getString("text") +
                                " (confidence: " + jsonCaption.getDouble("confidence") + ").";
                    }
                }

                System.out.println(caption);
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }

        return caption;
    }

    public String analyzeText(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String caption = "";

        try {
            URIBuilder builder = new URIBuilder(sendImageToAnalyzeText);

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
                    new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);

                if (json.has("description") && json.getJSONObject("description").has("captions")) {

                    JSONObject jsonCaption = json.getJSONObject("description").getJSONArray("captions").getJSONObject(0);

                    if (jsonCaption.has("text") && jsonCaption.has("confidence")) {

                        caption = "Caption: " + jsonCaption.getString("text") +
                                " (confidence: " + jsonCaption.getDouble("confidence") + ").";
                    }
                }

                System.out.println(caption);
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }

        return caption;
    }

}
