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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField search;

    public final ObservableList<Table> tableList = FXCollections.observableArrayList();
    public final ObservableList<TableField> tableFieldList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().put(Constant.Controller, Constant.ObjectTableFieldController, this);

        //对应与表格列绑定
        tablePic.setCellValueFactory(new PropertyValueFactory(Table.CONSTANT_FILED_TABLE_VIEW));
        tableName.setCellValueFactory(new PropertyValueFactory(Table.CONSTANT_NAME));

        refershTables();
        objTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTableDetail((Table) newValue));

        objTable.setItems(tableList);
        fieldTable.setItems(tableFieldList);

        FilteredList<Table> filter = new FilteredList<>(tableList, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(table -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if (table.getName().contains(newValue)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Table> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(objTable.comparatorProperty());

        objTable.setItems(sortedData);
    }

    private void showTableDetail(Table table) {
        fieldName.setCellValueFactory(new PropertyValueFactory(TableField.CONSTANT_NAME));
        fieldType.setCellValueFactory(new PropertyValueFactory(TableField.CONSTANT_TYPE));
        fieldOperate.setCellValueFactory(new PropertyValueFactory(TableField.CONSTANT_FIELD_BUTTON));
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
