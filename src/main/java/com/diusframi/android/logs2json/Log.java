package com.diusframi.android.logs2json;

import android.content.Context;

public class Log {

    private LogManager logManager;
    private String tag;
    public boolean toFile = false;
    private static Context context;
    private static int statLevel;

    public static void init(Context context, int statLevel){
        Log.context = context;
        Log.statLevel = statLevel;
    }

    public Log(){
        logManager = new LogManager("applog.txt");
    }

    public Log(String tag, String fileName){
        this.tag = tag;
        logManager = new LogManager(fileName);
    }

    public boolean isToFile() {
        return toFile;
    }

    public void setToFile(boolean toFile) {
        this.toFile = toFile;
    }

    public void d(String tag, String log){
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String className = stack[statLevel].getClassName();
        String methodName = stack[statLevel].getMethodName();
        log = className +"::"+ methodName+"->"+log;

        if (toFile){
            logManager.addLogToFile(tag, log, context);
        }else{
            android.util.Log.d(tag, log);
        }
    }

}
