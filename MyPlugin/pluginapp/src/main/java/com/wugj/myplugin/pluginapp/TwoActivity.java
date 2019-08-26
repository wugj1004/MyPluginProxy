package com.wugj.myplugin.pluginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wugj.myplugin.plugincore.BaseActivity;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/8/22
 * </br>
 * version:
 */
public class TwoActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_two);

        that.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(that,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
