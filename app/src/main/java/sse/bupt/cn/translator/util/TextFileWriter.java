package sse.bupt.cn.translator.util;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

import sse.bupt.cn.translator.model.Text;

public class TextFileWriter implements Runnable {
    private static final String TAG = "TextFileWriter";

    private String path;

    private List<Text> texts;

    public TextFileWriter(String path, List<Text> texts) {
        this.path = path;
        this.texts = texts;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(path));
            if (texts == null) {
                Log.i(TAG, "---text is null---");
                return;
            }
            int size = texts.size();
            for (int index = 0; index < size; ++index) {
                out.writeObject(texts.get(index));
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            Log.i(TAG, "---file:" + path + "not found---");
        } catch (IOException e) {
            Log.i(TAG, "---write path error---");
        }
    }
}
