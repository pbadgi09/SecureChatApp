package com.example.admin.myapplication.utils;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 02-09-2016.
 */
public class Utils {
    public static final int TYPEIMAGE = 1;
    public static final int TYPEVIDEO = 2;
    private static final String CAMERADIRECTORY = "imagedirectory";


    public static Uri getOutpurMediaFile(int type) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), CAMERADIRECTORY);
        if (!file.exists()) {
            if (!file.mkdir()) {
                Log.e("error", "cannot create directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediafile;
        if (type==TYPEIMAGE){
            mediafile = new File(file.getPath(),File.separator+"ing_"+timeStamp+".jpg_");
        }else
        {
            mediafile = new File(file.getPath(),File.separator+"vid_"+timeStamp+".mp4");
        }
        return Uri.fromFile(mediafile);

    }

}
