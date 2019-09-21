package com.yechy.handyfont.example;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yechy.handyfont.HandyContextWrapper;
import com.yechy.handyfont.HandyFontLayoutFactory;

/**
 * Created by cloud on 2019-09-21.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        HandyFontLayoutFactory factory = new HandyFontLayoutFactory(this);
//        factory.installViewFactory();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(HandyContextWrapper.wrap(newBase, this));
    }
}
