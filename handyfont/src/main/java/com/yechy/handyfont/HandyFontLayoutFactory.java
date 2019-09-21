package com.yechy.handyfont;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cloud on 2019-09-17.
 */
public class HandyFontLayoutFactory implements LayoutInflater.Factory2 {
    private static final String TAG = "HandyFontLayoutFactory";
    private AppCompatDelegate appCompatDelegate;
    private Context mContext;
    private FontAttribute mFontAttribute;

    private static final Map<String, Constructor<? extends View>> sConstructorMap = new HashMap<>();
    private static final String[] mClassPrefixList = {"android.widget.", "android.view.",
            "android.webkit"};

    public void installViewFactory() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (layoutInflater.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(layoutInflater, this);
        }
    }

    public HandyFontLayoutFactory(Activity activity) {
        this.mContext = activity;
        if (activity instanceof AppCompatActivity) {
            this.appCompatDelegate = ((AppCompatActivity) activity).getDelegate();
        }
        mFontAttribute = new FontAttribute();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        if (appCompatDelegate != null) {
            view = appCompatDelegate.createView(parent, name, context, attrs);
        }
        if (view == null) {
            view = createViewFromTag(name, context, attrs);
        }
        if (HandyFontConfig.getInstance().isReplaceEnabled()) {
            mFontAttribute.load(view, attrs);
        }
        return view;
    }

    private View createViewFromTag(String name, Context context, AttributeSet attrs) {
        if (name.contains(".")) {
            return createView(name, context, attrs);
        }

        for (String tag : mClassPrefixList) {
            View v = createView(tag + name, context, attrs);
            if (v == null) {
                continue;
            }
            return v;
        }
        return null;
    }

    private View createView(String name, Context context, AttributeSet attrs) {
        Constructor<? extends View> constructor = sConstructorMap.get(name);
        if (constructor == null) {
            try {
                Class<? extends View> aClass = context.getClassLoader().loadClass(name).asSubclass(View.class);
                constructor = aClass.getConstructor(Context.class, AttributeSet.class);
                sConstructorMap.put(name, constructor);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (constructor != null) {
            try {
                return constructor.newInstance(context, attrs);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }


}
