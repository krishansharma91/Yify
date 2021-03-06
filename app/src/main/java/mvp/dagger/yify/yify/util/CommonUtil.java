package mvp.dagger.yify.yify.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import mvp.dagger.yify.yify.BaseApp;
import mvp.dagger.yify.yify.api.util.CancelableCallback;

import static mvp.dagger.yify.yify.BaseApp.getContext;


/**
 * Created by krishan on 1/4/15.
 */
public class CommonUtil {

    // Dummy private constructor in order to avoid initialization of class
    private CommonUtil() {
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    public static int getAppVersion() {
        try {
            Context context = getContext();
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Gets the instance of shared preferences
     */

    public static SharedPreferences getPreferences() {
        return getContext().getSharedPreferences(Constants.PREFERENCE_FILE_NAME,
                Context.MODE_PRIVATE);
    }


    /**
     * @param message message to be displayed in Toast.
     */
    public static void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param message message to be displayed in Toast.
     */
    public static void showToast(int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param fromActivity the activity from where we want to switch
     * @param toActivity   the class name of the  activity where we are switching to
     */

    public static void switchActivity(Activity fromActivity, Class toActivity) {
        Intent intent = new Intent(fromActivity, toActivity);
        fromActivity.startActivity(intent);
    }

    public static boolean hasGps() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    public static String loadJSONFromAsset(String filepath) {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open(filepath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Get's the api key for this application
     */
    public static String getAppKey() {
        String key = null;
        try {
            InputStream is = getContext().getAssets().open("app_key");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            key = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return key;

    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static void releaseCallback(CancelableCallback<? extends Object> callback) {
        if (callback != null) {
            callback.cancel();
            callback = null;
        }
    }


    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp) {
        Resources resources = BaseApp.getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static boolean isGreaterThanOrEqualToLolipop() {
        return Build.VERSION.SDK_INT >= 21;

    }
}

