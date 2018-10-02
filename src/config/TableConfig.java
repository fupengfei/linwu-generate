package config;

import bean.Table;
import bean.TableField;
import com.google.common.base.CaseFormat;
import contants.DbColumnType;
import convert.MySqlTypeConvert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import utils.UiUtils;

/**
 * @author ：ZhangLei
 * @date ：2018/8/31
 * @description :
 */
@Getter
@Setter
public class TableConfig {

    private DbConfig dbConfig;
    private List<Table> tables = new ArrayList<>();
    private List<Table> generateTables = new ArrayList<>();

    public TableConfig(DbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public void initTable() {
        try {
            Connection connection = dbConfig.getConnection();
            if (connection == null) {
                UiUtils.alertErrorMessage("未获取数据库连接");
                return;
            }
            PreparedStatement statement = connection.prepareStatement("show table status");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                String tableName = results.getString("Name");
                String comment = results.getString("Comment");
                Table table = new Table();
                table.setName(tableName);
                table.setComment(comment);
                tablefieldExcute(table);
                tables.add(table);
            }
        } catch (SQLException e) {
            UiUtils.alertErrorMessage(String.format("获取数据库表信息失败：%s", e.getMessage()));
        }
    }


    /**
     * 填充 表字段信息
     */
    public void tablefieldExcute(Table table) {
        StringBuilder builder = new StringBuilder();
        builder.append("show full fields from ").append(table.getName());
        try {
            PreparedStatement statement = dbConfig.getConnection().prepareStatement(builder.toString());
            ResultSet results = statement.executeQuery();
            MySqlTypeConvert mysqlTypeConvert = new MySqlTypeConvert();
            while (results.next()) {
                TableField field = new TableField();

                String name = results.getString("Field");
                field.setName(name);

                String key = results.getString("Key");
                if (!StringUtils.isEmpty(key) && "PRI".equals(key.toUpperCase())) {
                    field.setKeyFlag(true);
                }

                String comment = results.getString("Comment");
                field.setComment(comment);

                String type = results.getString("Type");
                field.setTableName(table.getName());
                field.setType(type);

                //数据库类型与Java转换
                DbColumnType dbColumnType = mysqlTypeConvert.processTypeConvert(field.getType());
                field.setColumnType(dbColumnType);
                table.getFieldInfoList().add(field);
            }
        } catch (SQLException e) {
            UiUtils.alertErrorMessage(String.format("获取数据库表信息失败：%s", e.getMessage()));
        }
    }

    public static void main(String[] args) {

        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data").toLowerCase());
    }
}
