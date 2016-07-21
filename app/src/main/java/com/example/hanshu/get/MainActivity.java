package com.example.hanshu.get;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    Button submit,sub_post;
    EditText name, psd;
    String body;
    String pa;
    String str;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        psd = (EditText) findViewById(R.id.psd);
        submit = (Button) findViewById(R.id.sub);
        sub_post= (Button) findViewById(R.id.post);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            body = name.getText().toString().trim();
                            pa = psd.getText().toString().trim();
                            String path = "http://10.39.0.242:8080/test/post?name="
                                    + URLEncoder.encode(body,"utf-8")
                                    + "&psd=" +URLEncoder.encode(pa,"utf-8");
                            URL url = new URL(path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.setConnectTimeout(5000);
                            int code = conn.getResponseCode();
                            if (code == 200) {
                                InputStream in = conn.getInputStream();
                                str = ToolStream.stream(in);
                                if (str.equals("")) {
                                   runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           Toast.makeText(MainActivity.this,str,
                                                   Toast.LENGTH_SHORT).show();
                                       }
                                   });
                                }else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT)
                                                    .show();
                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    sub_post.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    body = name.getText().toString().trim();
                    pa = psd.getText().toString().trim();
                    String path = "http://10.39.0.242:8080/test/post";
                    URL url = null;
                    try {
                        url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setConnectTimeout(5000);
                        String data="name="
                                + URLEncoder.encode(body,"utf-8")
                                + "&psd=" +URLEncoder.encode(pa,"utf-8");
                        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                        conn.setRequestProperty("Content-Length",data.length()+"");
                        conn.setDoInput(true);
                        OutputStream os=conn.getOutputStream();
                        os.write(data.getBytes());
                        int code = conn.getResponseCode();
                        if (code == 200) {
                            InputStream in = conn.getInputStream();
                            str = ToolStream.stream(in);
                            if (str.equals("")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,str,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    });

    }
}
