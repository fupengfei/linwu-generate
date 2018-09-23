import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jvm.Hook;


public class CodeGenerate extends Application {
    private  Stage rootStage;
    private  BorderPane rootBorderPane;


    @Override
    public void start(Stage rootStage) throws Exception{
        initRootFxml(rootStage);

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
