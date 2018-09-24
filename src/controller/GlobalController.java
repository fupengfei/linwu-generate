package controller;

import cache.Cache;
import config.DbConfig;
import config.GlobalConfig;
import config.TableConfig;
import contants.Constant;
import contants.DBTypeEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
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
public class GlobalController extends BaseController implements Initializable {
    @FXML
    private TextField filePath;
    @FXML
    private TextField parentPackage;
    @FXML
    private TextField author;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void next(ActionEvent actionEvent) {
        //TODO 校验是否都输入
        boolean check = check();
        check=true;
        if(!check){
            return;
        }
        initTableGenerteFXML();

    }

    private boolean check() {
        //TODO 文件路径非法校验，模块非法校验
        if(StringUtils.isBlank(filePath.getText())){
            UI.alertErrorMessage("输入文件路径");
            return false;
        }
        if(StringUtils.isBlank(parentPackage.getText())){
            UI.alertErrorMessage("输入模块包名");
            return false;
        }
        if(StringUtils.isBlank(author.getText())){
            UI.alertErrorMessage("输入作者信息");
            return false;
        }

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor(author.getText());
        globalConfig.setOutputDir(filePath.getText());
        globalConfig.setParentPackage(parentPackage.getText());
        Cache.setGuavaTable(Constant.GLOBAL_CONFIG,Constant.GLOBAL_CONFIG,globalConfig);
        return true;
    }

    public void initTableGenerteFXML(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/fxml/table-generate.fxml"));
            AnchorPane anchorPane = loader.load();
            this.rootBorderPane.setCenter(anchorPane);

            TableGenerateController controller = loader.getController();
            controller.setRootStage(rootStage);
            controller.setRootBorderPane(rootBorderPane);
        } catch (IOException e) {
            UI.alertErrorMessage(String.format("加载table-generate失败：%s",e.getMessage()));
        }
    }
}
