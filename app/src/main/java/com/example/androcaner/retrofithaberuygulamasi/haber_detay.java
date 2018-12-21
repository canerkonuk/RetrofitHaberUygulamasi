package com.example.androcaner.retrofithaberuygulamasi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class haber_detay extends AppCompatActivity {

    WebView webView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber_detay);


        webView=findViewById(R.id.habersitesi);
       
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Bundle recyclerbundle=getIntent().getExtras();
        webView.loadUrl(recyclerbundle.getString("URL"));

        Bundle kenburnsbundle=getIntent().getExtras();
        webView.loadUrl(kenburnsbundle.getString("ENÜSTTEKİHABERURL"));


    }
}
