package com.horizon.tstest.utils;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.horizon.tstest.R;

/*By Phat La*/
public class DialogUtils {

    public static AlertDialog createLoadingDialog(Context context, String message){
        return createLoadingDialog(context, message, -1);
    }

    public static AlertDialog createLoadingDialog(Context context, String message, int color){
        View v = View.inflate(context, R.layout.progress,null);
        ProgressBar progressBar = v.findViewById(R.id.progressBar);
        TextView textView       = v.findViewById(R.id.textView);

        //
        if(color > 0) progressBar.getIndeterminateDrawable().setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY);
        textView.setText(message);

        return new AlertDialog.Builder(context).setView(v).setCancelable(false).create();
    }


    public static AlertDialog createMessageDialog(Context context, String message){
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create();
    }
}
