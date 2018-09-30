package controller;

import bean.EnumBean;
import bean.EnumProperties;
import cache.Cache;
import com.google.common.collect.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contants.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import utils.IOUtils;
import utils.UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @Author 林雾
 * @Date 2018/9/21/021
 * @Description
 */
@Getter
@Setter
public class EnumNewController extends BaseController implements Initializable {
    @FXML
    private TableView enumTable;
    @FXML
    private TableColumn className;
    @FXML
    private TableColumn enumDeleteButton;
    @FXML
    private TableView enumProperties;
    @FXML
    private TableColumn enumName;
    @FXML
    private TableColumn enumValue;
    @FXML
    private TableColumn propertiesDeleteButton;
    @FXML
    private TextField newName;
    @FXML
    private TextField newValue;
    @FXML
    private TextField enumClassName;
    private ObservableList<EnumBean> enumBeans = FXCollections.observableArrayList();
    private ObservableList<EnumProperties> properties = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().put(Constant.Controller,Constant.EnumNewController,this);

        className.setCellValueFactory(new PropertyValueFactory("className"));
        enumDeleteButton.setCellValueFactory(new PropertyValueFactory("deleteButton"));

        enumName.setCellValueFactory(new PropertyValueFactory("code"));
        enumValue.setCellValueFactory(new PropertyValueFactory("msg"));
        propertiesDeleteButton.setCellValueFactory(new PropertyValueFactory("propertiesDeleteButton"));
        //初始化枚举详情列表清空
        showEnumDetailTable(null);

        //监听行选取变化，右边Enum展示对应的枚举属性列表
        enumTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEnumDetailTable((EnumBean) newValue));
        enumTable.setItems(enumBeans);

    }


    /**右侧窗体枚举详情
     * @param enumBean
     */
    public void showEnumDetailTable(EnumBean enumBean){
        if (enumBean == null) {
            enumProperties.setItems(FXCollections.observableArrayList());
        }else{
            List<EnumProperties> properties = enumBean.getProperties();
            enumProperties.setItems(FXCollections.observableArrayList(properties));
        }
    }

    /**新增枚举属性事件
     * @param mouseEvent
     */
    public void addProperties(MouseEvent mouseEvent) {
        String name = newName.getText();
        try {
            Integer.parseInt(name);
        } catch (NumberFormatException e) {
            UI.alertErrorMessage("枚举Name必须为数字类型");
            return;
        }

        String value = newValue.getText();
        if(StringUtils.isBlank(value)){
            UI.alertErrorMessage("枚举Value必须为字符类型");
            return;
        }

        EnumProperties newProperties = new EnumProperties(null);
        newProperties.initTableView(enumProperties);
        newProperties.setCode(name);
        newProperties.setMsg(value);
        properties.add(newProperties);
        enumProperties.setItems(properties);

        newName.setText(null);
        newValue.setText(null);
    }


    /**新增枚举
     * @param mouseEvent
     */
    public void addEnum(MouseEvent mouseEvent) {
        String enumName = enumClassName.getText();
        if(StringUtils.isBlank(enumName)){
            UI.alertErrorMessage("请输入枚举名");
            return;
        }

        Table<String, Object, Object> guavaTable = Cache.getGuavaTable();
        boolean contains = guavaTable.contains(Constant.ENUM_JSON, enumName);
        if(contains){
            UI.alertErrorMessage(String.format("枚举已存在：%s",enumName));
            return;
        }

        if(properties.isEmpty()){
            UI.alertErrorMessage("未配置枚举 name value");
            return;
        }

        EnumBean bean = new EnumBean();
        bean.initTableView(enumTable);
        bean.setClassName(enumName);
        String str = bean.getClassName().substring(0, 1).toLowerCase() + bean.getClassName()
                .substring(1);
        bean.setFieldName(str);


        List<EnumProperties> collect = properties.stream().map(pro -> {
            pro.setBean(bean);
            return pro;
        }).collect(Collectors.toList());
        bean.setProperties(collect);
        enumBeans.add(bean);
        properties.clear();
        guavaTable.put(Constant.ENUM_JSON,enumName,bean);

        enumClassName.setText(null);

    }
}
