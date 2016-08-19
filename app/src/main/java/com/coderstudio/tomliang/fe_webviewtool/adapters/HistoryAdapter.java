package com.coderstudio.tomliang.fe_webviewtool.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coderstudio.tomliang.fe_webviewtool.R;
import com.coderstudio.tomliang.fe_webviewtool.bean.History;
import com.coderstudio.tomliang.fe_webviewtool.utils.ViewHolder;

import java.util.List;

/**
 * Created by lianghangbing on 16/8/2.
 */
public class HistoryAdapter extends MasterListAdapter<History> {

    private static final String ICON = "/favicon.ico";
    public HistoryAdapter(Context context) {
        super(context);
    }

    public HistoryAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.history_item, null);
        }
        History item = getItem(position);
        ImageView ivIcon = ViewHolder.get(convertView, R.id.iv_icon);
        TextView tvUrl = ViewHolder.get(convertView, R.id.tv_url);

        Glide.with(mContext).load(item.getUrl()+ICON).into(ivIcon);
        tvUrl.setText(item.getUrl());

        return convertView;
    }
}
