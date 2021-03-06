package sse.bupt.cn.translator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sse.bupt.cn.translator.R;
import sse.bupt.cn.translator.adapter.ChineseTextAdapter;
import sse.bupt.cn.translator.adapter.EnglishTextAdapter;
import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.constants.UrlConstant;
import sse.bupt.cn.translator.model.Text;
import sse.bupt.cn.translator.network.StringRequestWrapper;
import sse.bupt.cn.translator.responsehandler.GetChineseHandler;
import sse.bupt.cn.translator.responsehandler.TextHandler;
import sse.bupt.cn.translator.util.TextFileWriter;
import sse.bupt.cn.translator.util.TextInfoHolder;

public class TextActivity extends AppCompatActivity {
    private static final String TAG = "TextActivity";

    private List<Text> texts;

    private List<String> chineses;

    private String path;

    private int position;

    private int currentPages;

    private EnglishTextAdapter englishAdapter;

    private ChineseTextAdapter chineseTextAdapter;

    private ListView chineseView;

    private Handler handler;

    private int chineseIndex;

    private boolean isChineseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        initialize();
    }


    public void initialize() {
        isChineseList = false;

        initializeHandler();

        initializeIntentInfo();

        initializeComponent();
    }

    private void initializeComponent() {
        chineseView = findViewById(R.id.chinese_list_view);
//        chineseView = findViewById(R.id.chinese_text_view);
//        chineseView.setTextSize(20);
//        chineseView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chineseView.setVisibility(View.GONE);
//            }
//        });
//        chineseView.setVisibility(View.GONE);
        chineses = new ArrayList<>();
        chineseTextAdapter = new ChineseTextAdapter(chineses, this);
        chineseView.setAdapter(chineseTextAdapter);
        final ListView textListView = findViewById(R.id.english_list_view);
        textListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (texts.get(position).getChineseText() == null || texts.get(position).getChineseText().equals("")) {
                    StringRequestWrapper request = new StringRequestWrapper(TextActivity.this);
                    GetChineseHandler chineseHandler = new GetChineseHandler(handler);
                    Map<String, String> param = new HashMap<>();
                    param.put("articleName", path);
                    param.put("paraId", String.valueOf(texts.get(position).getParaId()));
                    chineseIndex = texts.get(position).getParaId();
                    request.sendGetRequest(UrlConstant.GETCHINESETEXT, chineseHandler, param);
                } else {
//                    chineseView.setText(texts.get(position).getChineseText());
                    chineses.clear();
                    chineses.addAll(texts.get(position).getChineseText());
                    chineseTextAdapter.notifyDataSetChanged();
                    chineseView.setVisibility(View.VISIBLE);
                }
            }
        });
        englishAdapter = new EnglishTextAdapter(texts, this);
        textListView.setAdapter(englishAdapter);
    }

    private void initializeIntentInfo() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position".toLowerCase(), 0);
        path = intent.getStringExtra("path".toLowerCase());
        currentPages = intent.getIntExtra("lastViewPages", 0);
        texts = TextInfoHolder.getTexts();
        setTitle(path);
        if (texts == null || texts.isEmpty()) {
            Map<String, String> params = new HashMap<>();
            params.put("articleName", path);
            TextHandler textHandler = new TextHandler(handler);
            StringRequestWrapper request = new StringRequestWrapper(this);
            request.sendGetRequest(UrlConstant.GETTEXTS, textHandler, params);
        }
    }

    private void initializeHandler() {
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int type = msg.what;
                switch (type) {
                    case MessageType.TEXT_RESPONSE_SUCCESS:
                        List<Text> tmp = (List<Text>) msg.obj;
                        texts.clear();
                        texts.addAll(tmp);
                        englishAdapter.notifyDataSetChanged();
                        Log.i(TAG, "---message handler " + texts.size() + "---");
                        break;
                    case MessageType.TEXT_RESPONSE_DO_NOT_CONTAIN_JSON_OBJECT:
                        Log.i(TAG, "text response do not contain json object");
                        break;
                    case MessageType.TEXT_RESPONSE_PARSE_ERROR:
                        Log.i(TAG, "text response parse error");
                        break;
                    case MessageType.TEXT_REQUEST_INTERNET_ERROR:
                        Log.i(TAG, "text request internet error");
                        break;
                    case MessageType.GET_CHINESE_ERROR:
                        Log.i(TAG, "get chinese error");
                        break;
                    case MessageType.GET_CHINESE_TEXT_AND_SHOW_TO_ACTIVITY:
                        Log.i(TAG, "---get chinese text---");
                        List<String> result = (List<String>) msg.obj;
                        if (result == null || result.equals("")) {
                            Log.i(TAG, "---null message---");
                            chineses.clear();
                            chineses.add(Text.makePara(chineseIndex, "目前暂时没有对应的中文翻译"));
//                            chineseView.setText(Text.makePara(chineseIndex, "目前暂时没有对应的中文翻译"));
                        } else {
                            texts.get(chineseIndex - 1).setChineseText(result);
                            chineses.clear();
                            chineses.addAll(result);
                            chineseTextAdapter.notifyDataSetChanged();
//                            texts.get(chineseIndex - 1).setChineseText(result);
//                            chineseView.setText(Text.makePara(chineseIndex, result));
                        }
                        chineseView.setVisibility(View.VISIBLE);
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        TextFileWriter writer = new TextFileWriter(path, texts, handler, this);
        writer.start();
        TextInfoHolder.setIndex(position);
        TextInfoHolder.setLastViewPages(currentPages);
        TextInfoHolder.setLastViewTime(Calendar.getInstance().getTime());
    }

    @Override
    public void onBackPressed() {
        if (isChineseList) {
            chineseView.setVisibility(View.GONE);
            isChineseList = false;
            return;
        }
        super.onBackPressed();
    }
}
