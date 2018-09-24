package bean;

import cache.Cache;
import com.google.gson.annotations.Expose;
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
    @Expose
    private String className;
    @Expose
    private List<EnumProperties> properties ;
    @Expose(serialize = false)
    private TableView enumTable;
    @Expose(serialize = false)
    private Button deleteButton;
    @Expose(serialize = false)
    private Button selectButton;

    public void initButton(){
        this.deleteButton = new Button();
        deleteButton.setText("删除");
        deleteButton.setOnMouseClicked(event -> {
            this.enumTable.getItems().remove(this);
            Cache.getGuavaTable().remove(Constant.ENUM_JSON,className);
        });
    }

    public void initSelectButton(){
        this.selectButton = new Button();
        selectButton.setText("选择");
        selectButton.setOnMouseClicked(event -> {
//            this.enumTable.getItems().remove(this);
//            Cache.getGuavaTable().remove(Constant.ENUM_JSON,className);
        });
    }

    public void initTableView(TableView enumTable){
        this.enumTable = enumTable;
        initButton();
        initSelectButton();
    }
}
