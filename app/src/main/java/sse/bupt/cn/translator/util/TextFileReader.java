package sse.bupt.cn.translator.util;

import android.os.Handler;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import sse.bupt.cn.translator.model.Text;

public class TextFileReader implements Runnable {
    private static final String TAG = "TextFileReader";

    private String path;

    private Handler handler;

    public TextFileReader(String path, Handler handler) {
        this.path = path;
        this.handler = handler;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        List<Text> texts = new ArrayList<>();

        ObjectInput in;
        try {
            in = new ObjectInputStream(new FileInputStream(path));
            Text text;
            while ((text = (Text)in.readObject()) != null) {
                texts.add(text);
            }
        } catch (IOException e) {
            Log.i(TAG, "---read path error---");
            //TODO(leeshun) dispatch this error to activity
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "---class not found---");
        }
    }
}
