package controller;

import bean.EnumBean;
import bean.EnumProperties;
import cache.Cache;
import contants.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import utils.UiUtils;

import java.net.URL;
import java.util.List;
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
    private TableColumn name;
    @FXML
    private TableColumn code;
    @FXML
    private TableColumn msg;
    @FXML
    private TableColumn propertiesDeleteButton;
    @FXML
    private TextField addName;
    @FXML
    private TextField addCode;
    @FXML
    private TextField addMsg;


    private ObservableList<EnumBean> enumBeans = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().put(Constant.Controller,Constant.EnumListController,this);

        className.setCellValueFactory(new PropertyValueFactory(EnumBean.CONSTANT_CLASS_NAME));
        enumDeleteButton.setCellValueFactory(new PropertyValueFactory(EnumBean.CONSTANT_DELETE_BUTTON));

        code.setCellValueFactory(new PropertyValueFactory(EnumProperties.CONSTANT_CODE));
        msg.setCellValueFactory(new PropertyValueFactory(EnumProperties.CONSTANT_MSG));
        name.setCellValueFactory(new PropertyValueFactory(EnumProperties.CONSTANT_NAME));
        propertiesDeleteButton.setCellValueFactory(new PropertyValueFactory(EnumProperties.CONSTANT_PROPERTIES_DELETE_BUTTON));
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
        String code = addCode.getText();
        try {
            Integer.parseInt(code);
        } catch (NumberFormatException e) {
            UiUtils.alertErrorMessage("枚举code必须为数字类型");
            return;
        }

        String msg = addMsg.getText();
        if(StringUtils.isBlank(msg)){
            UiUtils.alertErrorMessage("枚举msg必须为字符类型");
            return;
        }

        String name = addName.getText();
        if(StringUtils.isBlank(name)){
            UiUtils.alertErrorMessage("枚举name必须为字符类型");
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
        newProperties.setCode(code);
        newProperties.setMsg(msg);
        newProperties.setName(name.toUpperCase());

        enumBean.getProperties().add(newProperties);
        showEnumDetailTable(enumBean);
    }
}
