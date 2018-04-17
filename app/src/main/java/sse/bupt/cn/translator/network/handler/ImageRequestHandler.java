package sse.bupt.cn.translator.network.handler;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

public interface ImageRequestHandler {
    void onSuccess(Bitmap response);

    void onFail(VolleyError error);
}
