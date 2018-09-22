package cache;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @author ：ZhangLei
 * @date ：2018/9/18
 * @description :
 */
public class Cache {
    private static final Table<String,Object,Object> guavaTable = HashBasedTable.create();

    public static void setGuavaTable(String rowKey,Object columnKey,Object cell){
        guavaTable.put(rowKey,columnKey,cell);
    }

    public static Table<String,Object,Object> getGuavaTable(){
        return guavaTable;
    }

    public static void main(String[] args) {
        guavaTable.put("1",2,3);
        guavaTable.put("2",22,33);
        guavaTable.put("3",222,333);

        System.out.println(guavaTable.containsColumn(2));
        System.out.println(guavaTable.contains("2",22));
        System.out.println(guavaTable.containsRow("0"));
    }

}
