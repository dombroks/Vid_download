package com.projects.younes_belouche.vid_download;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Bundle extras;
    private Button video;
    private Button song;
    private WebView webView;
    private String YOUTUBE_ID;
    private String API_WEBSITE = "https://www.yt-download.org/@api/button/mp3/";
    private String API_WEBSITE_VIDEO = "https://www.yt-download.org/@api/button/videos/";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.video);
        song = findViewById(R.id.song);

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDisplayZoomControls(false);

        extras = getIntent().getExtras();

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extras != null) {
                    YOUTUBE_ID = extras.getString(Intent.EXTRA_TEXT).toString();
                    YOUTUBE_ID = extractYTId(YOUTUBE_ID);
                    String download_video = API_WEBSITE_VIDEO + YOUTUBE_ID;
                   // webView.loadUrl(download_video);
                    Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(download_video));
                    startActivity(browserIntent);


                } else
                    Toast.makeText(MainActivity.this, "No video found", Toast.LENGTH_LONG).show();


            }
        });
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extras != null) {
                    YOUTUBE_ID = extras.getString(Intent.EXTRA_TEXT).toString();
                    YOUTUBE_ID = extractYTId(YOUTUBE_ID);
                    String download_song = API_WEBSITE + YOUTUBE_ID;
                    webView.loadUrl(download_song);
                } else
                    Toast.makeText(MainActivity.this, "No video found", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static String extractYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()) {
            vId = matcher.group(1);
        }
        return vId;
    }


}
