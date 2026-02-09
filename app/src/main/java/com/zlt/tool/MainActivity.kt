package com.zlt.tool

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var web: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        web = WebView(this)
        setContentView(web)

        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true

        web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                injectTool()
            }
        }

        web.loadUrl("http://192.168.8.1")
    }

    private fun injectTool() {
        val js = `
(function () {
  if (document.getElementById("zlt-lock-ui")) return;
  const box = document.createElement("div");
  box.id="zlt-lock-ui";
  box.style="position:fixed;top:20px;left:20px;background:black;color:#0f0;padding:10px;z-index:99999;border:1px solid #0f0";
  box.innerHTML="ZLT Tool Injected - Ready";
  document.body.appendChild(box);
})();
`
        web.evaluateJavascript(js, null)
    }
}