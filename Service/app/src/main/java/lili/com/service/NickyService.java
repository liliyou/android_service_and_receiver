package lili.com.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

/**
 * Created by xuyating on 2017/4/23.
 */

public class NickyService extends Service {
    // 發送廣播事件用的Action名稱
//     <receiver android:name="com.winho.ble.step.NickyReceiver">
//            <intent-filter>
//                <!-- 使用Action名稱設定接收的廣播事件 -->
//                <action android:name=
//            "com.example.dd" />
//            </intent-filter>
//        </receiver>
    public final static String ACTION_GATT_CONNECTED = "com.example.dd";

    public static Boolean hasCallbacks = false;

    private Handler handler = new Handler();

    public void connect() {
        handler.postDelayed(showTime, 1000);
    }

    public void disconnect() {
        handler.removeCallbacks(showTime);
    }

    private Runnable showTime = new Runnable() {
        public void run() {
            //如果退到背景就關閉Service 動作
            if (ConstantApplication.wasInBackground) {
                disconnect();
                hasCallbacks = false;

            } else {
                hasCallbacks = true;
                Intent intent = new Intent(ACTION_GATT_CONNECTED);
                intent.putExtra("name", "binder");
                intent.putExtra("time", new Date().toString());
                sendBroadcast(intent);

                handler.postDelayed(this, 1000);
            }
        }
    };

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public NickyService getService() {
            return NickyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        //綁定
        Log.i("binder", "mBinder executed");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.i("binder", "onUnbind executed");
        return super.onUnbind(intent);
    }
}


