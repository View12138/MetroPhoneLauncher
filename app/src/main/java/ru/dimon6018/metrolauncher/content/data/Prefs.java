package ru.dimon6018.metrolauncher.content.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.Map;

public class Prefs implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String FILE_NAME = "Prefs";
    public static final String APP_PREFIX = "app:";
    public static final String APP_POS_PREFIX = "pos:";
    private final Context mContext;
    private final SharedPreferences prefs;
    public Prefs(Context context) {
        mContext = context;
        prefs = mContext.getSharedPreferences(FILE_NAME, 0);
    }
    public void addApp(String packag, String label) {
        prefs.edit()
                .putString(APP_PREFIX + packag, label)
                .apply();
    }
    public void setPos(String packag, int Pos) {
        prefs.edit()
                .putInt(APP_POS_PREFIX + packag, Pos)
                .apply();
    }
    public int getPos(String packag) {
        return prefs.getInt(APP_POS_PREFIX + packag, 0);
    }
    public void removeApp(String packag) {
        prefs.edit()
                .remove(APP_PREFIX + packag)
                .apply();
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, @Nullable String s) {
    }
        public LinkedList<App> getAppsPackage() {
            LinkedList<App> apps = new LinkedList<>();
            Map<String, ?> map = prefs.getAll();
            for (String key : map.keySet()) {
                App app = new App();
                if (key.startsWith(APP_PREFIX)) {
                    CharSequence seed = key.substring(APP_PREFIX.length()); //package
                    app.app_label = (CharSequence) map.get(key); //name
                    app.app_package = seed;
                    apps.add(app);
                }
            }
            return apps;
        }

}

