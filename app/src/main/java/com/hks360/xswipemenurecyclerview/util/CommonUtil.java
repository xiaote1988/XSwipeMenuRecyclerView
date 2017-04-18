package com.hks360.xswipemenurecyclerview.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xiaote on 2017/4/17.
 */

public class CommonUtil {
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
