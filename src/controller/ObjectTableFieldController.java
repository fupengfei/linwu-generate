package controller;

import bean.EnumBean;
import bean.EnumProperties;
import bean.Table;
import bean.TableField;
import cache.Cache;
import config.TableConfig;
import contants.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
        Cache.getGuavaTable().put(Constant.Controller, Constant.ObjectTableFieldController,this);

        //对应与表格列绑定
        tablePic.setCellValueFactory(new PropertyValueFactory("filedTableView"));
        tableName.setCellValueFactory(new PropertyValueFactory("name"));

        TableConfig tableConfig = (TableConfig) Cache.getGuavaTable().get(Constant.TABLE_CONFIG, Constant.TABLE_CONFIG);
        List<Table> tables = tableConfig.getTables();
        tableList.addAll(tables);
        objTable.setItems(tableList);
        objTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTableDetail((Table) newValue));


        fieldTable.setItems(tableFieldList);
    }


    private void showTableDetail(Table table){
        //表字段展示
        fieldName.setCellValueFactory(new PropertyValueFactory("name"));
        fieldType.setCellValueFactory(new PropertyValueFactory("type"));
        fieldOperate.setCellValueFactory(new PropertyValueFactory("fieldButton"));

        tableFieldList.clear();
        List<TableField> fieldInfoList = table.getFieldInfoList();
        fieldInfoList.forEach(bean->bean.initFieldButton());
        tableFieldList.addAll(fieldInfoList);

        //方式当前字段表格对象，为了比对是哪一行的字段需要配置
//        tableFieldList.forEach(field->field.setTableView(fieldTable));
        fieldTable.setItems(tableFieldList);
    }
}
