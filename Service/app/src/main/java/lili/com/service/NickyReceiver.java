package lili.com.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by xuyating on 2017/4/23.
 */

public class NickyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");
        String time = intent.getStringExtra("time");
        Log.i("NickyReceiver", "name" + name + "time" + time);
    }

}
