package com.xtec.timeline.widget.pwdkeyboard;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.xtec.timeline.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 虚拟键盘
 */
public class VirtualKeyboardView extends RelativeLayout {

    Context context;
    private EditText mEditText;

    //因为就6个输入框不会变了，用数组内存申请固定空间，比List省空间（自己认为）
    private GridView gridView;    //用GridView布局键盘，其实并不是真正的键盘，只是模拟键盘的功能

    private ArrayList<Map<String, String>> valueList;    //有人可能有疑问，为何这里不用数组了？
    //因为要用Adapter中适配，用数组不能往adapter中填充

    private RelativeLayout layoutBack;

    public VirtualKeyboardView(Context context) {
        this(context,null);
    }

    public VirtualKeyboardView(Context context,AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        View view = View.inflate(context, R.layout.layout_virtual_keyboard, null);

        valueList = new ArrayList<>();

        layoutBack = (RelativeLayout) view.findViewById(R.id.layoutBack);

        gridView = (GridView) view.findViewById(R.id.gv_keyboard);

        initValueList();

        setupView();

        addView(view);      //必须要，不然不显示控件
    }

    public void init(Context context,EditText editText){
        mEditText = editText;

        // 设置不调用系统键盘
        if (Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            ((Activity)context).getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public RelativeLayout getLayoutBack() {
        return layoutBack;
    }

    public ArrayList<Map<String, String>> getValueList() {
        return valueList;
    }

    private void initValueList() {

        // 初始化按钮上应该显示的数字
        for (int i = 1; i < 13; i++) {
            Map<String, String> map = new HashMap<>();
            if (i < 10) {
                map.put("name", String.valueOf(i));
            } else if (i == 10) {
                map.put("name", ".");
            } else if (i == 11) {
                map.put("name", String.valueOf(0));
            } else if (i == 12) {
                map.put("name", "");
            }
            valueList.add(map);
        }
    }

    public GridView getGridView() {
        return gridView;
    }

    private void setupView() {

        KeyBoardAdapter keyBoardAdapter = new KeyBoardAdapter(context, valueList);
        gridView.setAdapter(keyBoardAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position < 11 && position != 9) {    //点击0~9按钮

                    String amount = mEditText.getText().toString().trim();
                    amount = amount + valueList.get(position).get("name");

                    mEditText.setText(amount);

                    Editable ea = mEditText.getText();
                    mEditText.setSelection(ea.length());
                } else {

                    if (position == 9) {      //点击退格键
                        String amount = mEditText.getText().toString().trim();
                        if (!amount.contains(".")) {
                            amount = amount + valueList.get(position).get("name");
                            mEditText.setText(amount);

                            Editable ea = mEditText.getText();
                            mEditText.setSelection(ea.length());
                        }
                    }

                    if (position == 11) {      //点击退格键
                        String amount = mEditText.getText().toString().trim();
                        if (amount.length() > 0) {
                            amount = amount.substring(0, amount.length() - 1);
                            mEditText.setText(amount);

                            Editable ea = mEditText.getText();
                            mEditText.setSelection(ea.length());
                        }
                    }
                }
            }
        });
    }
}
