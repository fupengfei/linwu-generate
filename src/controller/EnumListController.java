package controller;

import bean.EnumBean;
import bean.EnumProperties;
import cache.Cache;
import contants.Constant;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import utils.UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author 林雾
 * @Date 2018/9/21/021
 * @Description
 */
@Getter
@Setter
public class EnumListController extends BaseController implements Initializable {
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


    private ObservableList<EnumBean> enumBeans = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        //当前EnumTable选择的行，新增的值塞入Enum对象list
        int selectedIndex = enumTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex==-1){
            return;
        }
        EnumBean enumBean = (EnumBean) enumTable.getItems().get(selectedIndex);

        EnumProperties newProperties = new EnumProperties(enumBean);
        newProperties.initTableView(enumProperties);
        newProperties.setCode(name);
        newProperties.setMsg(value);

        enumBean.getProperties().add(newProperties);
        showEnumDetailTable(enumBean);
    }
}
