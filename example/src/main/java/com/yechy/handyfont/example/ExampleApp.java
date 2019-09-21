package com.yechy.handyfont.example;

import android.app.Application;

import com.yechy.handyfont.HandyFontConfig;

/**
 * Created by cloud on 2019-09-15.
 */
public class ExampleApp extends Application {

    private String chilanka_regular = "Chilanka-Regular.ttf";
    private String dancingScript_bold = "DancingScript-Bold.ttf";
    private String dancingScript_regular = "DancingScript-Regular.ttf";

    private String sans_serif = "sans-serif";
    private String sans_serif_medium = "sans-serif-medium";


    @Override
    public void onCreate() {
        super.onCreate();
        HandyFontConfig.getInstance()
                .setLogEnabled(true)
                .setDebugEnabled(true)
                .setReplaceEnabled(true)
                .addReplaceDefaultFont(dancingScript_bold)
                .addReplacedFont(sans_serif, chilanka_regular)
                .addReplacedFont(sans_serif_medium, dancingScript_regular);
    }
}
