package bean;

import config.FilePath;
import config.SolutionFilePathConfig;
import config.PackageConfig;
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
    private ImageView filedTableView;
    private ImageView imageView;
    private String name;
    private String comment;
    private List<TableField> fieldInfoList = new ArrayList<>();
    private Button allTableOperate;
    private Button selectTableOperate;
    private FileChoose fileChoose = new FileChoose();
    private FilePath filePath;
    private PackageConfig packageConfig;

    public Table(){
        this.imageView = new ImageView(new Image(getClass().getResourceAsStream("/resource/table.png")));
        this.filedTableView = new ImageView(new Image(getClass().getResourceAsStream("/resource/table.png")));
        initAllTableOperate();
        initSelectTableOperate();
    }

    private void initAllTableOperate(){
        this.allTableOperate = new Button();
        this.allTableOperate.setText("选择");
        this.allTableOperate.setOnMouseClicked(event -> {
            //选择按钮会把图片也移动过去，重新生成一张
            this.imageView = new ImageView(new Image(getClass().getResourceAsStream("/resource/table.png")));
            ObservableList<Table> select = ChooseTableController.select;
            if(!select.contains(this)){
                select.add(this);
            }
        });
    }

    private void initSelectTableOperate(){
        this.selectTableOperate = new Button();
        this.selectTableOperate.setText("移除");
        this.selectTableOperate.setOnMouseClicked(event -> {
            ObservableList<Table> select = ChooseTableController.select;
            if(select.contains(this)){
                select.remove(this);
            }
        });
    }
}
