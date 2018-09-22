package controller;

import bean.EnumBeanList;
import cache.Cache;
import com.google.gson.Gson;
import contants.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.IOUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author 林雾
 * @Date 2018/9/21/021
 * @Description
 */
public class MainController extends BaseController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String enumJson = IOUtils.getEnumJson();
        EnumBeanList enumBeanList= new Gson().fromJson(enumJson, EnumBeanList.class);
        if(enumBeanList!=null&&enumBeanList.getList()!=null&&!enumBeanList.getList().isEmpty()){
            enumBeanList.getList().forEach(bean->{
                Cache.setGuavaTable(Constant.ENUM_JSON,bean.getClassName(),bean);
            });
        }
    }

    /**加载通用枚举列表页面
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
    }

    /**加载通用枚举列表页面
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
    }

    /**展示通用枚举窗口
     * @param actionEvent
     */
    public void showEnumListFXML(ActionEvent actionEvent) throws Exception {
        initEnumListFxml(this.getRootStage());
    }

    /**展示新增通用枚举窗口
     * @param actionEvent
     */
    public void showEnumNewFXML(ActionEvent actionEvent) throws Exception {
        initEnumNewFxml(this.getRootStage());
    }
}
