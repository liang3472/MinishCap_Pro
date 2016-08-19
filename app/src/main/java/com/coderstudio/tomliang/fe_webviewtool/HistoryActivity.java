package com.coderstudio.tomliang.fe_webviewtool;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coderstudio.tomliang.fe_webviewtool.adapters.HistoryAdapter;
import com.coderstudio.tomliang.fe_webviewtool.bean.History;
import com.coderstudio.tomliang.fe_webviewtool.dao.HistoryDao;
import com.coderstudio.tomliang.fe_webviewtool.event.QueryHistoryEvent;
import com.coderstudio.tomliang.fe_webviewtool.itf.HistoryOperationI;
import com.coderstudio.tomliang.fe_webviewtool.manager.ThreadManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lianghangbing on 16/8/2.
 */
public class HistoryActivity extends BaseActivity implements HistoryOperationI{

    public static final int OPEN_HISTORY = 30;
    public static final String HISTORY_RESULT = "RESULT";
    private HistoryDao historyDao;
    @BindView(R.id.lv_history)
    protected ListView lvHistory;
    private HistoryAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_history;
    }

    @Override
    protected void initView() {
        historyDao = AppContext.getInstance().getDaoSession().getHistoryDao();

        mAdapter = new HistoryAdapter(this);
        lvHistory.setAdapter(mAdapter);
        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(HISTORY_RESULT, mAdapter.getItem(position).getUrl());
                setResult(OPEN_HISTORY, intent);
                finish();
            }
        });

        queryHistory();
    }

    private void queryHistory() {
        ThreadManager.getShortPool().execute(new Runnable() {
            @Override
            public void run() {
                List<History> historys = getHistories();
                QueryHistoryEvent event = new QueryHistoryEvent();
                event.setCode(this.hashCode());
                event.setHistories(historys);
                EventBus.getDefault().post(event);
            }
        });
    }

    @Override
    public void addHistory(String url) {

    }

    @Override
    public List<History> getHistories() {
        return historyDao.queryBuilder().orderDesc(HistoryDao.Properties.Count).list();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onQueryHistoryEvent(QueryHistoryEvent event){
        mAdapter.resetDataSource(event.getHistories());
    }
}
