package sse.bupt.cn.translator.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import sse.bupt.cn.translator.network.handler.StringFailHandler;
import sse.bupt.cn.translator.network.handler.StringRequestHandler;
import sse.bupt.cn.translator.network.handler.StringSuccessHandler;

public class StringRequestWrapper {
    private static final String TAG = "StringRequestWrapper";

    private RequestQueue queue;

    public StringRequestWrapper(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    public void sendGetRequest(String url, StringRequestHandler handler) {
        StringSuccessHandler successHandler = new StringSuccessHandler(handler);
        StringFailHandler failHandler = new StringFailHandler(handler);
        Log.i(TAG, "---GET---start request " + url + "---");
        StringRequest request = new StringRequest(url, successHandler, failHandler);

        queue.add(request);
    }

    public void sendGetRequest(String url, StringRequestHandler handler, final Map<String, String> body) {
        StringSuccessHandler successHandler = new StringSuccessHandler(handler);
        StringFailHandler failHandler = new StringFailHandler(handler);

        Log.i(TAG, "---POST---start request " + url + "---");
        StringRequest request = new StringRequest(Request.Method.GET, url, successHandler, failHandler) {
            @Override
            protected Map<String, String> getParams() {
                return body;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };

        queue.add(request);
    }
}
