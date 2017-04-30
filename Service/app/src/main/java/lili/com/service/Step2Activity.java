package lili.com.service;

import android.os.Bundle;
import android.view.Window;

/**
 * Created by xuyating on 2017/4/23.
 */

public class Step2Activity extends BaseTestActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設置無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 設置形象頁面
        setContentView(R.layout.activity_main);


    }

}
