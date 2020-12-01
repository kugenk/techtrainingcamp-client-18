package com.example.basicfunctions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.basicfunctions.adapter.BasicFuncAdapter;
import com.example.basicfunctions.entity.MetaData;
import com.example.utils.JSONParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private List<MetaData> metaDataList;
    private Context mContext;
    private BasicFuncAdapter basicFuncAdapter = null;
    private ListView listView;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);

        //获取SharedPreference对象
        SharedPreferences sharedPreferences = getSharedPreferences("responseBody", Activity.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        //隐藏title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //图片点击事件
                ImageView imageView = findViewById(R.id.icon2);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListViewActivity.this, LoginViewActivity.class);
                        intent.putExtra("token", token);
                        startActivity(intent);
                    }
                });
                //设置点击事件下方文字
                TextView textView1 = findViewById(R.id.toutiao);
                textView1.setText("头条");
                textView1.setTextSize(10);
                textView1.setTextColor(Color.BLACK);

                TextView textView2 = findViewById(R.id.account);
                textView2.setTextSize(10);
                textView2.setTextColor(Color.BLACK);
                if (token.equals("")) {
                    textView2.setText("未登录");
                } else {
                    textView2.setText("已登录");
                }
            }
        }).start();


        mContext = this;
        listView = (ListView) findViewById(R.id.listview);

        //解析Json数据
        try {
            AssetManager assetManager = getAssets();
            InputStreamReader inputStreamReader;
            inputStreamReader = new InputStreamReader(assetManager.open("metadata.json"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            //jackson解析
            JSONParser jsonParser = new JSONParser();
            metaDataList = jsonParser.parseMetadataToList(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        basicFuncAdapter = new BasicFuncAdapter(metaDataList, mContext);
        listView.setAdapter(basicFuncAdapter);

        //注册ListView监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if (token.equals("")) {
                    intent = new Intent(ListViewActivity.this, LoginViewActivity.class);
                } else {
                    intent = new Intent(ListViewActivity.this, ContentViewActivity.class);
                }
                intent.putExtra("token", token);
                intent.putExtra("id", metaDataList.get(position).getId());
                intent.putExtra("title", metaDataList.get(position).getTitle());
                intent.putExtra("publishTime", metaDataList.get(position).getPublishTime());
                intent.putExtra("author", metaDataList.get(position).getAuthor());
                startActivity(intent);
            }
        });
    }
}