package io.programmes_radio.www.progradio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private Date lastRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new MyAppWebViewClient());
        mWebView.loadUrl("https://www.programmes-radio.io");

        this.lastRefresh = new Date();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        Date now = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        if (fmt.format(this.lastRefresh).equals(fmt.format(now))) {
            mWebView.reload();
            this.lastRefresh = now;
        }
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
