package com.yechy.handyfont.example;

import android.app.Application;
import android.content.Context;

import com.yechy.handyfont.HandyContextWrapper;
import com.yechy.handyfont.HandyFontConfig;

/**
 * Created by cloud on 2019-09-15.
 */
public class ExampleApp extends Application {

    private String chilanka_regular = "font/Chilanka-Regular.ttf";
    private String dancingScript_bold = "font/DancingScript-Bold.ttf";
    private String dancingScript_regular = "font/DancingScript-Regular.ttf";

    private String sans_serif = "sans-serif";
    private String sans_serif_medium = "sans-serif-medium";


    @Override
    public void onCreate() {
        super.onCreate();
        HandyFontConfig.getInstance()
                .setLogEnabled(true)
                .setDebugEnabled(true)
                .setReplaceEnabled(true)
                .addReplacedFontForDefaultFontFamily(dancingScript_bold)
                .addReplacedFont(sans_serif, chilanka_regular)
                .addReplacedFont(sans_serif_medium, dancingScript_regular);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(HandyContextWrapper.wrap(base));
    }
}
