/*  Permission Group	                        Permissions
 *  android.permission-group.CALENDAR           android.permission.READ_CALENDAR
 *                                              android.permission.WRITE_CALENDAR
 *
 *  android.permission-group.CAMERA             android.permission.CAMERA
 *
 *  android.permission-group.CONTACTS           android.permission.READ_CONTACTS
 *                                              android.permission.WRITE_CONTACTS
 *                                              android.permission.GET_ACCOUNTS
 *
 *  android.permission-group.LOCATION           android.permission.ACCESS_FINE_LOCATION
 *                                              android.permission.ACCESS_COARSE_LOCATION
 *
 *  android.permission-group.MICROPHONE         android.permission.RECORD_AUDIO
 *
 *  android.permission-group.PHONE              android.permission.READ_PHONE_STATE
 *                                              android.permission.CALL_PHONE
 *                                              android.permission.READ_CALL_LOG
 *                                              android.permission.WRITE_CALL_LOG
 *                                              com.android.voicemail.permission.ADD_VOICEMAIL
 *                                              android.permission.USE_SIP
 *                                              android.permission.PROCESS_OUTGOING_CALLS
 *
 *  android.permission-group.SENSORS            android.permission.BODY_SENSORS
 *  android.permission-group.SMS                android.permission.SEND_SMS
 *                                              android.permission.RECEIVE_SMS
 *                                              android.permission.READ_SMS
 *                                              android.permission.RECEIVE_WAP_PUSH
 *                                              android.permission.RECEIVE_MMS
 *                                              android.permission.READ_CELL_BROADCASTS
 *
 *  android.permission-group.STORAGE            android.permission.READ_EXTERNAL_STORAGE
 *                                              android.permission.WRITE_EXTERNAL_STORAGE
 */
package com.ewq.tools.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import com.bqs.risk.df.android.BqsDF;
import com.bqs.risk.df.android.BqsParams;
import com.bqs.risk.df.android.OnBqsContactsListener;
import com.bqs.risk.df.android.OnBqsDFListener;
import com.ewq.tools.BuildConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description: ??????????????????????????????
 * ???Application????????? PermissionCheck.init(...)??????
 */
@SuppressWarnings("unused")
public class PermissionCheck {
    /**
     * ??????????????????
     */
    public static final int REQUEST_CODE_STORAGE = 0xAAA1;
    /**
     * ??????????????????
     */
    public static final int REQUEST_CODE_PHONE = 0xAAA2;
    /**
     * ??????????????????
     */
    public static final int REQUEST_CODE_SMS = 0xAAA3;
    /**
     * ??????????????????
     */
    public static final int REQUEST_CODE_ALL = 0xAAAA;
    /**
     * ???????????????????????????
     */
    public static final int REQUEST_CODE_BQS = 0xAAAB;
    //??????????????????
    public static String BQSPARTNERID = "haoxin";
    /**
     * ?????????
     */
    private static String ROOT_PATH;
    /**
     * ????????????
     */
    private static String TOAST;

    private PermissionCheck() {
    }

    public static PermissionCheck getInstance() {
        return PermissionCheckInstance.instance;
    }

    /**
     * ?????????
     *
     * @param root_path ?????????
     * @param toast     ????????????
     *                  E.G.  ????????????????????????????????????????????????????????????
     */
    public static void init(String root_path, String toast) {
        ROOT_PATH = root_path;
        TOAST = toast;
    }

