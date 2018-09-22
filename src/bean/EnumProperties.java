package bean;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 林雾
 * @Date 2018/9/22/022
 * @Description
 */
@Getter
@Setter
public class EnumProperties {
    private String code;
    private String msg;
    private Button propertiesDeleteButton;
    private TableView enumProperties;
    private EnumBean bean;

    public EnumProperties(TableView enumProperties,EnumBean bean){
        this.bean = bean;
        this.enumProperties = enumProperties;
        this.propertiesDeleteButton = new Button();
        propertiesDeleteButton.setText("删除");
        propertiesDeleteButton.setOnMouseClicked(event -> {
            enumProperties.getItems().remove(this);
            if(this.bean!=null){
                //需要在删除，行切换的时候会重新加载List
                this.bean.getProperties().remove(this);
            }
        });
    }
}
