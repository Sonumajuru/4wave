package com.fourwave.activities;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon)
    {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        // TODO Auto-generated method stub
        //  multi_per.setVisibility(ProgressBar.GONE);

        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url)
    {
        // TODO Auto-generated method stub
        super.onPageFinished(view, url);
        //  multi_per.setVisibility(ProgressBar.VISIBLE);
    }
}