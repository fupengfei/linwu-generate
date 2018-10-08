package utils;

import bean.EnumBean;
import com.google.common.io.Files;
import com.google.gson.Gson;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

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

    public final static String JsonFilePath = String.format("%s%s", System.getProperty("user.dir"), "/src/resource/enum.json");

    public static String getEnumJson(){
        try{
            List<String> list = Files
                    .readLines(new File(JsonFilePath), Charset.forName("utf-8"));
            return list.stream().collect(Collectors.joining());
        } catch (IOException e) {
            UiUtils.alertErrorMessage(String.format("获取通用枚举JSON文件解析出错:%s",e));
        }
        return "";
    }

    public static void setEnumJson(String json){
        try{
            Files.write(json,new File(JsonFilePath), Charset.forName("utf-8"));
        } catch (Exception e) {
            UiUtils.alertErrorMessage(String.format("写入通用枚举JSON文件解析出错:%s",e));
        }
    }
}
