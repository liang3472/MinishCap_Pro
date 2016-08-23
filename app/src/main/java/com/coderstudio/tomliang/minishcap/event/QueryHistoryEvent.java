package com.coderstudio.tomliang.minishcap.event;

import com.coderstudio.tomliang.minishcap.bean.History;

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
