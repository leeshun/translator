package sse.bupt.cn.translator.responsehandler;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.VolleyError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.network.handler.ImageRequestHandler;
import sse.bupt.cn.translator.util.MessageFactory;

public class ImageHandler implements ImageRequestHandler {
    private static final String TAG = "ImageHandler";


    private Handler handler;

    public ImageHandler(Handler handler) {
        this.handler = handler;
    }


    @Override
    public void onSuccess(Bitmap response, String name) {
        File path = new File(name + ".png");
        OutputStream output;
        try {
            output = new FileOutputStream(path);
            response.compress(Bitmap.CompressFormat.PNG, 85, output);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            Log.i(TAG, "---open file error---");
            return;
        } catch (IOException e) {
            Log.i(TAG, "---flush file error---");
            return;
        }
        Message message = MessageFactory.getMessage(MessageType.IMAGE_RESPONSE_SUCCESS);
        message.obj = response;
        handler.sendMessage(message);
    }

    @Override
    public void onFail(VolleyError error) {
        Log.i(TAG, "---get image error---");
    }
}
