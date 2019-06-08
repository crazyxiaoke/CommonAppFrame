package com.hz.zxk.lib_commonframe.uitls.ioHandler;

/**
 * @author zhengxiaoke
 * @date 2019-06-08 23:21
 */
public class IOHandlerFactory implements IOFactory{

    private static IOHandlerFactory sInstance;

    private IOHandler memoryIOHandler;
    private IOHandler preferencesIOHandler;

    private IOHandlerFactory(){

    }


    public static IOHandlerFactory getInstance(){
        if(sInstance==null){
            synchronized (IOHandlerFactory.class){
                if(sInstance==null){
                    sInstance=new IOHandlerFactory();
                }
            }
        }
        return sInstance;
    }

    @Override
    public  IOHandler createIOHandler(Class<? extends IOHandler> ioHandlerClass) {
        try {
            ioHandlerClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return new PreferencesIOHandler();
    }

    public  IOHandler getMemoryIOHandler(){
        if(memoryIOHandler==null){
            memoryIOHandler=createIOHandler(MemoryIOHandler.class);
        }
        return memoryIOHandler;
    }

    public  IOHandler getPreferencesIOHandler(){
        if(preferencesIOHandler==null){
            preferencesIOHandler=createIOHandler(PreferencesIOHandler.class);
        }
        return preferencesIOHandler;
    }

    public  IOHandler getDefaultIOHandler(){
        return createIOHandler(PreferencesIOHandler.class);
    }

}
