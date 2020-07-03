package com.lenovo.smarttraffic.helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class HttpHelper {

    private static HttpHelper httpHelper;
    private String BASEURL="http://172.16.2.115:8088/transportservice/action/";
    private RequestQueue requestQueue;

    public HttpHelper(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }


    public static HttpHelper getInstance(Context context) {
        if (httpHelper==null) {
            httpHelper=new HttpHelper(context);
        }
        return httpHelper;
    }

    public void PostJson(String url, String body, Response.Listener<JSONObject> jsonObjectListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASEURL + url, body, jsonObjectListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}
