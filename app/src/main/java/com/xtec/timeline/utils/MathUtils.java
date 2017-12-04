package com.xtec.timeline.utils;

import android.util.Log;

/**
 * author : 武昌丶鱼
 * e-mail : reyzarc@163.com
 * time   : 2017-07-20
 * desc
 */
public class MathUtils {

    private static final String TAG = "MathUtils";


    /**
     * @param totalMoney
     * @param apr        年化利率
     */
    public static float calculateInterest(int totalMoney, float apr,int period) {

        //每月待还金额
        float repaymentPerMonth = totalMoney / period;
        //总利息
        float totalInterest = 0;
        //投资的金额
        float bidAmount = totalMoney;

        for (int i = 0; i < period; i++) {
            //本月的利息收益
            float currentInterest = bidAmount * apr / period;
            //总利息
            totalInterest += currentInterest;
            //下月剩余可投金额
            bidAmount = bidAmount - repaymentPerMonth;
        }

        float amout = totalMoney + totalInterest;
        Log.e(TAG, "年化利率为"+apr*100+"%时总利息：" + totalInterest + "---->总资产：" + amout);

        return totalMoney + totalInterest;

    }

    public static void calculateInterest2(float bidPerMonth, float apr) {

        //总利息
        float totalInterest = 0;
        //总资金
        float totalAmount;

        for (int i = 0; i < 12; i++) {
            //当前月的投资总额
            totalAmount = bidPerMonth+ bidPerMonth*i + totalInterest;

            float currentInterest = totalAmount * apr / 12;

            totalInterest += currentInterest;
        }

        totalAmount = bidPerMonth*12+totalInterest;

        Log.e(TAG,"年化利率为"+apr*100+"%时滚动投总利息："+totalInterest+"---->滚动投总资产："+totalAmount);
    }

}