    public static boolean selfPermissionGranted(Context context, String permission) {
        // For Android < Android M, self permissions are always granted.
        boolean result = true;
        int targetSdkVersion = -1;
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                // use Context#checkSelfPermission
                result = context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                result = PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
            }
            Log.d("permission", permission + result);
        }
        return result;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param activity          {@link Activity}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(@NonNull Activity activity, @NonNull List<String>
            deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRationalePermissions(activity, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint("LongLogTag")
    public static boolean hasAlwaysDeniedPermission(@NonNull Activity activity, @NonNull String[] deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            Log.d("hasAlwaysDeniedPermission", deniedPermission);
            Log.d("shouldShowRationalePermissions(activity, deniedPermission)", "" + shouldShowRationalePermissions(activity, deniedPermission));
            if (!shouldShowRationalePermissions(activity, deniedPermission)) {
                Log.d("hasAlwaysDeniedPermission", deniedPermission);
                return true;
            }
        }
        return false;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment          {@link androidx.fragment.app.Fragment}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(@NonNull Fragment fragment, @NonNull
            List<String>
            deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRationalePermissions(fragment, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    static boolean shouldShowRationalePermissions(Object o, String... permissions) {
        boolean rationale = false;
        boolean isOk = false;
        for (String permission : permissions) {
            if (o instanceof Activity) {
                Log.d("rationale", "Activity" + ActivityCompat.shouldShowRequestPermissionRationale((Activity) o, permission));
                rationale = ActivityCompat.shouldShowRequestPermissionRationale((Activity) o, permission);
                isOk = selfPermissionGranted((Activity) o, permission);
            } else if (o instanceof Fragment) {
                Log.d("rationale", "Fragment");
                rationale = ((Fragment) o).shouldShowRequestPermissionRationale(permission);
                isOk = selfPermissionGranted(((Fragment) o).getActivity(), permission);
            }
            Log.d("rationale", rationale + "");
            //??????????????????????????????
            if (isOk)
                return true;
            //???????????????????????????????????????
            if (rationale)
                return true;
        }
        return false;
    }

    /**
     * ????????????
     *
     * @param activity    activity
     * @param permissions ????????????
     *                    <p>
     *                    Manifest.permission.WRITE_EXTERNAL_STORAGE
     *                    Manifest.permission.CALL_PHONE
     *                    Manifest.permission.READ_SMS
     *                    Manifest.permission.RECEIVE_SMS
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void askForPermissions(Activity activity, List<String> permissions, int requestCode) {
        // ????????????android6.0????????????????????????????????????????????????????????????
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            initBqs();
            return;
        }
        List<String> permissionTemp = new ArrayList<>();
        permissionTemp.addAll(permissions);
        for (String permission : permissions) {
            // PackageManager.PERMISSION_GRANTED    ????????????
            // PackageManager.PERMISSION_DENIED     ????????????
            // ???????????????????????????????????????????????????????????????
            System.out.println("permission" + permission);
            if (selfPermissionGranted(activity, permission)) {
                permissionTemp.remove(permission);
            }
        }
        if (hasAlwaysDeniedPermission(activity, permissionTemp)) {
            showAskDialog(activity);
        } else {
            System.out.println("permissionTemp:" + permissionTemp.size());
            if (permissionTemp.size() > 0) {
                activity.requestPermissions(permissionTemp.toArray(new String[permissionTemp.size()]), requestCode);
            } else {
                initBqs();
            }
        }
    }

    public void askForPermissions(Activity activity, String[] permissions, int requestCode) {
        try {
            if (null == permissions || permissions.length <= 0) {
                return;
            }
            System.out.println("askForPermissions");
            List<String> list = new ArrayList<>();
            Collections.addAll(list, permissions);
            askForPermissions(activity, list, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ????????????
     *
     * @param context    context
     * @param permission ?????????????????????
     * @return ?????????????????????
     * true - ??????
     * false - ????????????
     */
    public boolean checkPermission(Context context, String permission) {
        // ????????????android6.0????????????????????????????????????????????????????????????
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        int verify = ContextCompat.checkSelfPermission(context, permission);
        // PackageManager.PERMISSION_GRANTED    ????????????
        // PackageManager.PERMISSION_DENIED     ????????????
        return verify == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * ???????????????????????????
     *
     * @param activity     activity
     * @param requestCode  requestCode
     * @param permissions  ????????????
     * @param grantResults ????????????
     */
    public void onRequestPermissionsResult(final Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_STORAGE || requestCode == REQUEST_CODE_PHONE || requestCode == REQUEST_CODE_SMS || requestCode == REQUEST_CODE_ALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // ??????????????????????????????????????????????????????ROOT???????????????????????????DATA?????????
                initApp(requestCode);
            }
        }
        if (requestCode == REQUEST_CODE_BQS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // ??????????????????????????????????????????
                initBqs();
            }
        }
        Log.e("permission", "onRequestPermissionsResult");
        Log.e("requestCode", requestCode + "");
        if (requestCode == REQUEST_CODE_STORAGE || requestCode == REQUEST_CODE_ALL) {
            needAskAgainForPermissions(activity, permissions);
        }
    }

    /**
     * ??????????????????????????????
     */
    private void initApp(int requestCode) {
        if (requestCode == REQUEST_CODE_STORAGE || requestCode == REQUEST_CODE_ALL) {
            File filePath = new File(ROOT_PATH);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
        }
    }

    /**
     * ????????????????????????????????????????????????AND?????????????????????????????????????????????
     *
     * @param activity    activity
     * @param permissions ????????????
     * @return ????????????
     * true - ??????????????????
     * false - ?????????????????????
     */
    private boolean needAskAgainForPermissions(Activity activity, String[] permissions) {
        for (String permission : permissions) {
            switch (permission) {
                // ???APP?????????????????????????????????????????????????????????????????????
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    // ???????????????????????????????????????????????????????????????????????????true???????????????
                    if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED
                            && !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                        // false????????????????????????????????????Intent????????????APP setting??????
                        showAskDialog(activity);
                        return true;
                    }

                default:
                    break;
            }
        }
        return false;
    }

    /**
     * ????????????dialog
     */
    private void showAskDialog(final Activity activity) {
        new AlertDialog.Builder(activity).setMessage("permission failed")
                .setNegativeButton("??????", null)
                .setPositiveButton("?????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ????????????????????????
                        try {
                            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);

                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + info.packageName));
                            activity.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).create().show();
    }

    /**
     * ???????????????????????????????????????
     */
    public void initBqs() {
        BqsDF.setOnBqsContactsListener(new OnBqsContactsListener() {
            @Override
            public void onSuccess(String tokenKey) {
                if (BuildConfig.DEBUG)
                    System.out.println("====??????????????????????????? tokenKey=" + tokenKey);
            }

            @Override
            public void onGatherResult(boolean hasContacts, boolean hasCallRecord, boolean hasSmsRecord) {
                if (BuildConfig.DEBUG)
                    System.out.println("====??????????????????????????? hasContacts=" + hasContacts + ",hasCallRecord=" + hasCallRecord + ",hasSmsRecord=" + hasSmsRecord);
            }

            @Override
            public void onFailure(String resultCode, String resultDesc) {
                if (BuildConfig.DEBUG)
                    System.out.println("====??????????????????????????? resultCode=" + resultCode + ",resultDesc=" + resultDesc);
            }
        });
        BqsDF.setOnBqsDFListener(new OnBqsDFListener() {
            @Override
            public void onSuccess(String tokenKey) {
                //????????????????????????
                if (BuildConfig.DEBUG)
                    System.out.println("====???????????????????????? tokenkey=" + tokenKey);
            }

            @Override
            public void onFailure(String resultCode, String resultDesc) {
                //????????????????????????
                if (BuildConfig.DEBUG)
                    System.out.println("====???????????????????????? resultCode=" + resultCode + ",resultDesc=" + resultDesc);
            }
        });
        BqsParams params = new BqsParams();
        params.setPartnerId(BQSPARTNERID);
        params.setGatherCallRecord(true);
        params.setGatherContact(true);
        params.setGatherSMSRecord(true);
        params.setDevMode(BuildConfig.DEBUG);
        BqsDF.initialize(ActivityManage.peek(), params);
    }

    private static class PermissionCheckInstance {
        static PermissionCheck instance = new PermissionCheck();
    }
}
