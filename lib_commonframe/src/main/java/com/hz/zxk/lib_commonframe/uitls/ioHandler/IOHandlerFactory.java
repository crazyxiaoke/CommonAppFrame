package com.hz.zxk.lib_commonframe.uitls.ioHandler;

/**
 * @author zhengxiaoke
 * @date 2019-06-08 23:21
 */
public class IOHandlerFactory {

    public static IOHandler createIOHandler(Class<? extends IOHandler> ioHandler){
        try {
            ioHandler.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return new PreferencesIOHandler();
    }

    public static IOHandler getMemoryIOHandler(){
        return createIOHandler(MemoryIOHandler.class);
    }

    public static IOHandler getPreferencesIOHandler(){
        return createIOHandler(PreferencesIOHandler.class);
    }

    public static IOHandler getDefaultIOHandler(){
        return createIOHandler(PreferencesIOHandler.class);
    }

}
