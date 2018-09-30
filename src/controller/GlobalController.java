package controller;

import cache.Cache;
import config.FilePathConfig;
import config.GlobalConfig;
import config.PackageConfig;
import config.TemplateConfig;
import contants.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private RadioButton solution;
    @FXML
    private RadioButton wy;
    private ToggleGroup group = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().put(Constant.Controller,Constant.GlobalController,this);
        solution.setToggleGroup(group);
        wy.setToggleGroup(group);
    }


    public void next(ActionEvent actionEvent) {
        //TODO 校验是否都输入
        boolean check = check();
        if(!check){
            return;
        }
        initTableGenerteFXML();
    }

    private boolean check() {
        if(StringUtils.isBlank(filePath.getText())){
            UI.alertErrorMessage("输入文件路径");
            return false;
        }
        if(StringUtils.isBlank(parentPackage.getText())){
            UI.alertErrorMessage("输入父模块包名");
            return false;
        }
        if(StringUtils.isBlank(author.getText())){
            UI.alertErrorMessage("输入作者信息");
            return false;
        }

        RadioButton selecte = (RadioButton) group.getSelectedToggle();
        if (selecte == null) {
            UI.alertErrorMessage("选择生成的项目");
            return false;
        }

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setProjectTemplate(selecte.getId());
        globalConfig.setAuthor(author.getText());
        globalConfig.setOutputDir(filePath.getText());
        globalConfig.setParentPackage(parentPackage.getText());
        Cache.setGuavaTable(Constant.GLOBAL_CONFIG,Constant.GLOBAL_CONFIG,globalConfig);

        FilePathConfig filePathConfig = new FilePathConfig();
        PackageConfig packageConfig = new PackageConfig();
        TemplateConfig templateConfig = new TemplateConfig();

        globalConfig.setFilePathConfig(filePathConfig);
        globalConfig.setPackageConfig(packageConfig);
        globalConfig.setTemplateConfig(templateConfig);

        if(Constant.SOLUTION.equals(selecte.getId())){
            filePathConfig.initSolutionPath();
            packageConfig.initSolution(parentPackage.getText());
            templateConfig.initSolution();
            globalConfig.setSource(Constant.SOLUTION);
        }
        if(Constant.WY.equals(selecte.getId())){
            filePathConfig.initWyPath();
            packageConfig.initWy(parentPackage.getText());
            templateConfig.initWy();
            globalConfig.setSource(Constant.WY);
        }
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
