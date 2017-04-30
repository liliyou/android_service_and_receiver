package lili.com.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by xuyating on 2017/4/23.
 */

public class BaseTestActivity extends Activity {
    protected static NickyService nickyService;

    protected NickyReceiver receiver = new NickyReceiver();

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder service) {

            Log.i("binder", "onServiceConnected");
            if (nickyService != null && !nickyService.hasCallbacks) {
                nickyService = null;
            }
            if (nickyService == null) {
                nickyService = ((NickyService.LocalBinder) service).getService();
                nickyService.connect();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            Log.i("binder", "onServiceDisconnected");
            nickyService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        stopTimer();
        bindService();
        registerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService();
    }

    @Override
    protected void onPause() {
        startTimer();
        unregisterReceiver();
        super.onPause();
    }


    public void startTimer() {
        ((ConstantApplication) getApplication()).startActivityTransitionTimer();

    }

    public void stopTimer() {
        ((ConstantApplication) getApplication()).stopActivityTransitionTimer();
    }


    public void registerReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        // 註冊廣播接收元件
        registerReceiver(receiver, filter);
    }

    public void unregisterReceiver() {

        // 移除廣播接收元件
        unregisterReceiver(receiver);
    }


    public void bindService() {
        Intent gattServiceIntent = new Intent(this, NickyService.class);
        //綁定
        Log.i("binder", "mBinder executed");
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);

    }

    public void unbindService() {

        Log.i("unbindService", "unbindService executed");
        unbindService(mServiceConnection);
    }


}
