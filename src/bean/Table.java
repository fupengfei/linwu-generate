package bean;

import cache.Cache;
import com.google.common.base.CaseFormat;
import contants.Constant;
import controller.ChooseTableController;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Table {
    public static String CONSTANT_NAME = "name";
    public static String CONSTANT_IMAGE_VIEW = "imageView";
    public static String CONSTANT_FILED_TABLE_VIEW = "filedTableView";
    public static String CONSTANT_COMMENT = "comment";
    public static String CONSTANT_FIELD_INFO_LIST = "fieldInfoList";
    public static String CONSTANT_ALL_BUTTON = "allButton";
    public static String CONSTANT_SELECT_BUTTON = "selectButton";

    private ImageView filedTableView;
    private ImageView imageView;
    private String name;
    private String comment;
    private List<TableField> fieldInfoList = new ArrayList<>();
    private Button allButton;
    private Button selectButton;

    public Table(){
        this.imageView = new ImageView(new Image(getClass().getResourceAsStream("/resource/table.png")));
        this.filedTableView = new ImageView(new Image(getClass().getResourceAsStream("/resource/table.png")));
        initAllTableOperate();
        initSelectTableOperate();
    }

    private void initAllTableOperate(){
        this.allButton = new Button();
        this.allButton.setText("选择");
        this.allButton.setOnMouseClicked(event -> {

            //选择按钮会把图片也移动过去，重新生成一张
            this.imageView = new ImageView(new Image(getClass().getResourceAsStream("/resource/table.png")));

            ChooseTableController chooseTableController = (ChooseTableController) Cache.getGuavaTable().get(Constant.Controller, Constant.ChooseTableController);
            ObservableList<Table> select = chooseTableController.getSelect();
            if(!select.contains(this)){
                select.add(this);
            }

            //隐藏
            this.allButton.setVisible(false);
            this.allButton.setManaged(false);
        });
    }

    private void initSelectTableOperate(){
        this.selectButton = new Button();
        this.selectButton.setText("移除");
        this.selectButton.setOnMouseClicked(event -> {
            ChooseTableController chooseTableController = (ChooseTableController) Cache.getGuavaTable().get(Constant.Controller, Constant.ChooseTableController);
            ObservableList<Table> select = chooseTableController.getSelect();
            if(select.contains(this)){
                select.remove(this);
            }
            //展示
            this.allButton.setVisible(true);
            this.allButton.setManaged(true);
        });
    }

    public String getClassName(){
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, this.name);
    }
    public String getClassNameLower(){
        return getClassName().toLowerCase();
    }

    public String getClassField(){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this.name);
    }
}
