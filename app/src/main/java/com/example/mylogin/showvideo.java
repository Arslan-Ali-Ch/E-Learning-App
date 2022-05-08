package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class showvideo extends AppCompatActivity {
    VideoView showv;
    ArrayList<video2model> vi=new ArrayList<>();
    int vpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvideo);
        getSupportActionBar().setTitle("VideoLectures");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        MediaController mediacontroller =  new MediaController(this);
        showv=findViewById(R.id.showvideoView);
        showv.setMediaController(mediacontroller);
        mediacontroller.setAnchorView(showv);
        String videourl=getIntent().getStringExtra("vurl");
        vpos=getIntent().getIntExtra("vpos",0);
        
        video2model vm=video2.video2array.get(vpos);
         Uri uri = Uri.parse(vm.getVideourl());
        showv.setVideoURI(uri);
        showv.start();

    }
}