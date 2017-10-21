package com.usherchen.demo_chat.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.usherchen.demo_chat.R;

/**
 * Created by UsherChen on 2017/10/21.
 */

public class SendRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivHeadSend;
    public TextView tvMsg;

    public SendRecyclerViewHolder(View view) {
        super(view);
        ivHeadSend = (ImageView) view.findViewById(R.id.iv_head_send);
        tvMsg = (TextView) view.findViewById(R.id.tv_msg);
    }
}
