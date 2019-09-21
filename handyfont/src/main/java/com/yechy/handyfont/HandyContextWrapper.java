package com.yechy.handyfont;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.LayoutInflaterFactory;

/**
 * Created by cloud on 2019-09-21.
 */
public class HandyContextWrapper extends ContextWrapper {

    private AppCompatActivity appCompatActivity;

    public static HandyContextWrapper wrap(Context base, AppCompatActivity appCompatActivity) {
        return new HandyContextWrapper(base, appCompatActivity);
    }

    public HandyContextWrapper(Context base, AppCompatActivity appCompatActivity) {
        super(base);
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public Object getSystemService(String name) {
        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            LayoutInflater inflater = (LayoutInflater) super.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (inflater.getFactory2() != null) {
                if (inflater.getFactory2() instanceof HandyFontLayoutFactory) {
                    return inflater;
                }
                inflater = inflater.cloneInContext(this);
            }
            LayoutInflater.Factory2 factory = new HandyFontLayoutFactory(appCompatActivity);
            LayoutInflaterCompat.setFactory2(inflater, factory);
            return inflater;
        }
        return super.getSystemService(name);
    }
}
