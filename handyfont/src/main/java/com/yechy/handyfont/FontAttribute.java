package com.yechy.handyfont;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

/**
 * Created by cloud on 2019-09-17.
 */
public class FontAttribute {
    private static final String TAG = "HandyFont";
    private static final String ACTION_BAR_TITLE = "action_bar_title";
    private static final String ACTION_BAR_SUBTITLE = "action_bar_subtitle";

    private static final String ATTR_FONT_FAMILT = "fontFamily";
    private int[] attributeIds = new int[]{android.R.attr.fontFamily};

    private boolean loggable = HandyFontConfig.getInstance().isLoggable();
    private boolean debuggable = HandyFontConfig.getInstance().isDebuggable();

    public void load(View view, AttributeSet attrs) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            String fontFamilt = resolveFontFamily(view.getContext(), attrs);
            if(loggable) Log.d(TAG, "loadAttribute, fontFamily=" + fontFamilt);
            if (fontFamilt == null) fontFamilt = HandyFontConfig.NULL_FONT_FAMILY;
            Typeface typeface = TypefaceUtil.getReplacedTypeface(view.getContext(), fontFamilt);
            Typeface defaultTypeface = tv.getTypeface();
            if (typeface != null) {
                tv.setTypeface(typeface, tv.getTypeface().getStyle());
            }
            if (debuggable) {
                String text = tv.getText().toString();
                String ntext = text + " (default font family is " + fontFamilt + ")";
                Spannable spannable = new SpannableString(ntext);

                spannable.setSpan(new TypefaceSpan(fontFamilt), ntext.length() - text.length(),
                        ntext.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                tv.setText(spannable);
            }
        }
    }

    private String resolveFontFamily(Context context, AttributeSet attrs) {
        String fontFamily = HandyUtil.pullFontPathFromView(context, attrs, attributeIds);
        if (TextUtils.isEmpty(fontFamily)) {
            fontFamily = HandyUtil.pullFontPathFromStyle(context, attrs, attributeIds);
        }
        if (TextUtils.isEmpty(fontFamily)) {
            fontFamily = HandyUtil.pullFontPathFromTextAppearance(context, attrs, attributeIds);
        }
        return fontFamily;
    }

    /**
     * Some styles are in sub styles, such as actionBarTextStyle etc..
     *
     * @param view view to check.
     * @return 2 element array, default to -1 unless a style has been found.
     */
    protected static int[] getStyleForTextView(TextView view) {
        final int[] styleIds = new int[]{-1, -1};
        // Try to find the specific actionbar styles
        if (isActionBarTitle(view)) {
            styleIds[0] = android.R.attr.actionBarStyle;
            styleIds[1] = android.R.attr.titleTextStyle;
        } else if (isActionBarSubTitle(view)) {
            styleIds[0] = android.R.attr.actionBarStyle;
            styleIds[1] = android.R.attr.subtitleTextStyle;
        }
        if (styleIds[0] == -1) {
            // Use TextAppearance as default style
            styleIds[0] = android.R.attr.textAppearance;
        }
        return styleIds;
    }

    /**
     * An even dirtier way to see if the TextView is part of the ActionBar
     *
     * @param view TextView to check is Title
     * @return true if it is.
     */
    @SuppressLint("NewApi")
    protected static boolean isActionBarTitle(TextView view) {
        if (matchesResourceIdName(view, ACTION_BAR_TITLE)) return true;
        if (parentIsToolbarV7(view)) {
            final Toolbar parent = (Toolbar) view.getParent();
            return TextUtils.equals(parent.getTitle(), view.getText());
        }
        return false;
    }

    /**
     * An even dirtier way to see if the TextView is part of the ActionBar
     *
     * @param view TextView to check is Title
     * @return true if it is.
     */
    @SuppressLint("NewApi")
    protected static boolean isActionBarSubTitle(TextView view) {
        if (matchesResourceIdName(view, ACTION_BAR_SUBTITLE)) return true;
        if (parentIsToolbarV7(view)) {
            final Toolbar parent = (Toolbar) view.getParent();
            return TextUtils.equals(parent.getSubtitle(), view.getText());
        }
        return false;
    }

    protected static boolean parentIsToolbarV7(View view) {
        return HandyUtil.canCheckForV7Toolbar() && view.getParent() != null && (view.getParent() instanceof Toolbar);
    }

    /**
     * Use to match a view against a potential view id. Such as ActionBar title etc.
     *
     * @param view    not null view you want to see has resource matching name.
     * @param matches not null resource name to match against. Its not case sensitive.
     * @return true if matches false otherwise.
     */
    protected static boolean matchesResourceIdName(View view, String matches) {
        if (view.getId() == View.NO_ID) return false;
        final String resourceEntryName = view.getResources().getResourceEntryName(view.getId());
        return resourceEntryName.equalsIgnoreCase(matches);
    }
}
