package sse.bupt.cn.translator.network.handler;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;

public class ImageSuccessHandler implements Response.Listener<Bitmap> {
    private static final String TAG = "ImageSuccessHandler";

    private ImageRequestHandler handler;

    public ImageSuccessHandler(ImageRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onResponse(Bitmap response) {
        Log.i(TAG, "---receive a image---");
        handler.onSuccess(response);
    }
}
