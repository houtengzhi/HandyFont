package com.yechy.handyfont;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by cloud on 2019-09-30.
 */
public class HandyTypeface {

    private static boolean debuggable = HandyFontConfig.getInstance().isDebuggable();

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

        if (debuggable) {
            final String ntext = "default font family: " + defaultFontFamily;
//                view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//                    @Override
//                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//                        menu.setHeaderTitle("View Property");
//                        menu.add(1, 1, 1, ntext);
//                    }
//                });
        }
    }
}
