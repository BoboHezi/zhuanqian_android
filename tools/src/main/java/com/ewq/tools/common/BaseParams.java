package com.ewq.tools.common;

import android.os.Environment;

import java.io.File;

public class BaseParams {
    /**
     * 根路径
     */
    public static final String ROOT_PATH = getSDPath() + "/zq";
    /**
     * 根路径
     */
    public static final String DOWNLOAD_PATH = getSDPath() + "/Download/zq";
    /**
     * 照片文件文件保存路径
     */
    public static final String PHOTO_PATH = ROOT_PATH + "/photo";
    /**
     * SP文件名
     */
    public static final String SP_NAME = "basic_params";
    /**
     * 视频
     */
    public static final String MEDIA_VIDEO = "video";
    /**
     * 音乐
     */
    public static final String MEDIA_MUSIC = "music";
    /**
     * 滤镜
     */
    public static final String MEDIA_FILTER = "filter";
    /**
     * 贴纸
     */
    public static final String MEDIA_STICKERS = "stickers";

    /**
     * 获取SD卡的根目录
     */
    public static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        if (sdDir == null) {
            return "";
        } else {
            return sdDir.toString();
        }
    }
}
