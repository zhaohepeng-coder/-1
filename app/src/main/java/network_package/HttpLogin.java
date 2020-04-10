package network_package;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class HttpLogin {
    public static String LoginByPost(String id,String password)
    {
         String address = "";
         String result="";
         try{
             URL url = new URL(address);
             HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
             con.setRequestMethod("POST");
             con.setReadTimeout(5000);
             con.setConnectTimeout(5000);
             con.setRequestProperty("Content-Type","application/json;charset=UTF-8");
             con.setUseCaches(false);
             con.setDoInput(true);
             con.setDoOutput(true);
             JSONObject json_obj = new JSONObject();
             try {
                 json_obj.put("account",id);
                 json_obj.put("password",password);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             String content = String.valueOf(json_obj);

             OutputStream out = con.getOutputStream();
             out.write(content.getBytes());
             out.flush();
             out.close();
             con.connect();
             if(con.getResponseCode()==200)
             {
                 InputStream is =con.getInputStream();
                 ByteArrayOutputStream message = new ByteArrayOutputStream();
                 int len = 0;
                 byte buffer[] = new byte[1024];
                 while ((len =is.read(buffer))!=-1)
                 {
                     message.write(buffer, 0, len);
                 }

                 is.close();
                 message.close();
                 // 返回字符串
                 result = new String(message.toByteArray());

             }

         }
         catch (MalformedURLException e){
               e.printStackTrace();
         }
         catch (IOException e){
             e.printStackTrace();
         }
         return result;
    }





    public static String RegisterByPost(String id,String password,String name){
        String address = "";
        String result = "";

        try{
            URL url = new URL(address);//初始化URL
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//请求方式

            //超时信息
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            //post方式不能设置缓存，需手动设置为false
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            //我们请求的数据


            conn.setDoInput(true);
            conn.setDoOutput(true);
            JSONObject json_obj = new JSONObject();
            try {
                json_obj.put("id",id);
                json_obj.put("name",name);
                json_obj.put("password",password);

            } catch (JSONException e) {
                e.printStackTrace();
            }
               String content = String.valueOf(json_obj);

            //获取输出流
            OutputStream out = conn.getOutputStream();

            out.write(content.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                result = new String(message.toByteArray());
                //return result;
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;

    }
}



