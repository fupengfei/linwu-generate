package utils;

import bean.EnumBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Author 林雾
 * @Date 2018/9/24/024
 * @Description
 */
public class EnumJsonUtils {
    /**json文件中获取的Bean
     * @return
     */
    public static EnumBean[] jsonToEnumBeanArr() {
        String enumJson = IOUtils.getEnumJson();
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        return gson.fromJson(enumJson, EnumBean[].class);
    }
}
