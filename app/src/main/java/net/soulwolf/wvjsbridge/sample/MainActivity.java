package net.soulwolf.wvjsbridge.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.soulwolf.wvjsbridge.WJBridgeHandler;
import net.soulwolf.wvjsbridge.WJBridgeWebView;
import net.soulwolf.wvjsbridge.WJCallbacks;

public class MainActivity extends AppCompatActivity {

    private WJBridgeWebView webView;

    private MenuItem mMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.webView = (WJBridgeWebView) findViewById(R.id.web_view);
        this.webView.loadUrl("file:///android_asset/WebViewJavascriptBridgeDemo.html");
        this.webView.registerHandler("callNative", new WJBridgeHandler() {
            @Override
            public void handler(String data, WJCallbacks callbacks) {
                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                callbacks.onCallback("callNative response data" + System.currentTimeMillis());
            }
        });
    }

    public void callHandlerJSToast(View view){
        this.webView.callHandler("callJs", "{\"callJsData\":\"data\"}", new WJCallbacks() {
            @Override
            public void onCallback(String data) {
                Toast.makeText(getApplicationContext(),"callJs callback" + data,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenuItem = menu.add("QQX5TBS");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item == mMenuItem){
            startActivity(new Intent(this,QQX5TBSActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
