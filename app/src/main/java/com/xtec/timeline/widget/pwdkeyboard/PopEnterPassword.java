package com.xtec.timeline.widget.pwdkeyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.xtec.timeline.R;


/**
 * 输入支付密码
 *
 * @author lining
 */
public class PopEnterPassword extends PopupWindow {

    private PasswordView pwdView;

    private View mMenuView;

    private Activity mContext;

    public PopEnterPassword(final Activity context, final PopEnterPasswordListener listener) {

        super(context);

        this.mContext = context;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.pop_enter_password, null);

        pwdView = (PasswordView) mMenuView.findViewById(R.id.pwd_view);

        //添加密码输入完成的响应
//        pwdView.setOnFinishInput(new OnPasswordInputFinish() {
//            @Override
//            public void inputFinish(String password) {
//
//                dismiss();
//
//                Toast.makeText(mContext, "支付成功，密码为：" + password, Toast.LENGTH_SHORT).show();
//            }
//        });

        pwdView.setOnInputPwdFinishListener(new PasswordView.OnInputPwdFinishListener() {
            @Override
            public void onInputPwdFinish(String pwd) {
                dismiss();
                listener.onInputPwdFinish(pwd);

            }
        });

        pwdView.setOnForgetPwdClickListener(new PasswordView.OnForgetPwdClickListener() {
            @Override
            public void onForgetPwdClick() {
                listener.onForgetPwdClick();

            }
        });

//        // 监听X关闭按钮
//        pwdView.getImgCancel().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

        // 监听键盘上方的返回
        pwdView.getVirtualKeyboardView().getLayoutBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.pop_add_ainm);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x66000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    public interface PopEnterPasswordListener{
        void onInputPwdFinish(String pwd);
        void onForgetPwdClick();
    }
}
