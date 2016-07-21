package com.example.hanshu.get;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HanShu on 2016/7/21.
 */
public class ToolStream {

    public static String stream(InputStream in) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                baos.write(b, 0, b.length);
            }
            in.close();
            byte[] result = baos.toByteArray();
            String inn=new String(result);
            return inn;
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }

    }

}
