package com.sukaidev.latte.ec.main.personal.profile;

import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.main.personal.list.ListBean;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.ui.date.DateDialogUtil;

/**
 * Created by sukaidev on 2019/05/10.
 */
public class UserProfileClickListener extends SimpleClickListener {

    private LatteDelegate mDelegate;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(LatteDelegate delegate) {
        this.mDelegate = delegate;
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mDelegate.getContext());
        builder.setSingleChoiceItems(mGenders,0,listener);
        builder.show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                // 开启照相机或选择图片
                break;
            case 2:
                final LatteDelegate nameDelegate = bean.getDelegate();
                mDelegate.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AppCompatTextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final AppCompatTextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(mDelegate.getContext());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
