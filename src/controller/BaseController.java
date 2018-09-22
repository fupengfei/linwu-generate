package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 林雾
 * @Date 2018/9/21/021
 * @Description
 */
public class BaseController {
    /**主舞台
     * @param rootStage
     */
    protected Stage rootStage;
    /**主场景
     * @param rootStage
     */
    @FXML
    protected BorderPane rootBorderPane;

    public Stage getRootStage() {
        return rootStage;
    }

    public void setRootStage(Stage rootStage) {
        this.rootStage = rootStage;
    }

    public BorderPane getRootBorderPane() {
        return rootBorderPane;
    }

    public void setRootBorderPane(BorderPane rootBorderPane) {
        this.rootBorderPane = rootBorderPane;
    }
}
