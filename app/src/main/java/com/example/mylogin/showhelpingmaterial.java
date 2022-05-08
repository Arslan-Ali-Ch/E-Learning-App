package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URLEncoder;

public class showhelpingmaterial extends AppCompatActivity {
WebView pdfview;
help2model md=new help2model();
int pdfpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showhelpingmaterial);
        getSupportActionBar().setTitle("HelpingMaterial");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pdfview=findViewById(R.id.viewpdf);
        pdfpos=getIntent().getIntExtra("pdfpos",0);
        help2model hm=helpmaterial2.helparray.get(pdfpos);
        ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("opening.....");
        pdfview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            pd.dismiss();
            }
        });


        String url="";
        try {
            url= URLEncoder.encode(hm.getFileurl(),"UTF-8");

        }catch (Exception ec){}
        pdfview.getSettings().setLoadsImagesAutomatically(true);
        pdfview.getSettings().setJavaScriptEnabled(true);
        pdfview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        pdfview.setWebViewClient(new WebViewClient());
    pdfview.loadUrl("http://docs.google.com/viewer?url="+url);

    }
}