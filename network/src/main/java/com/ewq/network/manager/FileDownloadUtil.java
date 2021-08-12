package com.ewq.network.manager;

import android.Manifest;
import android.content.Context;

import com.ewq.tools.common.BaseParams;
import com.ewq.tools.log.Logger;
import com.ewq.tools.utils.PermissionCheck;
import com.ewq.tools.utils.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class FileDownloadUtil {
    private static final String TAG = "DownloadFile";
    public static boolean downLoadCanceFlag = true;

    public static void setDownLoadCanceFlag(boolean downLoadCanceFlag) {
        FileDownloadUtil.downLoadCanceFlag = downLoadCanceFlag;
    }

    /**
     * @param body     文件流
     * @param filename 文件名,含后缀
     * @Desction :
     * Creater at 2019/8/20 10:09
     * * 保存文件
     */
    public static boolean writeResponseBodyToDisk(Context context, ResponseBody body, String filename, DownloadListener downloadListener) {
        Logger.i(TAG, "下载完成，准备写入文件.");
        // 如果没有读写SD卡的权限，则不写入文件
        if (null != context && !PermissionCheck.getInstance().checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Logger.i(TAG, "请求读写SD卡权限");
            downloadListener.onFailure("请求读写SD卡权限");
            return false;
        }
        // 目录不存在  则创建
        File dir = new File(BaseParams.ROOT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            File futureStudioIconFile = new File(BaseParams.DOWNLOAD_PATH + File.separator + filename);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                downLoadCanceFlag = true;//开始下载
                while (downLoadCanceFlag) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Logger.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                    //计算当前下载百分比，并经由回调传出
                    downloadListener.onProgress((int) (100 * fileSizeDownloaded / fileSize));

                }
                outputStream.flush();
                //当百分比为100时下载结束，调用结束回调，并传出下载后的本地路径
                if ((int) (100 * fileSizeDownloaded / fileSize) == 100) {
                    downloadListener.onFinish(futureStudioIconFile.getPath()); //下载完成
                }
                //Cutscenes.dismiss(true);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                downloadListener.onFailure("下载失败");
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onFailure("下载失败");
            return false;
        }
    }

    /**
     * 保存文件
     *
     * @param body     文件流
     * @param filename 文件名,含后缀
     */
    public static boolean writeResponseBodyToDisk(Context context, ResponseBody body, String filename) {
        Logger.i(TAG, "下载完成，准备写入文件.");
        // 如果没有读写SD卡的权限，则不写入文件
        if (null != context && !PermissionCheck.getInstance().checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Logger.i(TAG, "请求读写SD卡权限");
            return false;
        }
        // 目录不存在  则创建
        File dir = new File(BaseParams.ROOT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            File futureStudioIconFile = new File(BaseParams.ROOT_PATH + File.separator + filename);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Logger.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                ToastUtil.toast("下载成功");
                //Cutscenes.dismiss(true);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void downLoadUpdate() {

        // OkHttpClient.Builder

    }

    /**
     * 文件是否存在
     */
    public static boolean isExists(String filename) {
        try {
            File file = new File(BaseParams.ROOT_PATH + File.separator + filename);
            if (!file.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
