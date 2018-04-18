package sse.bupt.cn.translator.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import sse.bupt.cn.translator.model.Text;

public class EnglishTextAdapter extends BaseAdapter {
    private static final String TAG = "TextAdapter";

    private List<Text> texts;

    private Context context;

    private LayoutInflater inflater;

    public EnglishTextAdapter(List<Text> texts, Context context) {
        this.texts = texts;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        Log.i(TAG, "---text size is " + texts.size() + "---");
        return texts.size();
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "---item " + texts.get(position).toString() + "---");
        return texts.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "---position is " + position + "---");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
