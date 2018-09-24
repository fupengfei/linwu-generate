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
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;
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
    private TableView fieldTable;
    @FXML
    private TableColumn field;
    @FXML
    private TableColumn fieldType;
    @FXML
    private TableColumn fieldoperate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
        tableGenerate.addAll(ChooseTableController.select);
        TableConfig tableConfig = (TableConfig) Cache.getGuavaTable().get(Constant.TABLE_CONFIG, Constant.TABLE_CONFIG);

        tableGenerate.forEach(table->tableConfig.tablefieldExcute(table));
        table.setItems(tableGenerate);

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTableDetail((Table) newValue));
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

        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        Table t = (Table) table.getItems().get(selectedIndex);
        t.getFileChoose().all();
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

        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        Table t = (Table) table.getItems().get(selectedIndex);
        t.getFileChoose().cancel();
    }

    private void showTableDetail(Table table){
        //表字段展示
        field.setCellValueFactory(new PropertyValueFactory("name"));
        fieldType.setCellValueFactory(new PropertyValueFactory("type"));
        fieldoperate.setCellValueFactory(new PropertyValueFactory("listView"));

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
    }

}
