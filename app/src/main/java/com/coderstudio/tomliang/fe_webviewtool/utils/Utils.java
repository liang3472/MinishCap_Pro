package com.coderstudio.tomliang.fe_webviewtool.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.coderstudio.tomliang.fe_webviewtool.bean.JsLib;

import java.util.regex.Pattern;

/**
 * Created by lianghangbing on 16/7/25.
 */

public class Utils {
    private Utils() {

    }

    public static boolean isUrl(String strLink) {
        Pattern pattern = Pattern
                .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
        return pattern.matcher(strLink).matches();
    }

    public static void toastMsg(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static JsLib getInjectErudaJs() {
        JsLib lib = new JsLib();
        lib.setJsSrc("//liriliri.github.io/eruda/eruda.min.js");
        lib.setJsOnLoad("function () { eruda.init(); }");
        return lib;
    }

    public static JsLib getInjectLiveJs() {
        JsLib lib = new JsLib();
        lib.setJsSrc("http://livejs.com/live.js");
        lib.setJsOnLoad("undefined");
        return lib;
    }

    public static String injectJsLib(JsLib... libs) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:(function () { ");
        for (int i = 0; i < libs.length; i++) {
            if(libs[i] != null){
                sb.append("var script" + i + " = document.createElement('script');");
                sb.append("script" + i + ".src=\"" + libs[i].getJsSrc() + "\";");
                sb.append("script" + i + ".onload = " + libs[i].getJsOnLoad() + ";");
                sb.append("document.body.appendChild(script" + i + ");");
            }
        }
        sb.append("})();");

        return sb.toString();
    }
}
