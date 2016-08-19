package com.coderstudio.tomliang.fe_webviewtool.adapters;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianghangbing on 15/12/14.
 */
public abstract class MasterListAdapter<T> extends BaseAdapter {
    protected Context mContext;

    protected List<T> mListItems = new ArrayList<>();
    private List<T> mCheckedItems = new ArrayList<>(); // 存储预选中的对象
    private List<T> mCommitItems = new ArrayList<>(); // 存储当前生效的对象

    // 用于有checkbox的列表
    public MasterListAdapter(Context context) {
        this.mContext = context;
    }

    public MasterListAdapter(Context context, List<T> list) {
        this.mContext = context;
        mListItems.addAll(list);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public T getItem(int index) {
        if (mListItems != null) {
            T obj = mListItems.get(index);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }

    /**
     * 检查预选项是否选中
     *
     * @param item
     * @return
     */
    public boolean isChecked(T item) {
        return mCheckedItems.contains(item);
    }

    /**
     * 检查生效定选项是否选中
     *
     * @param item
     * @return
     */
    public boolean isCommitChecked(T item) {
        return mCommitItems.contains(item);
    }

    /**
     * 返回与选项所选中的对象列表
     *
     * @return
     */
    public List<T> getCheckedItems() {
        return mCheckedItems;
    }

    /**
     * 选中指定的预选项对象
     *
     * @param checkedItem
     */
    public void setCheckItem(T checkedItem) {
        if (!mCheckedItems.contains(checkedItem)) {
            mCheckedItems.add(checkedItem);
        }
    }

    /**
     * 选中指定的预选项集合对象
     *
     * @param items
     */
    public void setCheckItems(List<T> items) {
        if (items != null && items.size() > 0) {
            for (T checkedItem : items) {
                if (!mCheckedItems.contains(checkedItem)) {
                    mCheckedItems.add(checkedItem);
                }
            }
        }
    }

    /**
     * 选中指定的生效选项集合对象
     */
    public void setCommitItems() {
        mCommitItems.clear();
        mCommitItems.addAll(getCheckedItems());
    }

    /**
     * 选中指定的生效选项集合对象
     */
    public List<T> getCommitItems() {
        return mCommitItems;
    }

    /**
     * 取消选中的预选项对象
     *
     * @param checkedItem
     */
    public void removeCheckItem(T checkedItem) {
        mCheckedItems.remove(checkedItem);
    }

    /**
     * 清除所有预选项的选中状态
     */
    public void clearCheckedItem() {
        mCheckedItems.clear();
        notifyDataSetChanged();
    }

    public List<T> getItemList() {
        return mListItems;
    }

    /**
     * 返回指定index的对象
     *
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getItemModel(int index) {
        return getItem(index);
    }

    @Override
    public int getCount() {
        if (mListItems != null) {
            return mListItems.size();
        }
        return 0;
    }

    /**
     * 添加数据，并刷新列表
     *
     * @param list
     */
    public void addDateItems(List<T> list) {
        if (mListItems != null) {
            mListItems.addAll(list);
        } else {
            mListItems = list;
        }
        notifyDataSetChanged();
    }

    /**
     * 重新设置数据源，并刷新列表
     *
     * @param list
     */
    public void resetDataSource(List<T> list) {
        clearDataSource();
        clearCheckedItem();

        mListItems.addAll(list);
        this.notifyDataSetChanged();
    }

    /**
     * 清除集合
     */
    public void clearDataSource() {
        if (mListItems != null && mListItems.size() > 0) {
            mListItems.clear();
            this.notifyDataSetChanged();
        }
    }
}
