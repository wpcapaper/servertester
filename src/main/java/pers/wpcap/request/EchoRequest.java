package pers.wpcap.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: EchoRequest
 * Created by haisong
 * Description:
 */
public class EchoRequest implements Serializable {

    @JsonProperty("camel_case")
    private Integer camelCase;

    public Integer getCamelCase() {
        return camelCase;
    }

    public void setCamelCase(Integer camelCase) {
        this.camelCase = camelCase;
    }
}
