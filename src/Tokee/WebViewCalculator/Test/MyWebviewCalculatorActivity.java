package Tokee.WebViewCalculator.Test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MyWebviewCalculatorActivity extends Activity {  
	
	WebView myWebView =null;
	Handler mHandler=null;
	
	
	/** Called when the activity is first created. */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Web View
        myWebView = (WebView)findViewById(R.id.webViewCalc);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        //Web View
   
        mHandler = new Handler();
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		myWebView.loadUrl("file:///android_asset/calculator.html");
	}    

	@Override
	protected void onPause() {
		super.onPause();
	}
	
}