package com.lunacoding.aiar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WebView webview = findViewById(R.id.webview);

		webview.getSettings().setJavaScriptEnabled(true);
		webview.setWebViewClient(new PurchaseWebViewClient());
		//webview.addJavascriptInterface(new MyJavaScriptInterface(), "android");
		webview.loadUrl("https://master.d1njizxeex4377.amplifyapp.com/ar-playground/");
	}

	private class PurchaseWebViewClient extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
		}
	}
	/*class MyJavaScriptInterface {
		@JavascriptInterface
		public void onUrlChange(String url) {
			Uri uri = Uri.parse(url);
			String pg_token = uri.getQueryParameter("pg_token");

			if (pg_token != null)
				approve(invoiceId, pg_token);
		}

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url != null && url.startsWith("intent://")) {
				try {
					Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
					Intent existPackage = getPackageManager().getLaunchIntentForPackage(intent.getPackage());
					if (existPackage != null) {
						startActivity(intent);
					} else {
						Intent marketIntent = new Intent(Intent.ACTION_VIEW);
						marketIntent.setData(Uri.parse("market://details?id=" + intent.getPackage()));
						startActivity(marketIntent);
					}
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (url != null && url.startsWith("market://")) {
				try {
					Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
					if (intent != null) {
						startActivity(intent);
					}
					return true;
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
			view.loadUrl(url);
			return false;
		}
	}*/

}
