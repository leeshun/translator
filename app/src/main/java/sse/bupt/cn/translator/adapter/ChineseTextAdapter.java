package sse.bupt.cn.translator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import sse.bupt.cn.translator.R;

public class ChineseTextAdapter extends BaseAdapter {
    private List<String> texts;

    private LayoutInflater inflater;

    public ChineseTextAdapter(List<String> texts, Context context) {
        this.texts = texts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public Object getItem(int i) {
        return texts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChineseTextHolder holder;
        if (view == null) {
            holder = new ChineseTextHolder();
            view = inflater.inflate(R.layout.chinese_text_item, viewGroup, false);

            holder.textView = view.findViewById(R.id.chinese_text_view);
            holder.editText = view.findViewById(R.id.chinese_edit_text);

            view.setTag(holder);
        } else {
            holder = (ChineseTextHolder) view.getTag();
        }

        holder.textView.setText(texts.get(i));
        return view;
    }

    private class ChineseTextHolder {
        EditText editText;
        TextView textView;
    }
}
