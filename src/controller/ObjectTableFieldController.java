package controller;

import bean.Table;
import bean.TableField;
import cache.Cache;
import config.TableConfig;
import contants.Constant;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 林雾
 * @Date 2018/9/24/024
 * @Description
 */
@Getter
@Setter
public class ObjectTableFieldController extends BaseController implements Initializable {

    @FXML
    private TableView objTable;
    @FXML
    private TableColumn tablePic;
    @FXML
    private TableColumn tableName;

    @FXML
    private TableView fieldTable;
    @FXML
    private TableColumn fieldName;
    @FXML
    private TableColumn fieldType;
    @FXML
    private TableColumn fieldOperate;

    public final ObservableList<Table> tableList = FXCollections.observableArrayList();
    public final ObservableList<TableField> tableFieldList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().put(Constant.Controller, Constant.ObjectTableFieldController, this);

        //对应与表格列绑定
        tablePic.setCellValueFactory(new PropertyValueFactory("filedTableView"));
        tableName.setCellValueFactory(new PropertyValueFactory("name"));

        refershTables();
        objTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTableDetail((Table) newValue));

        objTable.setItems(tableList);
        fieldTable.setItems(tableFieldList);
    }

    private void showTableDetail(Table table) {
        fieldName.setCellValueFactory(new PropertyValueFactory("name"));
        fieldType.setCellValueFactory(new PropertyValueFactory("type"));
        fieldOperate.setCellValueFactory(new PropertyValueFactory("fieldButton"));
        refershFieldTables(table);
    }

    public void refershTables() {
        tableList.clear();
        tableFieldList.clear();
        TableConfig tableConfig = (TableConfig) Cache.getGuavaTable().get(Constant.TABLE_CONFIG, Constant.TABLE_CONFIG);
        tableList.addAll(tableConfig.getTables());
    }

    public void refershFieldTables(Table table) {
        if (table == null) {
            return;
        }
        tableFieldList.clear();
        List<TableField> fieldInfoList = table.getFieldInfoList();
        fieldInfoList.forEach(bean -> bean.initFieldButton());
        tableFieldList.addAll(fieldInfoList);
    }
}
