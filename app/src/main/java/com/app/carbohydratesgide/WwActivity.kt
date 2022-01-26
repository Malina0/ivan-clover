package com.app.carbohydratesgide

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import com.app.carbohydratesgide.databinding.ActivityWwBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val RESULT_CODE = 1
class WwActivity : AppCompatActivity() {
    private lateinit var w: WebView
    private lateinit var link: String
    private lateinit var activity: Activity
    private var uploadMessage: ValueCallback<Array<Uri>>? = null

    private var redirect = false

    private lateinit var binding: ActivityWwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activity = this
        link = intent.getStringExtra("url") ?: ""

        if (link.startsWith("http"))
            open(link)
        else
            timer()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun open(s: String){
        w = WebView(this)
        binding.root.addView(w)
        w.webChromeClient = MyClient()
        w.webViewClient = MyWebClient()

        w.settings.javaScriptEnabled = true
        w.settings.domStorageEnabled = true

        w.settings.loadWithOverviewMode = true
        w.settings.useWideViewPort = true
        w.settings.allowFileAccess = true
        w.settings.allowContentAccess = true

        CookieManager.getInstance().setAcceptCookie(true)
        w.loadUrl(s)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    private fun timer(){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                Thread.sleep(790)
            }
            if (!redirect){
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }

    private inner class MyWebClient : WebViewClient() {
        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            if (request.url.toString() != link)
                redirect = true
            if (request.url.toString().startsWith("http"))
                view.loadUrl(request.url.toString())
            else
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        request.url
                    )
                )
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url != link)
                redirect = true
            if (url.startsWith("http"))
                view.loadUrl(url)
            else
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    )
                )
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (url == link){
                timer()
            }
        }
    }

    private inner class MyClient : WebChromeClient() {
        override fun onShowFileChooser(
            webView: WebView,
            filePathCallback: ValueCallback<Array<Uri>>,
            fileChooserParams: FileChooserParams
        ): Boolean {
            uploadMessage = filePathCallback
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            activity.startActivityForResult(
                Intent.createChooser(intent, "File Chooser"),
                RESULT_CODE
            )
            return true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == RESULT_CODE) {
            if (uploadMessage == null) return
            uploadMessage!!.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent))
            uploadMessage = null
        }
    }

    private lateinit var dialog: AlertDialog
    override fun onBackPressed() {
        if (w.canGoBack()) w.goBack()
        else{
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Exit")
            builder.setMessage("Are you sure?")
            builder.setPositiveButton("Yes"
            ) { _, _ -> finish() }
            builder.setNegativeButton("No"
            ) { _, _ -> dialog.dismiss() }
            dialog = builder.create()
            dialog.show()
        }
    }
}