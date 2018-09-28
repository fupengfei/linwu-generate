package bean;

import cache.Cache;
import com.google.common.collect.Table;
import com.google.gson.annotations.Expose;
import contants.Constant;
import controller.FieldEnumController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import lombok.Getter;
import lombok.Setter;

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

    @Expose(serialize = false)
    private TableView enumDetailTable;

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
            //当前选择的枚举放进字段对象
            for (int i = 0; i <enumTable.getItems().size() ; i++) {
                EnumBean enumBean = (EnumBean) enumTable.getItems().get(i);
                if(this.equals(enumBean)){
                    Table<String, Object, Object> guavaTable = Cache.getGuavaTable();
                    TableView fieldTableView = (TableView) guavaTable.get(Constant.FIELD_TABLE, Constant.FIELD_TABLE);
                    int fileldIndex = (int) guavaTable.get(Constant.FIELD_INDEX, Constant.FIELD_INDEX);
                    TableField field = (TableField) fieldTableView.getItems().get(fileldIndex);
                    field.setEnumBean(this);

                    //清除非当前选择的枚举
                    FieldEnumController fieldEnumController = (FieldEnumController) Cache.getGuavaTable().get(Constant.Controller, Constant.FieldEnumController);
                    fieldEnumController.getEnumBeans().removeIf(bean->!bean.equals(this));
                }
            }
        });
    }

    public void initTableView(TableView enumTable){
        this.enumTable = enumTable;
        initButton();
    }
}
