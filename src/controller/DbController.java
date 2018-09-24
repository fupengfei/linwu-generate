package controller;

import bean.EnumBean;
import bean.EnumProperties;
import cache.Cache;
import config.DbConfig;
import config.TableConfig;
import contants.Constant;
import contants.DBTypeEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
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
public class DbController extends BaseController implements Initializable {
    @FXML
    private TextField url;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ComboBox dbType;
    @FXML
    private AnchorPane rightAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**数据连接校验
     * @param actionEvent
     */
    public void checkDbParasm(ActionEvent actionEvent) {
        url.setText("jdbc:mysql://localhost:3306/solution");
        username.setText("root");
        password.setText("root");
        dbType.setValue("Mysql");
        if(StringUtils.isBlank(url.getText())){
            UI.alertErrorMessage("输入URL");
            return;
        }
        if(StringUtils.isBlank(username.getText())){
            UI.alertErrorMessage("输入username");
            return;
        }
        if(StringUtils.isBlank(password.getText())){
            UI.alertErrorMessage("输入password");
            return;
        }
        if (StringUtils.isBlank(dbType.getValue().toString())) {
            UI.alertErrorMessage("请选择数据库类型");
            return;
        }
        DbConfig dbConfig = null;
        try {
            dbConfig = new DbConfig();
            dbConfig.init(DBTypeEnum.MYSQL,
                    url.getText(), username.getText(), password.getText());
        } catch (Exception e) {
            UI.alertErrorMessage(String.format("连接失败:%s",e));
            return;
        }
        UI.alertInfoMessage("连接成功");

        TableConfig tableConfig = new TableConfig(dbConfig);
        if(tableConfig.getTables().isEmpty()){
            tableConfig.initTable();
        }
        Cache.getGuavaTable().put(Constant.TABLE_CONFIG,Constant.TABLE_CONFIG,tableConfig);
        initChooseTableFXML();
    }

    public void initChooseTableFXML(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/fxml/choose-table.fxml"));
            Pane pane = loader.load();
            rightAnchorPane.getChildren().add(pane);

            ChooseTableController controller = loader.getController();
            controller.setRootStage(rootStage);
            controller.setRootBorderPane(rootBorderPane);
        } catch (IOException e) {
            UI.alertErrorMessage(String.format("加载choose-table失败：%s",e.getMessage()));
        }
    }
}
