package com.example.mylogin;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

public class customprogressbar extends Dialog {
    public customprogressbar(@NonNull Context context) {
        super(context);
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(params);
        setTitle("singing in please wait...");
        setCancelable(true);
        setOnCancelListener(null);
        View p= LayoutInflater.from(context).inflate(R.layout.customdialogsinglerow,null);
        setContentView(p);
    }
}
