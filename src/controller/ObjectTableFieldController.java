package controller;

import bean.EnumBean;
import bean.EnumProperties;
import cache.Cache;
import contants.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author 林雾
 * @Date 2018/9/24/024
 * @Description
 */
@Getter
@Setter
public class ObjectTableFieldController extends BaseController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
