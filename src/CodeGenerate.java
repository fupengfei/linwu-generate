import bean.EnumBean;
import bean.EnumProperties;
import cache.Cache;
import contants.Constant;
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jvm.Hook;
import utils.EnumJsonUtils;
import utils.IOUtils;

import java.util.List;


public class CodeGenerate extends Application {
    private  Stage rootStage;
    private  BorderPane rootBorderPane;


    @Override
    public void start(Stage rootStage) throws Exception{
        initRootFxml(rootStage);

        EnumBean[] enumBeans = EnumJsonUtils.jsonToEnumBeanArr();
        if (enumBeans == null||enumBeans.length<1) {
            return;
        }
        for (int i = 0; i < enumBeans.length; i++) {
            enumBeans[i].initTableView(null);
            List<EnumProperties> properties = enumBeans[i].getProperties();
            if (properties != null && !properties.isEmpty()) {
                for (int j = 0; j < properties.size(); j++) {
                    properties.get(j).initTableView(null);
                    properties.get(j).setBean(enumBeans[i]);
                }
            }
            Cache.setGuavaTable(Constant.ENUM_JSON,enumBeans[i].getClassName(),enumBeans[i]);
        }
    }

    /**加载主布局
     * @param rootStage
     * @throws Exception
     */
    public  void initRootFxml(Stage rootStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CodeGenerate.class.getResource("./fxml/root.fxml"));

        rootBorderPane = (BorderPane) loader.load();
        Scene scene = new Scene(rootBorderPane);

        rootStage.setTitle("代码生成");
        rootStage.setScene(scene);
        rootStage.show();

        MainController controller = loader.getController();
        controller.setRootStage(rootStage);
        controller.setRootBorderPane(rootBorderPane);
    }

    public static void main(String[] args) {
        launch(args);
        Hook.start();
    }

}
