package com.hz.zxk.lib_commonframe.uitls.ioHandler;

import android.support.v7.widget.RecyclerView;

/**
 * @author zhengxiaoke
 * @date 2019-06-09 00:13
 */
public interface IOFactory {
    IOHandler  createIOHandler(Class<? extends IOHandler> ioHandlerClass);
}
