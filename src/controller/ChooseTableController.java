package controller;

import bean.Table;
import cache.Cache;
import config.TableConfig;
import contants.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import utils.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author 林雾
 * @Date 2018/9/21/021
 * @Description
 */
@Getter
@Setter
public class ChooseTableController extends BaseController implements Initializable {
    @FXML
    private TableView dbTables;
    @FXML
    private TableColumn allTablePic;
    @FXML
    private TableColumn allTableName;
    @FXML
    private TableColumn allTableOperate;

    @FXML
    private TableView selectTables;
    @FXML
    private TableColumn selectTablePic;
    @FXML
    private TableColumn selectTableName;
    @FXML
    private TableColumn selectTableOperate;

    public static final ObservableList<Table> all = FXCollections.observableArrayList();
    public static final ObservableList<Table> select = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();

        TableConfig tableConfig = (TableConfig) Cache.getGuavaTable().get(Constant.TABLE_CONFIG, Constant.TABLE_CONFIG);
        all.clear();
        all.addAll(tableConfig.getTables());
        dbTables.setItems(all);

        select.clear();
        select.addAll(tableConfig.getGenerateTables());
        selectTables.setItems(select);


    }

    /**
     * 映射表格列与对象
     */
    public void initTableView() {
        allTablePic.setCellValueFactory(new PropertyValueFactory("imageView"));
        allTableName.setCellValueFactory(new PropertyValueFactory("name"));
        allTableOperate.setCellValueFactory(new PropertyValueFactory("allTableOperate"));

        selectTablePic.setCellValueFactory(new PropertyValueFactory("imageView"));
        selectTableName.setCellValueFactory(new PropertyValueFactory("name"));
        selectTableOperate.setCellValueFactory(new PropertyValueFactory("selectTableOperate"));
    }

    /**选择完毕 下一部加载新页面，配置表生成规则
     * @param actionEvent
     */
    public void next(ActionEvent actionEvent) {
        if(select.isEmpty()){
            UI.alertErrorMessage("选择需要生成的表");
            return;
        }

        initglobalFXML();
    }

    public void initglobalFXML(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/fxml/global.fxml"));
            AnchorPane anchorPane = loader.load();
            this.rootBorderPane.setCenter(anchorPane);

            GlobalController controller = loader.getController();
            controller.setRootStage(rootStage);
            controller.setRootBorderPane(rootBorderPane);
        } catch (IOException e) {
            UI.alertErrorMessage(String.format("加载table-generate失败：%s",e.getMessage()));
        }
    }
}
