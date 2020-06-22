package network_package;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class HttpLogin {
    public static String LoginByPost(String id,String password)
    {
         String address = "http://39.99.186.221:80/ZulinTongAfterEnd_v1.0/test";
         String result="";
         try{
             URL url = new URL(address);
             HttpURLConnection con = (HttpURLConnection) url.openConnection();
             con.setRequestMethod("POST");
             con.setReadTimeout(8000);
             con.setConnectTimeout(8000);
             //con.setRequestProperty("Content-Type","application/json;charset=UTF-8");
             con.setUseCaches(false);
             JSONObject json_obj = new JSONObject();
             try {
                 json_obj.put("account",id);
                 json_obj.put("password",password);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             String content = String.valueOf(json_obj);

             OutputStream out =(OutputStream) con.getOutputStream();
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
                System.out.println(result);
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





    public static String RegisterByPost(String id,String password){
        String address = "http://39.99.186.221:80/ZulinTongAfterEnd_v1.0/test";
        String result = "";

        try{
            URL url = new URL(address);//初始化URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
            return  e.toString();
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;

    }
    public static String checkuser(String id,String password){
        JSONObject json_obj = new JSONObject();
        try {
            json_obj.put("account",id);
            json_obj.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params =json_obj.toString();
        try {
            URL url=new URL("http://39.99.186.221:80/ZulinTongAfterEnd_v1.0/test");
            HttpURLConnection connect=(HttpURLConnection)url.openConnection();
            connect.setDoInput(true);
            connect.setDoOutput(true);
            connect.setRequestMethod("POST");
            connect.setUseCaches(false);
            connect.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            OutputStream outputStream = connect.getOutputStream();
            outputStream.write(params.getBytes());
            int response = connect.getResponseCode();
            if (response== HttpURLConnection.HTTP_OK)
            {
                System.out.println(response);
                InputStream input=connect.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
            else {
                System.out.println("hjhjujh");
                return " ";
            }
        } catch (Exception e) {
            Log.e("e:", String.valueOf(e));
            return e.toString();
        }
    }
}



