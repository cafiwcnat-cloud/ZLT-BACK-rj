package com.zlt.fullpro

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
// FULL PRO TOOL PLACEHOLDER
(function () {
  if (document.getElementById("zlt-lock-ui")) return;

  const ui = document.createElement("div");
  ui.id="zlt-lock-ui";
  ui.style="position:fixed;top:60px;left:20px;background:#000;color:#0f0;padding:12px;z-index:99999;border:2px solid #0f0;font-family:monospace;border-radius:8px";
  ui.innerHTML=`
    <b>ZLT FULL PRO TOOL</b><br><br>
    Status: READY<br><br>
    <button onclick="alert('Lock Command Placeholder')">LOCK</button>
    <button onclick="alert('Unlock Command Placeholder')">UNLOCK</button>
  `;
  document.body.appendChild(ui);
})();
`
        web.evaluateJavascript(js, null)
    }
}