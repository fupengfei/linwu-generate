package contants;

import java.util.Arrays;

/**
 * @author ：ZhangLei
 * @date ：2018/9/18
 * @description :
 */
public enum JavaTypeEnum {
    String, Integer, Long;

    private JavaTypeEnum typeEnum;

    public static boolean exist(String key){
        return Arrays.stream(JavaTypeEnum.values()).anyMatch(bean->bean.name().equals(key));
    }


}
