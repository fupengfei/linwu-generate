package controller;

import bean.EnumBean;
import bean.FileChoose;
import bean.Table;
import bean.TableField;
import cache.Cache;
import config.TableConfig;
import contants.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import utils.UI;

import java.io.IOException;
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
public class TableGenerateController extends BaseController implements Initializable {

    @FXML
    private TableView table;
    @FXML
    private TableColumn tableGeneratePic;
    @FXML
    private TableColumn tableGenerateName;
    public static final ObservableList<Table> tableGenerate = FXCollections.observableArrayList();
    public final ObservableList<TableField> tableFieldList = FXCollections.observableArrayList();

    @FXML
    private CheckBox controllerCheckBox;
    @FXML
    private CheckBox remoteCheckBox;
    @FXML
    private CheckBox remoteImplCheckBox;
    @FXML
    private CheckBox serviceCheckBox;
    @FXML
    private CheckBox serviceImplCheckBox;
    @FXML
    private CheckBox daoCheckBox;
    @FXML
    private CheckBox mapperCheckBox;
    @FXML
    private CheckBox entityCheckBox;
    @FXML
    private CheckBox enhancedCheckBox;
    @FXML
    private CheckBox reqCheckBox;
    @FXML
    private CheckBox respCheckBox;
    @FXML
    private CheckBox xmlCheckBox;
    @FXML
    private CheckBox queryCheckBox;

    @FXML
    private TableView fieldTable;
    @FXML
    private TableColumn field;
    @FXML
    private TableColumn fieldType;
    @FXML
    private TableColumn fieldOperate;
    @FXML
    private AnchorPane rightFieldEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().get(Constant.FIELD_ENUM_PAN,Constant.FIELD_ENUM_PAN);
        Cache.getGuavaTable().put(Constant.RIGHT_FIELD_EDIT_PAN,Constant.RIGHT_FIELD_EDIT_PAN,rightFieldEdit);
        initTableView();
        initFieldEnumFXML();
        tableGenerate.addAll(ChooseTableController.select);
        TableConfig tableConfig = (TableConfig) Cache.getGuavaTable().get(Constant.TABLE_CONFIG, Constant.TABLE_CONFIG);

        tableGenerate.forEach(table->tableConfig.tablefieldExcute(table));
        table.setItems(tableGenerate);

