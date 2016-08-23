package com.coderstudio.tomliang.minishcap.itf;

import com.coderstudio.tomliang.minishcap.bean.History;

import java.util.List;

/**
 * Created by lianghangbing on 16/8/2.
 */
public interface HistoryOperationI {
    void addHistory(String url);
    List<History> getHistories();
}
