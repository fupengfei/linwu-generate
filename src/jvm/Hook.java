package jvm;

import bean.EnumBean;
import cache.Cache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contants.Constant;
import utils.IOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author 林雾
 * @Date 2018/9/23/023
 * @Description
 */
public class Hook {
    public static void start(){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Map<Object, Object> row = Cache.getGuavaTable().row(Constant.ENUM_JSON);
                List<EnumBean> list = new ArrayList<>();
                row.forEach((k,v)->{
                    list.add((EnumBean) v);
                });

                GsonBuilder builder = new GsonBuilder();
                builder.excludeFieldsWithoutExposeAnnotation();
                Gson gson = builder.create();
                String json = gson.toJson(list);
                IOUtils.setEnumJson(json);
            }
        };


        //TODO 缓存新增枚举写入文件
        Runtime.getRuntime().addShutdownHook(new Thread(run));
    }
}
