package bean;

import contants.DbColumnType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ：ZhangLei
 * @date ：2018/8/22
 * @description :
 */
@Getter
@Setter
public class TableField {
    private String tableName;
    private boolean isKey;
    private String name;
    private String type;
    private DbColumnType columnType;
    private String comment;
    private String tabObj;
    private ListView listView;

    public void initListView(){
        ObservableList<String> strList = FXCollections.observableArrayList("红色","黄色","绿色");
        this.listView = new ListView();
        this.listView.setItems(strList);
    }
}
