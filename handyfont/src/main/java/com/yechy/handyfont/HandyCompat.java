package com.yechy.handyfont;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by cloud on 2019-09-30.
 */
public class HandyCompat {

    public static void setTypeface(TextView tv, String defaultFontFamily) {
        if (tv == null) {
            return;
        }
        Typeface typeface = TypefaceUtil.getReplacedTypeface(tv.getContext(), defaultFontFamily);
        Typeface defaultTypeface = tv.getTypeface();
        int defaultStyle = defaultTypeface != null ? defaultTypeface.getStyle() : 0;
        if (typeface != null) {
            tv.setTypeface(typeface, defaultStyle);
        }
    }
}
