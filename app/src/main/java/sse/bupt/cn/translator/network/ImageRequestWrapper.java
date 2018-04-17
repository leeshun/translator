package sse.bupt.cn.translator.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import sse.bupt.cn.translator.network.handler.ImageFailHandler;
import sse.bupt.cn.translator.network.handler.ImageRequestHandler;
import sse.bupt.cn.translator.network.handler.ImageSuccessHandler;


public class ImageRequestWrapper {
    private static final String TAG = "ImageRequestWrapper";

    private static final int WIDTH = 200;

    private static final int HEIGHT = 200;

    private RequestQueue queue;

    private int maxWidth;

    private int maxHeight;

    public ImageRequestWrapper(Context context) {
        this(context, WIDTH, HEIGHT);
    }

    public ImageRequestWrapper(Context context, final int width, final int height) {
        queue = Volley.newRequestQueue(context);
        maxWidth = width;
        maxHeight = height;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void imageRequest(String url, ImageView.ScaleType scaleType, Bitmap.Config config, ImageRequestHandler handler) {
        ImageSuccessHandler successHandler = new ImageSuccessHandler(handler);
        ImageFailHandler failHandler = new ImageFailHandler(handler);

        Log.i(TAG, "--- request url is " + url + "---" );
        ImageRequest request = new ImageRequest(url,successHandler,maxWidth,maxHeight,scaleType,config,failHandler);

        queue.add(request);
    }
}
