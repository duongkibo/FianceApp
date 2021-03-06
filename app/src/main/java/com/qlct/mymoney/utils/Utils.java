package com.qlct.mymoney.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.SpannableString;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utils {

    public static boolean arePermissionsGranted(Context context, String[] permissions) {
        if (permissions == null || permissions.length == 0) {
            return false;
        }

        for (String permission : permissions) {
            if (!isPermissionGranted(context, permission)) {
                return false;
            }
        }

        return true;
    }


    public static boolean isServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String decryptPhoneDB(String key, String data) {
        try {
            //128 bits
            int CIPHER_KEY_LEN = 16;
            if (key.length() < CIPHER_KEY_LEN) {
                int numPad = CIPHER_KEY_LEN - key.length();
                StringBuilder keyBuilder = new StringBuilder(key);
                for (int i = 0; i < numPad; i++) {
                    keyBuilder.append("0"); //0 pad to len 16 bytes
                }
                key = keyBuilder.toString();
            } else if (key.length() > CIPHER_KEY_LEN) {
                key = key.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
            }

            String[] parts = data.split(":");
            IvParameterSpec iv = new IvParameterSpec(Base64.decode(parts[parts.length - 1], Base64.DEFAULT));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.ISO_8859_1), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < parts.length - 1; i++) {
                builder.append(parts[i]);
            }
            byte[] decodedEncryptedData = Base64.decode(builder.toString(), Base64.DEFAULT);
            byte[] original = cipher.doFinal(decodedEncryptedData);
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String Number(String phone) {
        if (phone == null) return null;
        try {
            String s = phone.trim();
            String formattedNumber = "";
            if (s.length() <= 3 || s.contains("*") || s.contains("#")) {
                return s;
            }
            if (s.charAt(0) != '+') {
                formattedNumber += s.substring(0, 3);
                if (s.length() < 6) {
                    formattedNumber += " " + s.substring(3);
                }
                if (s.length() >= 6) {
                    formattedNumber += " " + s.substring(3, 6);
                }
                if (s.length() >= 8) {
                    formattedNumber += " " + s.substring(6, 8);
                }
                if (s.length() >= 10) {
                    formattedNumber += " " + s.substring(8);
                }

                return formattedNumber.trim();
            } else {
                formattedNumber += s.substring(0, 3);
                if (s.length() < 5) {
                    formattedNumber += " " + s.substring(3);
                }
                if (s.length() >= 5) {
                    formattedNumber += " " + s.substring(3, 5);
                }
                if (s.length() >= 8) {
                    formattedNumber += " " + s.substring(5, 8);
                }
                if (s.length() >= 10) {
                    formattedNumber += " " + s.substring(8, 10);
                }

                if (s.length() >= 12) {
                    formattedNumber += " " + s.substring(10);
                }
                return formattedNumber.trim();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static String removepecialCharacters(String yourtext) {
        if (isEmpty(yourtext)) return yourtext;
        return yourtext.replaceAll("[-+.^:,()]", "").replaceAll("\\s", "");
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null && inputManager != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }


    public static void call(Context mContext, String phone) {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Uri uri = Uri.parse("tel:" + Uri.encode(phone));
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            mContext.startActivity(intent);
        }
    }

    public static String fix84(String s) {
        if (s == null || Utils.isEmpty(s)) return s;
        if (s.length() <= 3) return s;
        if (s.substring(0, 3).equals("+84")) {
            return "0" + s.substring(3);
        }
        if (s.substring(0, 2).equals("84")) {
            return "0" + s.substring(2);
        } else {
            return s;
        }
    }


    public static boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isEmpty(CharSequence string) {
        return string == null || string.toString().trim().isEmpty();
    }

    public static boolean isEmpty(int i) {
        return i <= 0;
    }

    /**
     * @param strNumber
     * @param type      0 => hiển thị 1000
     *                  1 => hiển thị 1.000
     *                  2 => hiển thị 1000,0
     *                  3 => hiển thị 1000,00
     *                  4 => hiển thị 1000,000
     *                  5 => hiển thị 1000,0000
     * @return
     */
    public static String formatNumber(String strNumber, int type) {
        DecimalFormat decimalFormat;
        if (Utils.isEmpty(strNumber)) return strNumber;
        try {
            double number = Double.parseDouble(strNumber);
            switch (type) {
                case 0:
                    return strNumber.replaceAll("[-+.^:,()]", "").replaceAll("\\s", "");
                case 1:
                    decimalFormat = new DecimalFormat("###,###,###");
                    return decimalFormat.format(number).replaceAll(",", ".");
                case 2:
                    decimalFormat = new DecimalFormat("###,###,###.#");
                    return decimalFormat.format(number);
                case 3:
                    decimalFormat = new DecimalFormat("###,###,###.##");
                    return decimalFormat.format(number);
                case 4:
                    decimalFormat = new DecimalFormat("###,###,###.###");
                    return decimalFormat.format(number);
                case 5:
                    decimalFormat = new DecimalFormat("###,###,###.####");
                    return decimalFormat.format(number);
                default:
                    return strNumber;
            }
        } catch (Exception ex) {
            return strNumber;
        }
    }


    public static void openAppSettings(Context context) {
        context.startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", context.getPackageName(), null)));
    }

    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String convertStringNotAccent(String str) {
        if (Utils.isEmpty(str)) return str;
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static boolean containsIgnoreCase(String src, String what) {
        if (Utils.isEmpty(src) || Utils.isEmpty(what)) return false;
        String tempSrc = convertStringNotAccent(src.trim());
        String tempWhat = convertStringNotAccent(what.trim());
        final int length = tempWhat.length();
        if (length == 0)
            return true;

        final char firstLo = Character.toLowerCase(tempWhat.charAt(0));
        final char firstUp = Character.toUpperCase(tempWhat.charAt(0));

        for (int i = tempSrc.length() - length; i >= 0; i--) {
            final char ch = tempSrc.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;
            if (tempSrc.regionMatches(true, i, tempWhat, 0, length))
                return true;
        }
        return false;
    }

    public static SpannableString toSpannableString(String text) {
        if (Utils.isEmpty(text)) {
            return null;
        }

        return new SpannableString(HtmlCompat.fromHtml(text.replace("\n", "<br/>"), HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
    }

    public static void onDisplayPopupPermission(Context context) {
        if (!isMIUI()) {
            return;
        }
        try {
            // MIUI 8
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", context.getPackageName());
            context.startActivity(localIntent);
            return;
        } catch (Exception ignore) {
        }
        try {
            // MIUI 5/6/7
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", context.getPackageName());
            context.startActivity(localIntent);
            return;
        } catch (Exception ignore) {
        }
        // Otherwise jump to application details
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static boolean isMIUI() {
        String device = Build.MANUFACTURER;
        if (device.equals("Xiaomi")) {
            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                return prop.getProperty("ro.miui.ui.version.code", null) != null
                        || prop.getProperty("ro.miui.ui.version.name", null) != null
                        || prop.getProperty("ro.miui.internal.storage", null) != null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public static String loadJSONFromAsset(Context context, String link) {
        String json;
        try {
            InputStream is = context.getAssets().open(link);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
