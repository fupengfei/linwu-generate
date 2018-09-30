package bean;

import cache.Cache;
import com.google.common.collect.Table;
import contants.Constant;
import contants.DbColumnType;
import controller.FieldEnumController;
import controller.ObjectTableFieldController;
import controller.TableGenerateController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZhangLei
 * @date ：2018/8/22
 * @description :
 */
@Getter
@Setter
public class TableField {
    private String tableName;
    private boolean keyFlag;
    private String name;
    private String type;
    private DbColumnType columnType;
    private String comment;
    private String tabObj;
    private ChoiceBox choiceBox = new ChoiceBox();
    private TableView fieldTable;
    private Button fieldButton;
    private EnumBean enumBean;
    private TableField objField;
    private bean.Table objTable;
    private String fieldConstant;
    private String columnConstant;
    private String javaField;

    public TableField(){
        initChoiceBox();
    }

    public void initChoiceBox(){
        List<String> collect = new ArrayList<>();
        collect.add("不配置");
        collect.add("关联对象");
        collect.add("选择枚举");

        choiceBox.setItems(FXCollections.observableArrayList(collect));
        SingleSelectionModel selectionModel = choiceBox.getSelectionModel();
        selectionModel.select(0);
        choiceBox.setSelectionModel(selectionModel);

        //选择框索引被选择事件
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNum, Number newNum) {
                if(newNum.intValue()==0){
                    enumBean = null;
                    objField = null;
                    objTable = null;
                }

                //选择关联对象  加载关联对象窗口
                if(newNum.intValue()==1){
                    Table<String, Object, Object> guavaTable = Cache.getGuavaTable();
                    Pane pane  = (Pane) guavaTable.get(Constant.OBJ_TABLE_FIELD_PAN,Constant.OBJ_TABLE_FIELD_PAN);
                    AnchorPane rightFieldEdit = (AnchorPane) guavaTable.get(Constant.RIGHT_FIELD_EDIT_PAN, Constant.RIGHT_FIELD_EDIT_PAN);
                    ObservableList<Node> children = rightFieldEdit.getChildren();
                    children.clear();
                    children.add(pane);

                    ObjectTableFieldController objectTableFieldController = (ObjectTableFieldController) guavaTable.get(Constant.Controller, Constant.ObjectTableFieldController);
                    objectTableFieldController.refershTables();

                    //缓存当前字段需要配置的索引
                    for (int i = 0; i <fieldTable.getItems().size(); i++) {
                        TableField f = (TableField) fieldTable.getItems().get(i);
                        if(f.getChoiceBox().equals(choiceBox)){
                            guavaTable.put(Constant.FIELD_INDEX,Constant.FIELD_INDEX,i);
                        }
                    }
                }

                //选择枚举   加载枚举窗口
                if(newNum.intValue()==2){
                    Table<String, Object, Object> guavaTable = Cache.getGuavaTable();
                    Pane pane  = (Pane) guavaTable.get(Constant.FIELD_ENUM_PAN,Constant.FIELD_ENUM_PAN);
                    AnchorPane rightFieldEdit = (AnchorPane) guavaTable.get(Constant.RIGHT_FIELD_EDIT_PAN, Constant.RIGHT_FIELD_EDIT_PAN);
                    ObservableList<Node> children = rightFieldEdit.getChildren();
                    children.clear();
                    children.add(pane);

                    FieldEnumController fieldEnumController = (FieldEnumController) Cache.getGuavaTable().get(Constant.Controller, Constant.FieldEnumController);
                    fieldEnumController.refreshEnums();

                    //缓存当前字段需要配置的索引
                    for (int i = 0; i <fieldTable.getItems().size(); i++) {
                        TableField f = (TableField) fieldTable.getItems().get(i);
                        if(f.getChoiceBox().equals(choiceBox)){
                            guavaTable.put(Constant.FIELD_INDEX,Constant.FIELD_INDEX,i);
                        }
                    }
                }
            }
        });
    }

    public void setTableView(TableView fieldTable){
        this.fieldTable = fieldTable;
    }

    public void initFieldButton(){
        this.fieldButton = new Button();
        this.fieldButton.setText("关联");
        this.fieldButton.setOnMouseClicked(event -> {
            Table<String, Object, Object> guavaTable = Cache.getGuavaTable();
            ObjectTableFieldController controller = (ObjectTableFieldController) guavaTable
                    .get(Constant.Controller, Constant.ObjectTableFieldController);

            ObservableList<TableField> tableFieldList = controller.getTableFieldList();

            for (int i = 0; i < tableFieldList.size(); i++) {
                //当前选择的关联字段与this匹配
                if(this.equals(tableFieldList.get(i))){
                    TableView objTable = controller.getObjTable();

                    //获取选择关联的表
                    int fieldTableSelectIndex = objTable.getSelectionModel().getSelectedIndex();
                    if(fieldTableSelectIndex<0){
                        return;
                    }
                    bean.Table table = (bean.Table) objTable.getItems().get(fieldTableSelectIndex);

                    //具体是哪一个字段需要设置了关联对象 的字段索引
                    int fieldIndex = (int) guavaTable.get(Constant.FIELD_INDEX, Constant.FIELD_INDEX);
                    TableGenerateController tableGenerateController = (TableGenerateController) Cache.getGuavaTable().get(Constant.Controller, Constant.TableGenerateController);
                    //
                    ObservableList<TableField> tableFieldList1 = tableGenerateController.getTableFieldList();
                    TableField tableField = tableFieldList1.get(fieldIndex);
                    tableField.setObjTable(table);
                    tableField.setObjField(this);

                    //清除关联非关联对象字段
                    controller.getTableFieldList().removeIf(bean->!bean.equals(this));
                    controller.getTableList().removeIf(bean->!bean.equals(table));
                }
            }
        });
    }

    public String javaFieldFirstUppercase(){
        return this.getJavaField().substring(0,1).toUpperCase()+this.getJavaField().substring(1);
    }

    public static void main(String[] args) {
        TableField field = new TableField();
        field.isKeyFlag();
    }
}
