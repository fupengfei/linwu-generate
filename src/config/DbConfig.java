package config;

import contants.DBTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;

@Getter
@Setter
public class DbConfig {

    private Connection connection;

    public void init(DBTypeEnum dbTypeEnum, String url, String username, String password)
            throws Exception {
        Class.forName(dbTypeEnum.getValue()).newInstance();
        StringBuilder builder = new StringBuilder();
        builder.append(url).append("?user=").append(username).append("&").append("password=").append(password);
        connection = DriverManager.getConnection(builder.toString());
    }
}
