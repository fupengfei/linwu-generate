package bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author 林雾
 * @Date 2018/9/24/024
 * @Description
 */
@Setter
@Getter
public class FileChoose {
    private boolean controller = false;
    private boolean remote = false;
    private boolean remoteImpl = false;
    private boolean service = false;
    private boolean serviceImpl = false;
    private boolean dao = false;
    private boolean mapper = false;
    private boolean entity = false;
    private boolean enhanced = false;
    private boolean req = false;
    private boolean resp = false;
    private boolean xml = false;
    private boolean query = false;

    public void all(){
        this.controller = true;
        this.remote = true;
        this.remoteImpl = true;
        this.service = true;
        this.serviceImpl = true;
        this.dao = true;
        this.mapper = true;
        this.entity = true;
        this.enhanced = true;
        this.req = true;
        this.resp = true;
        this.xml = true;
        this.query = true;
    }

    public void cancel(){
        this.controller = false;
        this.remote = false;
        this.remoteImpl = false;
        this.service = false;
        this.serviceImpl = false;
        this.dao = false;
        this.mapper = false;
        this.entity = false;
        this.enhanced = false;
        this.req = false;
        this.resp = false;
        this.xml = false;
        this.query = false;
    }

}
