package com.usherchen.demo_chat.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usherchen.demo_chat.R;
import com.usherchen.demo_chat.bean.ChatBean;
import com.usherchen.demo_chat.ui.holder.ReceiveRecyclerViewHolder;
import com.usherchen.demo_chat.ui.holder.SendRecyclerViewHolder;

import java.util.ArrayList;

/**
 * Created by UsherChen on 2017/10/21.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    /**
     * 消息类型
     * 发送 1001
     * 接收 1002
     */
    public interface MSG_TYPE {
        int NULL = -1;
        int SEND = 1001;
        int RECEIVE = 1002;
    }

    private ArrayList<ChatBean> mMsgList;

    public ChatAdapter(Context mContext, ArrayList<ChatBean> mMsgList) {
        this.mContext = mContext;
        this.mMsgList = mMsgList;
    }

    public void upDateMsgData(ChatBean bean) {
        if (mMsgList == null) {
            mMsgList = new ArrayList<>();
        }
        mMsgList.add(bean);
        notifyDataSetChanged();//刷新列表
    }

    //设置多种布局
    @Override
    public int getItemViewType(int position) {
        int type = MSG_TYPE.NULL;
        try {
            type = mMsgList.get(position).getmType();
        } catch (Exception e) {
            //捕获异常
        }
        return type;
    }

    //返回不同的类型的布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == MSG_TYPE.SEND) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_send_msg, null);
            return new SendRecyclerViewHolder(itemView);
        } else if (viewType == MSG_TYPE.RECEIVE) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_receive_msg, null);
            return new ReceiveRecyclerViewHolder(itemView);
        } else {
            return null;
        }
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String text = mMsgList.get(position).getmMsg();
        if (holder instanceof SendRecyclerViewHolder) {
            SendRecyclerViewHolder sendHolder = (SendRecyclerViewHolder) holder;
            sendHolder.ivHeadSend.setImageResource(R.drawable.head_send);
            sendHolder.tvMsg.setText(text);
            return;
        }
        if (holder instanceof ReceiveRecyclerViewHolder) {
            ReceiveRecyclerViewHolder receiveHolder = (ReceiveRecyclerViewHolder) holder;
            receiveHolder.ivHeadSend.setImageResource(R.drawable.head_receive);
            receiveHolder.tvMsg.setText(text);
            return;
        }
    }

    @Override
    public int getItemCount() {
        try {
            return mMsgList.size();
        } catch (Exception e) {
            return 0;
        }
    }

}
