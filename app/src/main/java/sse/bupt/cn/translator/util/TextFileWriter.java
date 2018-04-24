package sse.bupt.cn.translator.util;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.model.Text;

public class TextFileWriter implements Runnable {
    private static final String TAG = "TextFileWriter";

    private String path;

    private List<Text> texts;

    private Handler handler;

    private Context context;

    public TextFileWriter(String path, List<Text> texts, Handler handler, Context context) {
        this.path = path;
        this.texts = texts;
        this.handler = handler;
        this.context = context;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(context.openFileOutput(path, Context.MODE_PRIVATE));
            if (texts == null) {
                Log.i(TAG, "---text is null---");
                return;
            }
            int size = texts.size();
            for (int index = 0; index < size; ++index) {
                Log.i(TAG, texts.get(index).getChineseText());
                out.writeObject(texts.get(index));
            }
            out.flush();
            out.close();
            Log.i(TAG, "---write size is " + texts.size() + "---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_WRITE_SUCCESS);
            handler.sendMessage(message);
        } catch (FileNotFoundException e) {
            Log.i(TAG, "---file:" + path + " not found---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_FILE_WRITE_ERROR);
            handler.sendMessage(message);
        } catch (IOException e) {
            Log.i(TAG, "---write path error---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_CLASS_NOT_FOUND);
            handler.sendMessage(message);
        }
    }
}
