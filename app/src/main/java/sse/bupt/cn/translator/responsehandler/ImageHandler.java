package sse.bupt.cn.translator.responsehandler;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import com.android.volley.VolleyError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import sse.bupt.cn.translator.network.handler.ImageRequestHandler;

public class ImageHandler implements ImageRequestHandler {
    private static final String TAG = "ImageHandler";


    private Handler handler;

    public ImageHandler(Handler handler) {
        this.handler = handler;
    }


    @Override
    public void onSuccess(Bitmap response, String name) {
        //TODO(leeshun) update bitmap
        File path = new File(name + ".png");
        OutputStream output;
        try {
            output = new FileOutputStream(path);
            response.compress(Bitmap.CompressFormat.PNG, 85, output);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            Log.i(TAG, "---open file error---");
        } catch (IOException e) {
            Log.i(TAG, "---flush file error---");
        }
    }

    @Override
    public void onFail(VolleyError error) {
        Log.i(TAG, "---get image error---");
    }
}
