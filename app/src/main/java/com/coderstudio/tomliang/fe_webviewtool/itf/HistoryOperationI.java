package com.coderstudio.tomliang.fe_webviewtool.itf;

import com.coderstudio.tomliang.fe_webviewtool.bean.History;

import java.util.List;

/**
 * Created by lianghangbing on 16/8/2.
 */
public interface HistoryOperationI {
    void addHistory(String url);
    List<History> getHistories();
}
