package com.horizon.tstest.utils;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/*By Phat La*/
public class Utils {
    public static void loadImage(ImageView imageView, String path) {
        if(TextUtils.isEmpty(path)) return;

        Context ctx = imageView.getContext();

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(ctx)
                .load(path)
                .apply(options)
                .into(imageView);
    }

    public static void hideSoftKeyboard(Activity activity) {
        View focusingView = activity.getCurrentFocus();
        if (focusingView == null) focusingView = new View(activity);

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) imm.hideSoftInputFromWindow(focusingView.getWindowToken(), 0);
    }
}
