package exam.android.norberthelmuth.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

    private String url;
    private ProgressBar mProgressBar;
    private static int PROGRESS_TIME_OUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        url = intent.getStringExtra("article_url");
        WebView webView = findViewById(R.id.activity_web);
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setMax(PROGRESS_TIME_OUT);

        loadWebViewLoad(webView);
    }

    private void loadWebViewLoad(WebView webview) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {

                mProgressBar.setProgress(newProgress);
                if (newProgress == PROGRESS_TIME_OUT) {
                    mProgressBar.setVisibility(view.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webview.loadUrl(this.url);
    }
}
