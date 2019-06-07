package com.hz.zxk.lib_commonframe.widget.navigationbar;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengxiaoke
 * @date 2019-06-07 22:36
 */
public class AbsNavigationBar<B extends AbsNavigationBar.Builder> implements INavigation{

    private B mBuilder;
    private View mNavigationBar;

    protected AbsNavigationBar(B builder){
        this.mBuilder=builder;
        //创建mNavigationBar
        createNavigationBar();

    }

    @Override
    public void createNavigationBar(){
        mNavigationBar= LayoutInflater.from(mBuilder.mContext)
                .inflate(mBuilder.mLayoutId,mBuilder.mParent,false);
        //添加mNavigationBar参数
        attachNavigationParam();
        //添加到父容器中中
        attachParent(mNavigationBar,mBuilder.mParent);
    }

    @Override
    public void attachNavigationParam(){
        //设置TextView的文本
        Map<Integer,CharSequence> textMaps=mBuilder.mTextMaps;
        for (Map.Entry<Integer,CharSequence> entry : textMaps.entrySet()) {
            TextView textView=findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }

        //设置点击事件
        Map<Integer, View.OnClickListener> clickListenerMaps=mBuilder.mClickListenersMaps;
        for(Map.Entry<Integer, View.OnClickListener> entry:clickListenerMaps.entrySet()){
            View view=findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
    }

    public <T extends View> T findViewById(int resId){
        return mNavigationBar.findViewById(resId);
    }

    @Override
    public void attachParent(View navigationBar,ViewGroup parent){
        parent.addView(navigationBar,0);
    }

    public B getBuilder(){
        return mBuilder;
    }




    public abstract static class Builder<B extends Builder>{

        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;

        public Map<Integer,CharSequence> mTextMaps;
        public Map<Integer, View.OnClickListener> mClickListenersMaps;

        public Builder(Context context, @LayoutRes int layoutId, ViewGroup parent){
            this.mContext=context;
            this.mLayoutId=layoutId;
            this.mParent=parent;

            mTextMaps=new HashMap<>();
            mClickListenersMaps=new HashMap<>();
        }

        public B setText(@IdRes int resId, CharSequence charSequence){
            mTextMaps.put(resId,charSequence);
            return (B)this;
        }

        public B setOnClickListener(@IdRes int resId, View.OnClickListener clickListener){
            mClickListenersMaps.put(resId,clickListener);
            return (B)this;
        }

        public abstract AbsNavigationBar create();

    }
}
