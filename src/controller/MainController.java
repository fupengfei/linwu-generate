package controller;

import bean.EnumBean;
import bean.EnumProperties;
import cache.Cache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contants.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.EnumJsonUtils;
import utils.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author 林雾
 * @Date 2018/9/21/021
 * @Description
 */
public class MainController extends BaseController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cache.getGuavaTable().put(Constant.Controller,Constant.MainController,this);
    }

    /**
     * 加载通用枚举列表页面
     *
     * @param rootStage
     * @throws Exception
     */
    public void initEnumListFxml(Stage rootStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("/fxml/enum-list.fxml"));
        AnchorPane anchorPane = loader.load();
        this.rootBorderPane.setCenter(anchorPane);

        EnumListController controller = loader.getController();
        controller.setRootStage(rootStage);
        controller.setRootBorderPane(rootBorderPane);

        //加载Enum JSON文件到缓存备用
        Map<Object, Object> row = Cache.getGuavaTable().row(Constant.ENUM_JSON);
        row.forEach((k, v) -> {
            EnumBean v1 = (EnumBean) v;
            initProperties(v1, controller.getEnumTable(), controller.getEnumProperties());

            List<EnumProperties> properties = v1.getProperties();
            if (properties != null && !properties.isEmpty()) {
                properties.forEach(pro -> pro.initTableView(controller.getEnumProperties()));
            }
            v1.setEnumTable(controller.getEnumTable());
            controller.getEnumBeans().add(v1);
        });
    }


    /**
     * 加载通用枚举列表页面
     *
     * @param rootStage
     * @throws Exception
     */
    public void initEnumNewFxml(Stage rootStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("/fxml/enum-new.fxml"));
        AnchorPane anchorPane = loader.load();
        this.rootBorderPane.setCenter(anchorPane);

        EnumNewController controller = loader.getController();
        controller.setRootStage(rootStage);
        controller.setRootBorderPane(rootBorderPane);

        //加载Enum JSON文件到缓存备用
        Map<Object, Object> row = Cache.getGuavaTable().row(Constant.ENUM_JSON);
        row.forEach((k, v) -> {
            EnumBean v1 = (EnumBean) v;
            initProperties(v1, controller.getEnumTable(), controller.getEnumProperties());

            List<EnumProperties> properties = v1.getProperties();
            if (properties != null && !properties.isEmpty()) {
                properties.forEach(pro -> pro.initTableView(controller.getEnumProperties()));
            }
            v1.setEnumTable(controller.getEnumTable());
            controller.getEnumBeans().add(v1);
        });
    }

    /**
     * 数据库链接页面
     *
     * @param actionEvent
     * @throws Exception
     */
    public void showDbFXML(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("/fxml/db.fxml"));
        AnchorPane anchorPane = loader.load();
        this.rootBorderPane.setCenter(anchorPane);

        DbController controller = loader.getController();
        controller.setRootStage(rootStage);
        controller.setRootBorderPane(rootBorderPane);
    }

    /**
     * 展示通用枚举窗口
     *
     * @param actionEvent
     */
    public void showEnumListFXML(ActionEvent actionEvent) throws Exception {
        initEnumListFxml(this.getRootStage());
    }

    /**
     * 展示新增通用枚举窗口
     *
     * @param actionEvent
     */
    public void showEnumNewFXML(ActionEvent actionEvent) throws Exception {
        initEnumNewFxml(this.getRootStage());
    }

    /**
     * 缓存加载的bean没有表对象以及EnumBean，手动塞入，删除事件需要改对象
     *
     * @param enumBean
     * @param enumTable
     * @param enumProperties
     */
    private void initProperties(EnumBean enumBean, TableView enumTable, TableView enumProperties) {
        enumBean.initTableView(enumTable);
        List<EnumProperties> properties = enumBean.getProperties();
        if (properties != null && !properties.isEmpty()) {
            properties.forEach(pro -> {
                pro.initTableView(enumProperties);
                pro.setBean(enumBean);
            });
        }
    }
}
