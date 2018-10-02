package controller;

import bean.EnumBean;
import bean.EnumProperties;
import bean.Table;
import cache.Cache;
import contants.Constant;
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
public class FieldEnumController  extends BaseController implements Initializable {
    @FXML
    private TableView enumTable;
    @FXML
    private TableColumn fieldEnumColumn;
    @FXML
    private TableColumn fieldOperateColumn;


    @FXML
    private TableView enumDetailTable;
    @FXML
    private TableColumn code;
    @FXML
    private TableColumn msg;
    @FXML
    private TableColumn name;
    @FXML
    private TextField search;


    private ObservableList<EnumBean> enumBeans = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().put(Constant.Controller,Constant.FieldEnumController,this);
        refreshEnums();

        fieldEnumColumn.setCellValueFactory(new PropertyValueFactory(EnumBean.CONSTANT_CLASS_NAME));
        fieldOperateColumn.setCellValueFactory(new PropertyValueFactory(EnumBean.CONSTANT_SELECT_BUTTON));
        enumTable.setItems(enumBeans);

        //监听行选取变化，右边Enum展示对应的枚举属性列表
        enumTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEnumDetailTable((EnumBean) newValue));

        code.setCellValueFactory(new PropertyValueFactory(EnumProperties.CONSTANT_CODE));
        msg.setCellValueFactory(new PropertyValueFactory(EnumProperties.CONSTANT_MSG));
        name.setCellValueFactory(new PropertyValueFactory(EnumProperties.CONSTANT_NAME));


        FilteredList<EnumBean> filter = new FilteredList<>(enumBeans, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(enumBean -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String filterName = newValue.toLowerCase();

                if (enumBean.getClassName().equalsIgnoreCase(filterName)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<EnumBean> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(enumTable.comparatorProperty());
        enumTable.setItems(sortedData);
    }

    public void refreshEnums() {
        enumBeans.clear();
        //缓存中的通用枚举列表
        Map<Object, Object> row = Cache.getGuavaTable().row(Constant.ENUM_JSON);
        if(!row.isEmpty()){
            row.forEach((k,v)->{
                EnumBean v1 = (EnumBean) v;
                v1.initTableView(enumTable);
                v1.initSelectButton();
                enumBeans.add((EnumBean) v);
            });
        }
    }

    /**右侧窗体枚举详情
     * @param enumBean
     */
    public void showEnumDetailTable(EnumBean enumBean){
        if (enumBean == null) {
            enumDetailTable.setItems(FXCollections.observableArrayList());
        }else{
            List<EnumProperties> properties = enumBean.getProperties();
            enumDetailTable.setItems(FXCollections.observableArrayList(properties));
        }
    }
}
