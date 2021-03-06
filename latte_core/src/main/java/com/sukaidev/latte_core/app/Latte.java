package com.sukaidev.latte_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by sukaidev on 2019/01/16.
 */
public final class Latte {

    public static Configurator init(Context context) {
        getConfiguration().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfiguration() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplicationContext() {
        return (Context) Latte.getConfiguration().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return (Handler) Latte.getConfiguration().get(ConfigKeys.HANDLER.name());
    }
}
