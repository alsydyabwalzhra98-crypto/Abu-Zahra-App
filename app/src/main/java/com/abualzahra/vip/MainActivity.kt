package com.abualzahra.vip

import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = WebView(this)
        setContentView(webView)

        webView.settings.apply {
            javaScriptEnabled = true // تفعيل الجافا سكريبت
            domStorageEnabled = true // تفعيل التخزين المحلي (ضروري للاتصال)
            databaseEnabled = true
            mediaPlaybackRequiresUserGesture = false // السماح بتشغيل الصوت تلقائياً
            allowFileAccess = true
            allowContentAccess = true
        }

        // معالج لطلب صلاحيات الميكروفون داخل الـ WebView
        webView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }
        }

        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://your-website-link.com") // استبدل هذا برابط موقعك الحقيقي
    }
}
