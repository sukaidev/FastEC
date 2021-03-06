package com.sukaidev.latte_core.delegates;

/**
 * Created by sukaidev on 2019/01/18.
 */
public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
