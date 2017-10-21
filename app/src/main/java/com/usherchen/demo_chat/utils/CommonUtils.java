package com.usherchen.demo_chat.utils;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by UsherChen on 2017/10/21.
 */

public class CommonUtils {

    public static boolean isEmpty(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(EditText editText) {
        if (isEmpty(editText.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(EditText editText) {
        return !isEmpty(editText);
    }

}
