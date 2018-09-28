package com.wang.xiaoyu.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.L;
import com.wang.xiaoyu.Utils.SwipelayoutManager;

import java.util.ArrayList;

/**
 * Created by 小 on 2018/9/25.
 */

public class ShopCarFragment extends BaseFragment {

    private RecyclerView mRv_shopcar;

    @Override
    public View initView() {
        View view = View.inflate(mActivity,R.layout.recyclerview_shopcar,null);
        mRv_shopcar = (RecyclerView) view.findViewById(R.id.rv_shopcar);

        return view;

    }

    @Override
    public void initData() {
        ArrayList<String> strings = new ArrayList<String>();
        for(int i=1;i<20;i++){
            strings.add("人民币"+i);
        }

        L.e("数量"+strings.get(0));
        //构建布局方式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);

        mRv_shopcar.setLayoutManager(linearLayoutManager);

        mRv_shopcar.setAdapter(new ShopcarAdapter(strings));

    }

    public static class ShopcarAdapter extends  RecyclerView.Adapter<ShopcarAdapter.ViewHolder>{

        private ArrayList<String> mStrings;
        static class ViewHolder extends RecyclerView.ViewHolder{
            Button mBu_shopcar;
            TextView mTv_shopcar_price;

            public ViewHolder(View itemView) {
                super(itemView);

                mTv_shopcar_price = (TextView) itemView.findViewById(R.id.tv_shopcar_price);
                mBu_shopcar = (Button) itemView.findViewById(R.id.bu_shopcar);
            }
        }

        public ShopcarAdapter(ArrayList<String> strings){

            mStrings=strings;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_shopcar,parent,false);

            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            String s=mStrings.get(position);

            holder.mTv_shopcar_price.setText(s);
            holder.mBu_shopcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mStrings.remove(position);
                    notifyDataSetChanged();
                    //说明当前的SwipeLayout已经关闭，需要让Manager清空一下
                    SwipelayoutManager.getInstance().clearCurrentLayout();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mStrings.size();
        }
    }
}
