package com.example.basicfunctions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.utils.ResponseBody;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginViewActivity extends AppCompatActivity implements View.OnClickListener {
    String id;
    String title;
    String author;
    String publishTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        author = intent.getStringExtra("author");
        publishTime = intent.getStringExtra("publishTime");

        //隐藏title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //图片点击事件
        ImageView imageView = findViewById(R.id.icon1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginViewActivity.this, ListViewActivity.class);
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
        String token = intent.getStringExtra("token");
        if (token.equals("")) {
            textView2.setText("未登录");
        } else {
            textView2.setText("已登录");
        }

        Button buttonLogin = findViewById(R.id.login);
        Button buttonExit = findViewById(R.id.exit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("responseBody", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sendRequestWithHttpURLConnection();
        //休眠0.5s，防止出现未拿到token就去加载contentview
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("responseBody", Activity.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Intent intent = new Intent(LoginViewActivity.this, ContentViewActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("author", author);
        intent.putExtra("publishTime", publishTime);
        startActivity(intent);

        //弹出ProgressDialog
        ProgressDialog progressDialog = new ProgressDialog(LoginViewActivity.this);
        progressDialog.setMessage("登录中...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://vcapi.lvdaqian.cn/login");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    EditText username = findViewById(R.id.username);
                    EditText password = findViewById(R.id.password);
                    out.writeBytes("username=" + username.getText() + "&&" + "password=" + password.getText());
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    // 解析response
                    int code = 0;
                    String message = "";
                    JSONObject object = new JSONObject(response.toString());
                    code = object.getInt("code");
                    message = object.getString("message");
                    String token = object.getString("token");
                    ResponseBody responseBody = new ResponseBody(code, message, token);
                    //获取SharedPreference对象
                    SharedPreferences sharedPreferences = getSharedPreferences("responseBody", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", responseBody.getToken());
                    editor.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}