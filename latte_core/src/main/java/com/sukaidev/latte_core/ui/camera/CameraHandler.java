package com.sukaidev.latte_core.ui.camera;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.FileUtils;
import com.sukaidev.latte_core.R;
import com.sukaidev.latte_core.delegates.PermissionCheckerDelegate;
import com.sukaidev.latte_core.util.file.FileUtil;

import java.io.File;

/**
 * Created by sukaidev on 2019/05/10.
 * 照片处理.
 */
public class CameraHandler implements View.OnClickListener {

    private final AlertDialog mDialog;
    private final PermissionCheckerDelegate mDelegate;


    public CameraHandler(PermissionCheckerDelegate delegate) {
        this.mDelegate = delegate;
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    final void beginCameraDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // 设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
        }
    }

    private String getPhotoName() {
        return FileUtil.getFileNameByTime("IMG", "jpg");
    }

    private void takePhoto() {
        final String currentPhotoName = getPhotoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR, currentPhotoName);

        // Android7.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());
            final Uri uri = mDelegate.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            // 将ContentProvider的uri路径转化为实际路径
            final File realFile = FileUtils.getFileByPath(FileUtil.getRealFilePath(mDelegate.getContext(), uri));
            final Uri realUri = Uri.fromFile(realFile);
            CameraImageBean.getInstance().setPath(realUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            final Uri fileUri = Uri.fromFile(tempFile);
            CameraImageBean.getInstance().setPath(fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        }
        mDelegate.startActivityForResult(intent, RequestCodes.TAKE_PHOTO);
    }

    private void pickPhoto() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        mDelegate.startActivityForResult(Intent.createChooser(intent, "选择获取图片的方式"), RequestCodes.PICK_PHOTO);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.photodialog_btn_take) {
            takePhoto();
        } else if (id == R.id.photodialog_btn_native) {
            pickPhoto();
        } else {
            mDialog.cancel();
        }
    }
}
