package sse.bupt.cn.translator.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sse.bupt.cn.translator.R;
import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.constants.UrlConstant;
import sse.bupt.cn.translator.model.Text;
import sse.bupt.cn.translator.network.StringRequestWrapper;
import sse.bupt.cn.translator.responsehandler.TextHandler;
import sse.bupt.cn.translator.util.TextFileReader;
import sse.bupt.cn.translator.util.TextFileWriter;
import sse.bupt.cn.translator.util.TextInfoHolder;

public class TextActivity extends AppCompatActivity {
    private static final String TAG = "TextActivity";

    private List<Text> texts;

    private String path;

    private int position;

    private int currentPages;

    private Handler handler;

    private boolean isFromInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        initialize();
    }


    public void initialize() {
        isFromInternet = false;
        Log.i(TAG, "--- initialize handler ---");
        initializeHandler();
        Log.i(TAG, "--- initialize intent information ---");
        initializeIntentInfo();
        Log.i(TAG, "--- initialize activity view ---");
        initializeComponent();
    }

    private void initializeComponent() {
        //TODO(leeshun) initialize activity view
    }

    private void initializeIntentInfo() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position".toLowerCase(), 0);
        path = intent.getStringExtra("path".toLowerCase());
        currentPages = intent.getIntExtra("lastViewPages", 0);
        texts = TextInfoHolder.getTexts();
        if (texts == null || texts.isEmpty()) {
            TextFileReader reader  = new TextFileReader(path,handler,this);
            reader.start();
            Log.i(TAG, "--- text is null ---");
            isFromInternet = true;
            Log.i(TAG, "--- get text from the internet ---");
            Map<String, String> params = new HashMap<>();
            params.put("articleName", path);
            TextHandler textHandler = new TextHandler(handler);
            StringRequestWrapper request = new StringRequestWrapper(this);
            request.sendPostRequest(UrlConstant.GETTEXTS, textHandler, params);
        }
    }

    private void initializeHandler() {
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int type = msg.what;
                switch (type) {
                    case MessageType.TEXT_RESPONSE_SUCCESS:
                        texts = (List<Text>) msg.obj;
                        Log.i(TAG, "---message handler " + texts.size() + "---");
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "--- pause ---");
        super.onPause();
        Log.i(TAG, "---start write---");
        TextFileWriter writer = new TextFileWriter(path, texts, handler, this);
        writer.start();
        Log.i(TAG, "---start transfer information---");
        TextInfoHolder.setIndex(position);
        TextInfoHolder.setLastViewPages(currentPages);
        TextInfoHolder.setLastViewTime(Calendar.getInstance().getTime());
        Log.i(TAG, "---end transfer information---");
    }
}
