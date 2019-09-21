package com.yechy.handyfont;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cloud on 2019-09-07.
 */
public class HandyFontConfig {

    private static final String TAG = HandyFontConfig.class.getSimpleName();

    private static HandyFontConfig instance;


    private Map<String, String> mReplacedMap = new HashMap<>();
    private boolean mIsReplace = true;
    private boolean mDebuggable = false;
    private boolean mLoggable = false;

    public static final String NULL_FONT_FAMILY = "null_font_family";

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

    public HandyFontConfig addReplaceDefaultFont(String newFontPath) {
        mReplacedMap.put(NULL_FONT_FAMILY, newFontPath);
        return this;
    }

    public HandyFontConfig removePlacedFont(String fontFamily) {
        if (mReplacedMap != null && !isEmpty(fontFamily)) {
            mReplacedMap.remove(fontFamily);
        }
        return this;
    }

    public HandyFontConfig removeDefaultReplacedFont() {
        if (mReplacedMap.containsKey(NULL_FONT_FAMILY)) {
            mReplacedMap.remove(NULL_FONT_FAMILY);
        }
        return this;
    }

    public HandyFontConfig setReplaceEnabled(boolean enabled) {
        mIsReplace = enabled;
        return this;
    }

    public boolean isReplaceEnabled() {
        return mIsReplace;
    }

    public HandyFontConfig setDebugEnabled(boolean enabled) {
        mDebuggable = enabled;
        return this;
    }

    public boolean isDebuggable() {
        return mDebuggable;
    }

    public HandyFontConfig setLogEnabled(boolean enabled) {
        mLoggable = enabled;
        return this;
    }

    public boolean isLoggable() {
        return mLoggable;
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
