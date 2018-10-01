package controller;

import bean.EnumBean;
import bean.EnumProperties;
import cache.Cache;
import contants.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


    private ObservableList<EnumBean> enumBeans = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().put(Constant.Controller,Constant.FieldEnumController,this);
        refreshEnums();

        fieldEnumColumn.setCellValueFactory(new PropertyValueFactory("className"));
        fieldOperateColumn.setCellValueFactory(new PropertyValueFactory("selectButton"));
        enumTable.setItems(enumBeans);

        //监听行选取变化，右边Enum展示对应的枚举属性列表
        enumTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEnumDetailTable((EnumBean) newValue));

        code.setCellValueFactory(new PropertyValueFactory("code"));
        msg.setCellValueFactory(new PropertyValueFactory("msg"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
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
