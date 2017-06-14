package pers.wpcap.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: EchoRequest
 * Created by haisong
 * Description: echo tester
 * https://stackoverflow.com/questions/8560348/different-names-of-json-property-during-serialization-and-deserialization
 */
public class EchoRequest implements Serializable {

    private Integer camelCase;

    public Integer getCamelCase() {
        return camelCase;
    }

    @JsonProperty("camelCase")
    public void setCamelCase(Integer camelCase) {
        this.camelCase = camelCase;
    }

    @JsonProperty("camel_case")
    public void setSnakeCamelCase(Integer camelCase) {
        this.camelCase = camelCase;
    }
}
