package com.yechy.handyfont;

import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cloud on 2019-09-07.
 */
public class HandyFontConfig {

    private static HandyFontConfig instance;


    private Map<String, String> mReplacedMap = new HashMap<>();

    public static HandyFontConfig getInstance() {
        if (instance == null) {
            synchronized (HandyFontConfig.class) {
                if (instance == null) {
                    instance = new HandyFontConfig();
                }
            }
        }
        return instance;
    }

    public HandyFontConfig addReplacedFont(String oldFontFamily, String newFontPath) {
        mReplacedMap.put(oldFontFamily, newFontPath);
        return this;
    }

    public HandyFontConfig removePlacedFont(String fontFamily) {
        if (mReplacedMap != null && !isEmpty(fontFamily)) {
            mReplacedMap.remove(fontFamily);
        }
        return this;
    }

    public void clearReplacedFonts() {
        if (mReplacedMap != null) {
            mReplacedMap.clear();
        }
    }

    public Map<String, String> getReplacedMap() {
        return mReplacedMap;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }
}
