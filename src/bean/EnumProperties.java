package bean;

import com.google.gson.annotations.Expose;
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
    @Expose
    private String name;
    @Expose
    private String code;
    @Expose
    private String msg;
    @Expose(serialize = false)
    private Button propertiesDeleteButton;
    @Expose(serialize = false)
    private TableView enumProperties;
    @Expose(serialize = false)
    private EnumBean bean;

    public EnumProperties(EnumBean bean) {
        this.bean = bean;
    }

    public void initTableView(TableView enumProperties){
        this.enumProperties = enumProperties;
        initButton();
    }

    public void initButton(){
        this.propertiesDeleteButton = new Button();
        propertiesDeleteButton.setText("删除");
        propertiesDeleteButton.setOnMouseClicked(event -> {
            enumProperties.getItems().remove(this);
            if (this.bean != null) {
                //需要在删除，行切换的时候会重新加载List
                this.bean.getProperties().remove(this);
            }
        });
    }
}
