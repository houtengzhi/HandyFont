package com.yechy.handyfont;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cloud on 2019-09-07.
 */
public class HandyUtil {

    public static String pullFontPathFromView(Context context, AttributeSet attrs, int[] attributeId) {
        if (attributeId == null || attrs == null)
            return null;

        final String attributeName;
        try {
            attributeName = context.getResources().getResourceEntryName(attributeId[0]);
        } catch (Resources.NotFoundException e) {
            // invalid attribute ID
            return null;
        }

        final int stringResourceId = attrs.getAttributeResourceValue(null, attributeName, -1);
        return stringResourceId > 0
                ? context.getString(stringResourceId)
                : attrs.getAttributeValue(null, attributeName);
    }

    public static String pullFontPathFromStyle(Context context, AttributeSet attrs, int[] attributeId) {
        if (attributeId == null || attrs == null)
            return null;
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, attributeId);
        if (typedArray != null) {
            try {
                // First defined attribute
                String fontFromAttribute = typedArray.getString(0);
                if (!TextUtils.isEmpty(fontFromAttribute)) {
                    return fontFromAttribute;
                }
            } catch (Exception ignore) {
                // Failed for some reason.
            } finally {
                typedArray.recycle();
            }
        }
        return null;
    }

    public static String pullFontPathFromTextAppearance(final Context context, AttributeSet attrs, int[] attributeId) {
        if (attributeId == null || attrs == null) {
            return null;
        }

        int textAppearanceId = -1;
        final TypedArray typedArrayAttr = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.textAppearance});
        if (typedArrayAttr != null) {
            try {
                textAppearanceId = typedArrayAttr.getResourceId(0, -1);
            } catch (Exception ignored) {
                // Failed for some reason
                return null;
            } finally {
                typedArrayAttr.recycle();
            }
        }

        final TypedArray textAppearanceAttrs = context.obtainStyledAttributes(textAppearanceId, attributeId);
        if (textAppearanceAttrs != null) {
            try {
                return textAppearanceAttrs.getString(0);
            } catch (Exception ignore) {
                // Failed for some reason.
                return null;
            } finally {
                textAppearanceAttrs.recycle();
            }
        }
        return null;
    }

    public static String pullFontPathFromTheme(Context context, int styleAttrId, int[] attributeId) {
        if (styleAttrId == -1 || attributeId == null)
            return null;

        final Resources.Theme theme = context.getTheme();
        final TypedValue value = new TypedValue();

        theme.resolveAttribute(styleAttrId, value, true);
        final TypedArray typedArray = theme.obtainStyledAttributes(value.resourceId, attributeId);
        try {
            String font = typedArray.getString(0);
            return font;
        } catch (Exception ignore) {
            // Failed for some reason.
            return null;
        } finally {
            typedArray.recycle();
        }
    }

    public static String pullFontPathFromTheme(Context context, int styleAttrId, int subStyleAttrId, int[] attributeId) {
        if (styleAttrId == -1 || attributeId == null)
            return null;

        final Resources.Theme theme = context.getTheme();
        final TypedValue value = new TypedValue();

        theme.resolveAttribute(styleAttrId, value, true);
        int subStyleResId = -1;
        final TypedArray parentTypedArray = theme.obtainStyledAttributes(value.resourceId, new int[]{subStyleAttrId});
        try {
            subStyleResId = parentTypedArray.getResourceId(0, -1);
        } catch (Exception ignore) {
            // Failed for some reason.
            return null;
        } finally {
            parentTypedArray.recycle();
        }

        if (subStyleResId == -1) return null;
        final TypedArray subTypedArray = context.obtainStyledAttributes(subStyleResId, attributeId);
        if (subTypedArray != null) {
            try {
                return subTypedArray.getString(0);
            } catch (Exception ignore) {
                // Failed for some reason.
                return null;
            } finally {
                subTypedArray.recycle();
            }
        }
        return null;
    }

    private static Boolean sToolbarCheck = null;
    private static Boolean sAppCompatViewCheck = null;

    /**
     * See if the user has added appcompat-v7, this is done at runtime, so we only check once.
     *
     * @return true if the v7.Toolbar is on the classpath
     */
    static boolean canCheckForV7Toolbar() {
        if (sToolbarCheck == null) {
            try {
                Class.forName("android.support.v7.widget.Toolbar");
                sToolbarCheck = Boolean.TRUE;
            } catch (ClassNotFoundException e) {
                sToolbarCheck = Boolean.FALSE;
            }
        }
        return sToolbarCheck;
    }

    /**
     * See if the user has added appcompat-v7 with AppCompatViews
     *
     * @return true if AppcompatTextView is on the classpath
     */
    static boolean canAddV7AppCompatViews() {
        if (sAppCompatViewCheck == null) {
            try {
                Class.forName("android.support.v7.widget.AppCompatTextView");
                sAppCompatViewCheck = Boolean.TRUE;
            } catch (ClassNotFoundException e) {
                sAppCompatViewCheck = Boolean.FALSE;
            }
        }
        return sAppCompatViewCheck;
    }
}
