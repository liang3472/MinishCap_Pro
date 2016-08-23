package com.coderstudio.tomliang.minishcap;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coderstudio.tomliang.minishcap.R;
import com.coderstudio.tomliang.minishcap.bean.History;
import com.coderstudio.tomliang.minishcap.bean.JsLib;
import com.coderstudio.tomliang.minishcap.dao.HistoryDao;
import com.coderstudio.tomliang.minishcap.itf.HistoryOperationI;
import com.coderstudio.tomliang.minishcap.utils.SpUtils;
import com.coderstudio.tomliang.minishcap.utils.Utils;
import com.coderstudio.tomliang.minishcap.zxing.CaptureActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements HistoryOperationI {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.wv_content)
    protected WebView wvContent;
    @BindView(R.id.btn_goto)
    protected Button btnGoTo;
    @BindView(R.id.et_url)
    protected EditText etUrl;
    @BindView(R.id.progressbar)
    protected ProgressBar progressBar;
    private HistoryDao historyDao;
    private Drawer result;
    private AccountHeader headerResult;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        historyDao = AppContext.getInstance().getDaoSession().getHistoryDao();
        initToolBar();
        initAccountHeader();
        initDrawer();
        initWebView();
    }

    private void initAccountHeader() {
        IProfile profile = new ProfileDrawerItem().withName("Fe_WebViewTool").withEmail("liang3472@gmail.com").withIcon(R.mipmap.ic_launcher);
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile)
                .build();
    }

    private void initToolBar() {
        btnGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etUrl.getText().toString().startsWith("http")) {
                    etUrl.setText("http://" + etUrl.getText().toString());
                }

                if (!TextUtils.isEmpty(etUrl.getText())) {
                    if (Utils.isUrl(etUrl.getText().toString())) {
                        goToUrl(etUrl.getText().toString());
                    } else {
                        Utils.toastMsg(v, "不是一个链接");
                    }
                }
            }
        });
    }

    private void initDrawer() {
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDisplayBelowStatusBar(false)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .withShowDrawerOnFirstLaunch(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("扫码").withIcon(FontAwesome.Icon.faw_eye).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("检测WebGl兼容性").withIcon(FontAwesome.Icon.faw_question).withIdentifier(2).withSelectable(false),
                        new SwitchDrawerItem().withName("调试模式").withIcon(FontAwesome.Icon.faw_gamepad).withDescription("页面可调试").withDescriptionTextColor(Color.GRAY).withIdentifier(3).withCheckable(true).withChecked(SpUtils.isAutoFlush()).withOnCheckedChangeListener(new OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                SpUtils.setDebugEnable(isChecked);
                                wvContent.reload();
                            }
                        }).withChecked(SpUtils.isDebugEnable()),
                        new SwitchDrawerItem().withName("自动刷新").withIcon(FontAwesome.Icon.faw_bullhorn).withDescription("实时检测代码变动").withDescriptionTextColor(Color.GRAY).withIdentifier(4).withCheckable(true).withChecked(SpUtils.isAutoFlush()).withOnCheckedChangeListener(new OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                SpUtils.setAutoFlush(isChecked);
                                wvContent.reload();
                            }
                        }),
                        new PrimaryDrawerItem().withName("历史").withIcon(FontAwesome.Icon.faw_history).withIdentifier(5).withSelectable(false),
                        new PrimaryDrawerItem().withName("清除缓存").withIcon(GoogleMaterial.Icon.gmd_disc_full).withIdentifier(6).withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            if (drawerItem.getIdentifier() == 1) {
                                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), CaptureActivity.OPEN_QR);
                            } else if (drawerItem.getIdentifier() == 2) {
                                wvContent.loadUrl("file:///android_asset/testWebGL.html");
                            } else if (drawerItem.getIdentifier() == 5) {
                                startActivityForResult(new Intent(MainActivity.this, HistoryActivity.class), HistoryActivity.OPEN_HISTORY);
                            } else if (drawerItem.getIdentifier() == 6) {
                                Toast.makeText(MainActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
                            }
                        }

                        return false;
                    }
                })
                .build();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void enableWebViewDebugging() {
        WebView.setWebContentsDebuggingEnabled(true);
    }

    private void initWebView() {
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.getSettings().setDomStorageEnabled(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            enableWebViewDebugging();
        }
        wvContent.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (etUrl != null) {
                    etUrl.setText(url);
                }
                JsLib lib1 = null;
                JsLib lib2 = null;
                if(SpUtils.isDebugEnable()){
                    lib1 = Utils.getInjectErudaJs();
                }
                if(SpUtils.isAutoFlush()){
                    lib2 = Utils.getInjectLiveJs();
                }
                view.loadUrl(Utils.injectJsLib(lib1, lib2));
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Log.e("test", url);
            }
        });

        wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar != null) {
                    progressBar.setProgress(newProgress);
                    if (newProgress >= 100) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

        });
    }

    private void goToUrl(String url) {
        wvContent.loadUrl(url);
        addHistory(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case CaptureActivity.OPEN_QR:
                //扫描二维码返回
                if (null != data) {
                    String url = data.getStringExtra(CaptureActivity.QR_RESULT);
                    etUrl.setText(url);
                    goToUrl(url);
                }
                break;
            case HistoryActivity.OPEN_HISTORY:
                //扫描二维码返回
                if (null != data) {
                    String url = data.getStringExtra(HistoryActivity.HISTORY_RESULT);
                    etUrl.setText(url);
                    goToUrl(url);
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void addHistory(String url) {
        History history = new History(url);
        List<History> histories = historyDao.queryBuilder().where(HistoryDao.Properties.Url.eq(url)).list();
        if (histories != null && histories.size() > 0) {
            History old = histories.get(0);
            history.setCount(old.getCount() + 1);
        } else {
            history.setCount(1);
        }
        historyDao.insertOrReplace(history);
    }

    @Override
    public List<History> getHistories() {
        return null;
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (result != null && result.isDrawerOpen()) {
                result.closeDrawer();
                return false;
            }
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
        }
    }


}
