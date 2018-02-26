package com.mygdx.minerbob.helpers.Internet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Алексей on 19.02.2018.
 */

public class PostRequest {
    public static void executeInsert(final String login, final GameWorld gameWorld, int score) {
        Map parameters = new HashMap();
        parameters.put("login", login);
        parameters.put("score", score + "");
        final HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).
                url("https://hushquiet.000webhostapp.com/insert.php").build();
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                gameWorld.assetLoader.addId(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        });
    }

    public static void executeSelect(final String id, final GameWorld gameWorld) {
        Map parameters = new HashMap();
        parameters.put("id", id);
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).
                url("https://hushquiet.000webhostapp.com/").build();
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                gameWorld.getLeaderBoardForm().generateString(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        });
    }

    public static void executeUpdate(final String id, String score, final GameWorld gameWorld) {
        Map parameters = new HashMap();
        parameters.put("id", id);
        parameters.put("score", score);
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).
                url("https://hushquiet.000webhostapp.com/update.php").build();
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        });
    }

    public static void executeStatus(final AssetLoader assetLoader) {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        final Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).
                url("https://hushquiet.000webhostapp.com/status.php").build();

        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                if (httpResponse.getResultAsString().equals("ok")) {
                    assetLoader.isInternet = true;
                }
//                assetLoader.checkInternet();
            }

            @Override
            public void failed(Throwable t) {
                assetLoader.isInternet = false;
  //              assetLoader.checkInternet();
            }

            @Override
            public void cancelled() {
                assetLoader.isInternet = false;
    //            assetLoader.checkInternet();
            }
        });
    }
}
