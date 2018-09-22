package contants;

import java.util.Arrays;

/**
 * @author ：ZhangLei
 * @date ：2018/9/18
 * @description :
 */
public enum DBTypeEnum {
    MYSQL("Mysql","com.mysql.jdbc.Driver","jdbc:mysql://");

    private String name;
    private String value;
    private String prefix;

    DBTypeEnum(String name, String value,String prefix) {
        this.name = name;
        this.value = value;
        this.prefix = prefix;
    }

    public static boolean exist(String name){
        return Arrays.stream(DBTypeEnum.values()).anyMatch(bean->bean.name().equals(name));
    }

    public static String getTypeValue(String name) {
        return Arrays.stream(DBTypeEnum.values()).filter(obj -> obj.getName().equals(name))
                .map(obj -> obj.getValue()).findFirst().orElse(null);
    }

    public static String getTypePrefix(String prefix) {
        return Arrays.stream(DBTypeEnum.values()).filter(obj -> obj.getPrefix().equals(prefix))
                .map(obj -> obj.getPrefix()).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getPrefix() {
        return prefix;
    }
}
