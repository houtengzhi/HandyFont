package com.yechy.handyfont;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;

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

    private static Map<String, Typeface> cachedFontMap = new HashMap<>();

    public Typeface getReplacedTypeface(Context context, String fontFamily) {
        Map<String, String> map = HandyFontConfig.getInstance().getReplacedMap();
        if (!isEmpty(fontFamily) && map.containsKey(fontFamily)) {
            String fontPath = map.get(fontFamily);
            Typeface typeface = createTypeface(context, fontPath);
            return typeface;
        }
        return null;
    }

    public static Typeface createTypeface(Context context, String fontPath) {
        if (isEmpty(fontPath)) {
            return null;
        }

        String fontName = new File(fontPath).getName();
        if (isEmpty(fontName)) {
            return null;
        }

        if (cachedFontMap.containsKey(fontName)) {
            return cachedFontMap.get(fontName);
        }

        AssetManager assetManager = context.getAssets();
        try {
            List<String> files = Arrays.asList(assetManager.list(""));
            if (files.contains(fontPath)) {
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
                cachedFontMap.put(fontName, typeface);
                return typeface;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void clearCachedFonts() {
        if (cachedFontMap != null) {
            cachedFontMap.clear();
        }
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }
}
