package com.coderstudio.tomliang.fe_webviewtool.event;

import com.coderstudio.tomliang.fe_webviewtool.bean.History;

import java.util.List;

/**
 * Created by lianghangbing on 16/8/2.
 */
public class QueryHistoryEvent extends BaseEvent{
    private List<History> histories;

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
