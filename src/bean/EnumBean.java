package bean;

import cache.Cache;
import contants.Constant;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 林雾
 * @Date 2018/9/22/022
 * @Description
 */
@Getter
@Setter
public class EnumBean {
    private String className;
    private List<EnumProperties> properties ;
    private TableView enumTable;
    private Button deleteButton;

    public EnumBean(TableView enumTable){
        this.enumTable = enumTable;
        this.deleteButton = new Button();
        deleteButton.setText("删除");
        deleteButton.setOnMouseClicked(event -> {
            enumTable.getItems().remove(this);
            Cache.getGuavaTable().remove(Constant.ENUM_JSON,className);
            //TODO 删除JSON文件中的对象
        });
    }
}
