package com.sukaidev.latte_core.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * Created by sukaidev on 2019/05/10.
 * 请求码存储.
 */
public class RequestCodes {

    public static final int TAKE_PHOTO = 4;
    public static final int PICK_PHOTO = 5;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int Crop_ERROR = UCrop.RESULT_ERROR;
    public static final int SCAN = 8;
}
