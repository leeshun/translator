package sse.bupt.cn.translator.network.handler;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ImageFailHandler implements Response.ErrorListener {
    private static final String TAG = "ImageFailHandler";

    private ImageRequestHandler handler;

    public ImageFailHandler(ImageRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i(TAG, "---" + error.getMessage() + "---");
        handler.onFail(error);
    }
}