        //表选中右侧展示
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTableDetail((Table) newValue));

        //表字段被选中右侧展示
        fieldTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    //当前字段的配置状态
                    TableField field = (TableField) newValue;
                    int index = field.getChoiceBox().getSelectionModel().getSelectedIndex();
                    //展示窗体，选中窗体
                    if(index==1){}
                    if(index==2){
                        Pane pane  = (Pane) Cache.getGuavaTable().get(Constant.FIELD_ENUM_PAN,Constant.FIELD_ENUM_PAN);
                        ObservableList<Node> children = rightFieldEdit.getChildren();
                        children.clear();
                        children.add(pane);
                    }
                });
    }

    /**
     * 映射表格列与对象
     */
    public void initTableView() {
        tableGeneratePic.setCellValueFactory(new PropertyValueFactory("imageView"));
        tableGenerateName.setCellValueFactory(new PropertyValueFactory("name"));
    }


    /**全选
     * @param actionEvent
     */
    public void all(ActionEvent actionEvent) {
        controllerCheckBox.setSelected(true);
        remoteCheckBox.setSelected(true);
        remoteImplCheckBox.setSelected(true);
        serviceCheckBox.setSelected(true);
        serviceImplCheckBox.setSelected(true);
        daoCheckBox.setSelected(true);
        mapperCheckBox.setSelected(true);
        entityCheckBox.setSelected(true);
        enhancedCheckBox.setSelected(true);
        reqCheckBox.setSelected(true);
        respCheckBox.setSelected(true);
        xmlCheckBox.setSelected(true);
        xmlCheckBox.setSelected(true);
        queryCheckBox.setSelected(true);

        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if(selectedIndex<0){
            return;
        }
        Table t = (Table) table.getItems().get(selectedIndex);
        t.getFileChoose().all();
    }


    public int getSelectIndex(){
        return table.getSelectionModel().getSelectedIndex();
    }


    public void setSelectTableCheckBox(){
        int selectIndex = getSelectIndex();
        if(selectIndex<0){
            return;
        }
        Table t = (Table) table.getItems().get(selectIndex);
        FileChoose fileChoose = t.getFileChoose();

        fileChoose.setController(controllerCheckBox.isSelected());
        fileChoose.setRemote(remoteCheckBox.isSelected());
        fileChoose.setRemoteImpl(remoteImplCheckBox.isSelected());
        fileChoose.setService(serviceCheckBox.isSelected());
        fileChoose.setServiceImpl(serviceImplCheckBox.isSelected());
        fileChoose.setDao(daoCheckBox.isSelected());
        fileChoose.setMapper(mapperCheckBox.isSelected());
        fileChoose.setEntity(entityCheckBox.isSelected());
        fileChoose.setEnhanced(enhancedCheckBox.isSelected());
        fileChoose.setReq(reqCheckBox.isSelected());
        fileChoose.setResp(respCheckBox.isSelected());
        fileChoose.setXml(xmlCheckBox.isSelected());
        fileChoose.setQuery(queryCheckBox.isSelected());
    }





    /**全取
     * @param actionEvent
     */
    public void cancel(ActionEvent actionEvent) {
        controllerCheckBox.setSelected(false);
        remoteCheckBox.setSelected(false);
        remoteImplCheckBox.setSelected(false);
        serviceCheckBox.setSelected(false);
        serviceImplCheckBox.setSelected(false);
        daoCheckBox.setSelected(false);
        mapperCheckBox.setSelected(false);
        entityCheckBox.setSelected(false);
        enhancedCheckBox.setSelected(false);
        reqCheckBox.setSelected(false);
        respCheckBox.setSelected(false);
        xmlCheckBox.setSelected(false);
        queryCheckBox.setSelected(false);

        int selectIndex = getSelectIndex();
        if(selectIndex<0){
            return;
        }
        Table t = (Table) table.getItems().get(selectIndex);
        t.getFileChoose().cancel();
    }


    private void showTableDetail(Table table){
        //表字段展示
        field.setCellValueFactory(new PropertyValueFactory("name"));
        fieldType.setCellValueFactory(new PropertyValueFactory("type"));
        fieldOperate.setCellValueFactory(new PropertyValueFactory("choiceBox"));

        tableFieldList.clear();
        List<TableField> fieldInfoList = table.getFieldInfoList();
        tableFieldList.addAll(fieldInfoList);
        fieldTable.setItems(tableFieldList);

        //生成文件展示
        FileChoose fileChoose = table.getFileChoose();
        controllerCheckBox.setSelected(fileChoose.isController());
        remoteCheckBox.setSelected(fileChoose.isRemote());
        remoteImplCheckBox.setSelected(fileChoose.isRemoteImpl());
        serviceCheckBox.setSelected(fileChoose.isService());
        serviceImplCheckBox.setSelected(fileChoose.isServiceImpl());
        daoCheckBox.setSelected(fileChoose.isDao());
        mapperCheckBox.setSelected(fileChoose.isMapper());
        entityCheckBox.setSelected(fileChoose.isEntity());
        enhancedCheckBox.setSelected(fileChoose.isEnhanced());
        reqCheckBox.setSelected(fileChoose.isReq());
        respCheckBox.setSelected(fileChoose.isResp());
        xmlCheckBox.setSelected(fileChoose.isXml());
        queryCheckBox.setSelected(fileChoose.isQuery());
    }

    public void initFieldEnumFXML(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/fxml/field-enum.fxml"));
            Pane pane = loader.load();
            Cache.getGuavaTable().put(Constant.FIELD_ENUM_PAN,Constant.FIELD_ENUM_PAN,pane);

            FieldEnumController controller = loader.getController();
            controller.setRootStage(rootStage);
            controller.setRootBorderPane(rootBorderPane);


        } catch (IOException e) {
            UI.alertErrorMessage(String.format("加载field-enum失败：%s",e.getMessage()));
        }
    }

}
