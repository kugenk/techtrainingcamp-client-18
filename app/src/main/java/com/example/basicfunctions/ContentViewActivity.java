package com.example.basicfunctions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.basicfunctions.entity.markdown.MarkdownLine;
import com.example.basicfunctions.entity.markdown.MarkdownLineComponent;
import com.example.utils.MarkdownParser;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ContentViewActivity extends AppCompatActivity {

    private String authorization;
    private String requestURL;
    private String message;
    private String data;
    private String token;
    private String id;
    private int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_view);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String publishTime = intent.getStringExtra("publishTime");
        System.out.println(token);
        sendRequestWithHttpURLConnection();
        final LinearLayout linearLayout = findViewById(R.id.content);

        //隐藏title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //设置箭头图片点击事件
        ImageView arrowView = findViewById(R.id.arrow);
        arrowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentViewActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        //检测状态码
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (code != 200) {
            TextView alarm = new TextView(this);
            alarm.setText(message);
            alarm.setTextSize(20);
            linearLayout.addView(alarm);
        } else {
            TextView titleTextView = new TextView(this);
            titleTextView.setText(title);
            titleTextView.setTextSize(24);
            titleTextView.setTextColor(Color.BLACK);
            titleTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            linearLayout.addView(titleTextView);

            TextView authorTextView = new TextView(this);
            authorTextView.setText(author);
            authorTextView.setTextSize(16);
            authorTextView.setTextColor(Color.BLACK);
            linearLayout.addView(authorTextView);

            TextView publishTimeTextView = new TextView(this);
            publishTimeTextView.setText(publishTime);
            publishTimeTextView.setTextSize(14);
            linearLayout.addView(publishTimeTextView);

            MarkdownParser markdownParser = new MarkdownParser();
            List<MarkdownLine> markdownLineList = markdownParser.parseArticleData(data);
            for (MarkdownLine markdownLine : markdownLineList) {
                int textSize = 0;
                if (markdownLine.getFormatLevel() == 0) {
                    textSize = 20;
                }
                if (markdownLine.getFormatLevel() == 1) {
                    textSize = 22;
                }
                if (markdownLine.getFormatLevel() == 2) {
                    textSize = 21;
                }
                for (MarkdownLineComponent markdownLineComponent : markdownLine.getComponents()) {
                    if (markdownLineComponent.getType() == MarkdownLineComponent.MarkdownComponentType.Text) {
                        TextView textView = new TextView(this);
                        textView.setText(markdownLineComponent.getText());
                        textView.setTextSize(textSize);
                        textView.setTextColor(Color.BLACK);
                        linearLayout.addView(textView);
                    }
                    if (markdownLineComponent.getType() == MarkdownLineComponent.MarkdownComponentType.Image) {
                        ImageView imageView = new ImageView(this);
                        int resId = this.getResources().getIdentifier(removeSuffix(markdownLineComponent.getImagePath()), "mipmap", this.getPackageName());
                        imageView.setImageResource(resId);
                        linearLayout.addView(imageView, 1000, 1000);
                    }
                    if (markdownLineComponent.getType() == MarkdownLineComponent.MarkdownComponentType.Hyperlink) {
                        TextView textView = new TextView(this);
                        textView.setText(markdownLineComponent.getText());
                        textView.setAutoLinkMask(Linkify.ALL);
                        textView.setMovementMethod(LinkMovementMethod.getInstance());
                        linearLayout.addView(textView);
                    }
                }
            }
        }
    }

    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                requestURL = "https://vcapi.lvdaqian.cn/article/" + id + "?markdown=true";
                authorization = "Bearer" + " " + token;
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(requestURL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Authorization", authorization);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    code = connection.getResponseCode();
                    message = connection.getResponseMessage();
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //读取data对象
                    JSONObject object = new JSONObject(response.toString());
                    data = object.getString("data");
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

    private String removeSuffix(String pre) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pre.length(); i++) {
            if (pre.charAt(i) == '.') {
                break;
            }
            stringBuilder.append(pre.charAt(i));
        }
        return pre.equals("teamBuilding_04.png") ? "team_04" : stringBuilder.toString();
    }
}




