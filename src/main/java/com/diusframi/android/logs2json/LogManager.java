package com.diusframi.android.logs2json;

import android.content.Context;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class LogManager {

    private String logFileName;

    public LogManager(String logFileName){
        this.logFileName = logFileName;
    }

    private String addLogJsonFormat(String tag, String log){
        String res = "";
        try{
            JSONObject joc = new JSONObject();
            joc.put("tag", tag);
            joc.put("log",log);
            joc.put("time", new Timestamp(System.currentTimeMillis()));
            res = joc.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public void addLogToFile(String tag, String log, Context context){
        File file = new File(context.getFilesDir()+"/"+logFileName);
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
                out.println(addLogJsonFormat(tag, log));
                out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
