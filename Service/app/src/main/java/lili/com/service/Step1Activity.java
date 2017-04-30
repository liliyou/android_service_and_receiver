package lili.com.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

/**
 * Created by xuyating on 2017/4/23.
 */

public class Step1Activity extends BaseTestActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設置無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 設置形象頁面
        setContentView(R.layout.activity_main);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Step1Activity.this,
                        Step2Activity.class);
                startActivity(intent3);
            }
        });
    }

}
