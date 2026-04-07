package com.abualzahra.vip

import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var myWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myWebView = WebView(this)
        setContentView(myWebView)

        // 1. تفعيل كافة خصائص المتصفح المتقدمة
        myWebView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true // ضروري جداً لرسائل الـ Transport
            databaseEnabled = true
            mediaPlaybackRequiresUserGesture = false
            allowFileAccess = true
            allowContentAccess = true
            // تفعيل الـ WebRTC والاتصالات المباشرة
            javaScriptCanOpenWindowsAutomatically = true
        }

        // 2. معالج الصلاحيات المتقدم (WebChromeClient)
        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                // منح الموقع صلاحية الميكروفون والكاميرا والشبكة فوراً
                runOnUiThread {
                    request.grant(request.resources)
                }
            }
        }

        myWebView.webViewClient = WebViewClient()

        // 3. طلب صلاحيات النظام من الهاتف نفسه
        checkPermissions()

        // تحميل موقعك
        myWebView.loadUrl("https://abualzahracom.online")
    }

    private fun checkPermissions() {
        val permissions = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE
        )
        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 101)
        }
    }
}
