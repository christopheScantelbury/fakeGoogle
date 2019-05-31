package com.example.my_browser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    private Button botaoBuscar;

    private Button botaoVoltar;

    private Button botaoAvancar;

    private String prefixoUrl = "https://www.google.com.br/search?hl=pt-BR&source=hp&q=";

    private EditText campoBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = findViewById(R.id.my_browser);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        botaoBuscar = findViewById(R.id.buscar);
        botaoVoltar = findViewById(R.id.voltar);
        botaoAvancar = findViewById(R.id.avancar);
        campoBusca = findViewById(R.id.campoBusca);
        myWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) { //fala para o webview responder as requisições

                    return false;



            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE); // mostra a progress
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE); // esconde a progress
            }
        });


        myWebView.loadUrl("https://google.com.br/"); //define o url inicial
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); //permite o uso de javaScript no webView


        botaoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myWebView.loadUrl(prefixoUrl+campoBusca.getText());
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.goBack();
            }
        });

        botaoAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.goForward();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //permite que o botão fisico de voltar seja usado como goBack do webView.
        if(keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
