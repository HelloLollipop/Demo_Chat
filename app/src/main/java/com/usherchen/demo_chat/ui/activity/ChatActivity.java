package com.usherchen.demo_chat.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.usherchen.demo_chat.R;
import com.usherchen.demo_chat.bean.ChatBean;
import com.usherchen.demo_chat.ui.adapter.ChatAdapter;
import com.usherchen.demo_chat.utils.CommonUtils;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private RecyclerView rvChat;
    private EditText etMessage;
    private TextView tvSend;


    private ArrayList<ChatBean> mMsgList;
    private ChatAdapter mChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initAction();
    }

    private void initAction() {
        tvSend.setVisibility(View.GONE);
        tvSend.setOnClickListener(this);
        etMessage.addTextChangedListener(this);
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        mChatAdapter = new ChatAdapter(this, mMsgList);
        rvChat.setAdapter(mChatAdapter);
    }

    private void initView() {
        setContentView(R.layout.activity_home);
        rvChat = (RecyclerView) findViewById(R.id.rv_chat);
        etMessage = (EditText) findViewById(R.id.et_message);
        tvSend = (TextView) findViewById(R.id.tv_send);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == tvSend.getId()) {
            if (CommonUtils.isNotEmpty(etMessage)) {
                if (mMsgList == null) {
                    mMsgList = new ArrayList<>();
                }
                //发送消息
                ChatBean bean = new ChatBean();
                bean.setmType(ChatAdapter.MSG_TYPE.SEND);
                bean.setmMsg(etMessage.getText().toString());
                etMessage.setText("");
                updateNewMsg(bean);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ChatBean bean = new ChatBean();
                        bean.setmType(ChatAdapter.MSG_TYPE.RECEIVE);
                        bean.setmMsg(getString(R.string.return_call));
                        updateNewMsg(bean);
                    }
                }, 2000);
            }
        }
    }

    private void updateNewMsg(ChatBean bean) {
        mChatAdapter.upDateMsgData(bean);
        rvChat.smoothScrollToPosition(mChatAdapter.getItemCount());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (CommonUtils.isNotEmpty(s.toString())) {
            tvSend.setVisibility(View.VISIBLE);
        } else {
            tvSend.setVisibility(View.GONE);
        }
    }
}
