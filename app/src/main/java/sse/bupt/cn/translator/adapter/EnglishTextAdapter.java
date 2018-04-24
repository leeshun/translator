package sse.bupt.cn.translator.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import sse.bupt.cn.translator.R;
import sse.bupt.cn.translator.constants.UrlConstant;
import sse.bupt.cn.translator.model.Text;

public class EnglishTextAdapter extends BaseAdapter {
    private static final String TAG = "TextAdapter";

    private List<Text> texts;

    private LayoutInflater inflater;

    public EnglishTextAdapter(List<Text> texts, Context context) {
        this.texts = texts;
        if (!Fresco.hasBeenInitialized()) {
            Fresco.initialize(context);
        }
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public Object getItem(int position) {
        return texts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EnglishTextHolder holder;
        if (convertView == null) {
            holder = new EnglishTextHolder();
            convertView = inflater.inflate(R.layout.text_item, parent, false);

            holder.imageView = convertView.findViewById(R.id.text_image_view);
            holder.textView = convertView.findViewById(R.id.text_text_view);
            convertView.setTag(holder);
        } else {
            holder = (EnglishTextHolder) convertView.getTag();
        }
        holder.textView.setTextSize(18);
        holder.textView.setText(Text.makePara(texts.get(position).getParaId(), texts.get(position).getEnglishText()));
        if (texts.get(position).isOnlyText()) {
            holder.imageView.setVisibility(View.GONE);
            holder.imageView.setAspectRatio(1.3f);
        } else {
            Uri uri = Uri.parse(UrlConstant.GETIMAGE + "/" + texts.get(position).getPictureUrl());
            Log.i(TAG, "---english text adapter,the picture url is " + texts.get(position).getPictureUrl());
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setImageURI(uri);
            //holder.imageView.setController(FrescoUtil.getController(holder.imageView,uri));
        }
        return convertView;
    }

    private class EnglishTextHolder {
        SimpleDraweeView imageView;
        TextView textView;
    }
}
