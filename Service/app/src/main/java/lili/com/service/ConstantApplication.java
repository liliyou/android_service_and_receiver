package lili.com.service;

import android.app.Application;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xuyating on 2017/4/30.
 */

public class ConstantApplication extends Application {

    //ActivityTimer
    public static boolean wasInBackground = true;
    private final long MAX_ACTIVITY_TRANSITION_TIME_MS = 2000;
    private Timer mActivityTransitionTimer;
    private TimerTask mActivityTransitionTimerTask;

    public void startActivityTransitionTimer() {

        this.mActivityTransitionTimer = new Timer();
        this.mActivityTransitionTimerTask = new TimerTask() {
            public void run() {
                wasInBackground = true;
            }
        };

        this.mActivityTransitionTimer
                .schedule(mActivityTransitionTimerTask, MAX_ACTIVITY_TRANSITION_TIME_MS);
    }

    public void stopActivityTransitionTimer() {
        if (this.mActivityTransitionTimerTask != null) {
            this.mActivityTransitionTimerTask.cancel();
        }

        if (this.mActivityTransitionTimer != null) {
            this.mActivityTransitionTimer.cancel();
        }

        this.wasInBackground = false;
    }
}
