package utils;

import contants.Constant;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;

/**
 * @Author 林雾
 * @Date 2018/10/2/002
 * @Description
 */
public class SystemUtils {
    public static void open(String outDir) {
        try {
            String osName = System.getProperty(Constant.OS_NAME);
            if (osName != null) {
                if (osName.contains(Constant.Mac)) {
                    Runtime.getRuntime().exec(Constant.MAC_OPEN + outDir);
                } else if (osName.contains(Constant.Windows)) {
                    Runtime.getRuntime().exec(Constant.Windows_OPEN + outDir);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String OS() {
        String osName = System.getProperty(Constant.OS_NAME);
        if (StringUtils.isNotBlank(osName)) {
            if (osName.contains(Constant.Mac)) {
                return Constant.Mac;
            } else if (osName.contains(Constant.Windows)) {
                return Constant.Windows;
            }
        }
        return null;
    }
}