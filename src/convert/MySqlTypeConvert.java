package convert;


import contants.DbColumnType;

public class MySqlTypeConvert {

    public DbColumnType processTypeConvert(String fieldType) {
        String t = fieldType.toLowerCase();
        if (t.contains("char") || t.contains("text")) {
            return DbColumnType.STRING;
        } else if (t.contains("bigint")) {
            return DbColumnType.LONG;
        } else if (t.contains("tinyint(1)")) {
            return DbColumnType.BOOLEAN;
        } else if (t.contains("int")) {
            return DbColumnType.INTEGER;
        } else if (t.contains("text")) {
            return DbColumnType.STRING;
        } else if (t.contains("bit")) {
            return DbColumnType.BOOLEAN;
        } else if (t.contains("decimal")) {
            return DbColumnType.BIG_DECIMAL;
        } else if (t.contains("clob")) {
            return DbColumnType.CLOB;
        } else if (t.contains("blob")) {
            return DbColumnType.BLOB;
        } else if (t.contains("binary")) {
            return DbColumnType.BYTE_ARRAY;
        } else if (t.contains("float")) {
            return DbColumnType.FLOAT;
        } else if (t.contains("double")) {
            return DbColumnType.DOUBLE;
        } else if (t.contains("json") || t.contains("enum")) {
            return DbColumnType.STRING;
        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
            switch (t) {
                case "date":
                    return DbColumnType.LOCAL_DATE;
                case "time":
                    return DbColumnType.LOCAL_TIME;
                case "year":
                    return DbColumnType.YEAR;
                default:
                    return DbColumnType.LOCAL_DATE_TIME;
            }
        }
        return DbColumnType.STRING;
    }
}
