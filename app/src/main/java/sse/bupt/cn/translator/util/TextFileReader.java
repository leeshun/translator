package sse.bupt.cn.translator.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.model.Text;

public class TextFileReader implements Runnable {
    private static final String TAG = "TextFileReader";

    private String path;

    private Handler handler;

    private Context context;

    public TextFileReader(String path, Handler handler, Context context) {
        this.path = path;
        this.handler = handler;
        this.context = context;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        List<Text> texts = new ArrayList<>();

        ObjectInput in;
        try {
            in = new ObjectInputStream(context.openFileInput(path));
            Log.i(TAG, "---read path is " + path + "---");
            Text text;
            while ((text = (Text) in.readObject()) != null) {
                texts.add(text);
            }
        } catch (FileNotFoundException e) {
            Log.i(TAG, "---file not found---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_FILE_NOT_FOUND);
            handler.sendMessage(message);
        } catch (EOFException e) {
            Log.i(TAG, "---read over---");
            Log.i(TAG, "---read text size is " + texts.size() + "---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_READ_SUCCESS);
            message.obj = texts;
            handler.sendMessage(message);
        } catch (IOException e) {
            Log.i(TAG, "---read path error---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_FILE_READ_ERROR, e.getMessage());
            handler.sendMessage(message);
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "---class not found---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_CLASS_NOT_FOUND, e.getMessage());
            handler.sendMessage(message);
        }
    }
}
