package utils;

import contants.Constant;
import javafx.scene.control.Alert;

/**
 * @author ：ZhangLei
 * @date ：2018/9/18
 * @description :
 */
public class UI {
    public static void alertErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误信息");
        alert.setHeaderText("程序加载发生错误");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alertInfoMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("成功");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
