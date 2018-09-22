package utils;

import java.io.*;
import java.net.URL;

/**
 * @author ：ZhangLei
 * @date ：2018/9/11
 * @description :
 */
public class IOUtils {

    public static final byte[] inputToByte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    public static ByteArrayOutputStream convent(InputStream in) throws Exception {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            swapStream.write(ch);
        }
        return swapStream;
    }

    public static String getEnumJson(){
        String laststr = "";
        try(InputStream in = IOUtils.class.getResourceAsStream("/resource/enum.json");
            InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);) {
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
        } catch (IOException e) {
            UI.alertErrorMessage(String.format("获取通用枚举JSON文件解析出错:%s",e));
        }
        return laststr;
    }

    public static void setEnumJson(String json){
        try{
            URL resource = IOUtils.class.getResource("/resource/enum.json");
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(resource.getFile()), "utf-8"));
            writer.write("");
            writer.flush();
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            UI.alertErrorMessage(String.format("写入通用枚举JSON文件解析出错:%s",e));
            e.printStackTrace();
        }
    }
}
