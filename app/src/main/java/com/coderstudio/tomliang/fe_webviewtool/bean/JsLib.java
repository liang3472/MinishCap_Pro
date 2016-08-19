package com.coderstudio.tomliang.fe_webviewtool.bean;

/**
 * Created by lianghangbing on 16/8/3.
 */
public class JsLib {
    private String jsSrc;
    private String jsOnLoad;

    public JsLib() {
    }

    public JsLib(String jsSrc, String jsOnLoad) {
        this.jsSrc = jsSrc;
        this.jsOnLoad = jsOnLoad;
    }

    public String getJsSrc() {
        return jsSrc;
    }

    public void setJsSrc(String jsSrc) {
        this.jsSrc = jsSrc;
    }

    public String getJsOnLoad() {
        return jsOnLoad;
    }

    public void setJsOnLoad(String jsOnLoad) {
        this.jsOnLoad = jsOnLoad;
    }
}
