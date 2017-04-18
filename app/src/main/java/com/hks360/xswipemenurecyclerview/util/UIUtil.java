package com.hks360.xswipemenurecyclerview.util;

import android.app.Activity;
import android.view.View;

/**
 * Created by xiaote on 2017/4/17.
 */

public class UIUtil {
    public static <T extends View> T findViewById(Activity activity, int resId) {
        return (T) activity.findViewById(resId);
    }

    public static <T extends View> T findViewById(View view, int resId) {
        return (T) view.findViewById(resId);
    }
}
