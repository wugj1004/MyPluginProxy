package com.wugj.myplugin.pluginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wugj.myplugin.plugincore.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        that.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(that, "第三方插件页面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(that,TwoActivity.class);
                startActivity(intent);
            }
        });
    }
}
