package com.wang.xiaoyu.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.wang.xiaoyu.Holder.BaseHolder;
import com.wang.xiaoyu.Holder.MoreHolder;
import com.wang.xiaoyu.Utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by 小 on 2018/9/17.
 */

public abstract class MyAadapter<T> extends BaseAdapter {

    //注意: 此处必须要从0开始写
    private static final int TYPE_NORMAL = 0;// 正常布局类型
    private static final int TYPE_MORE = 1;// 加载更多类型

    public ArrayList<T> data;
    public MyAadapter(ArrayList<T> data){
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size() + 1;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //返回条目类型个数
    @Override
    public int getViewTypeCount() {
        return 2; // 普通布局和加载跟多布局
    }

    // 返回当前位置应该展示那种布局类型
    @Override
    public int getItemViewType(int position) {
       if(position==getCount()-1){
            return TYPE_MORE;
       }else{
           return getInnerType();
       }
    }

    // 子类可以重写此方法来更改返回的布局类型
    public int getInnerType() {
        return TYPE_NORMAL;// 默认就是普通类型
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if(convertView==null){
            // 1. 加载布局文件
            // 2. 初始化控件 findViewById
            // 3. 打一个标记tag
            if(getItemViewType(position)==TYPE_MORE){
                //返回加载更多布局
                holder=new MoreHolder(hasMore());
            }else{
                holder=getHolder();// 子类返回具体对象
            }
        }else{
            holder= (BaseHolder) convertView.getTag();
        }

       // 4. 根据数据来刷新界面
        if(getItemViewType(position)!=TYPE_MORE){
            holder.setData(getItem(position));
        }else{
            //加载更多布局
            //一旦加载更多布局展示出来，就要调用此方法
            //只有在有加载更多时才加载
            MoreHolder moreHolder= (MoreHolder) holder;
            if(moreHolder.getData()==MoreHolder.STATE_MORE_MORE){
                loadingMore(moreHolder);
            }

        }

        return holder.getRootView();

    }

    //可以重新此方法，来控制是否加载更多
    public boolean hasMore(){
        return true; //默认都是有加载更多
    }

    public abstract  BaseHolder getHolder();

    public boolean isLoadingMore=false; //判断是否加载更多
    //请求更多数据
    public void loadingMore(final MoreHolder holder){

        if(!isLoadingMore){
            isLoadingMore=true;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    final ArrayList<T> moreData = onLoadingMore();
                    //要刷新界面，放入主线程
                    UIUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            if(moreData!=null){
                                // 每一页有10条数据, 如果返回的数据小于20条, 就认为到了最后一页了
                                if(moreData.size() < 6){
                                    //没有跟多数据了
                                    holder.setData(MoreHolder.STATE_MORE_NONE);
                                    Toast.makeText(UIUtils.getContext(),"没有更多数据了",Toast.LENGTH_SHORT).show();

                                }else{
                                    //还有更多数据
                                    holder.setData(MoreHolder.STATE_MORE_MORE);
                                }

                                //将更多数据追加到集合中
                                data.addAll(moreData);
                                //刷新数据
                                MyAadapter.this.notifyDataSetChanged();

                            }else{
                                //加载跟多失败
                                holder.setData(MoreHolder.STATE_MORE_ERROR);
                            }

                            isLoadingMore=false;
                        }
                    });

                }
            }).start();
        }

    }
    //加载更多数据，必须由子类完成
    public abstract ArrayList<T> onLoadingMore();
}
