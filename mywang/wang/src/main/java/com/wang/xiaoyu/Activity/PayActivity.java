package com.wang.xiaoyu.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Utils.L;
import com.wang.xiaoyu.Zfb.PayResult;
import com.wang.xiaoyu.domain.OrderInfoUtil2_0;

import java.util.Map;

public class PayActivity extends Activity implements View.OnClickListener{
    private RadioGroup mPaymentRadioGroup;
    private Button mPaymentButton;


    private static final int SDK_PAY_FLAG = 1001;
    public static final String RSA_PRIVATE = "";
    private String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbO3sDD0OCfIGEhTouVh7DCMoZc30Ursmol2efnMUnFJYHmPKAL4ZwkRK3SO4m8O0F2izTz84840poZ7H/vgwrmoOkB/M6Rs9UGkrMJhn6oZgd86FYzEOAVoXLT+6v/BbRLG6YCUlhbQ7FUldqAPEDOHtmrsxeYe3/7TjcxAalUruEeNIcKS9XuPxVRQlVAPiMFlYCQU2b2ST4qljQdtxjPhWngIbi7rWYGseNy1FUatFm0Y+B4rg8SIEMDxWiT3gggI6ny+jMiEksjsvcN7yYl2OaalsxTjLRgJCzPKwtSydkpj3HmduDn3jOJ4StWob+eQXWqdVZkshzv5f+pLmRAgMBAAECggEBAI4KBAoNqmk4gc5/lXgWpzkwzrQxH4+QS8dvGotLCH807/7trV8rnWlc5XI+UmPhahBDzWbWq9C87TXRRGdNP5eaX7/04lM9vbIoCK76YiUaPbWSWt2EsnM5OF2Lu0zebwZhEKUfQjzabHyTVpcRW3bl9MxFP41RMFRQfQpMeARG3PKMnKDLY5LDIJhgAggupwkIPtpw+Z0R1oIIQTjgxDKRRrKkipqCmOZcVD1eapyO8AqzKujfoRd/HWlrLEHVcqLqB4W7aTl2s2o+nCQuxik6CRaCsZk/KJFxmAeCOWBOZ/ihD6f1aLQb9DquwTnUzGWgCnx8E9penZRLiopYVcECgYEA1EvtZi/JcEMf4zOBtNJ4RtSvjkx3AIkBltSdJuXSBhYOyF+0KgGeHs0XugQAl6hXzwI+MRku+0dYhq7xzMn0ggrtn9gVQ5xsI+fYcPZkae5v5pPvUT8pGOfoYO2DgEyPNvUjQBKS0P1NDqNmflHdhPhz4RIp11Q7dPmCiW1okEkCgYEAuzBBjdbDoZkWk2chxZQFbONwfzPGG9FI/BkwGxxmOSC1SbzOg2qTWJxYqlg/Rys0ArKQvaESd7rbSVV94GzNkuI16dbIgctHyLHvWVpvGQhwWb+EiXUTntJH29scKWvBakgasYsnB8B8BOLdJkUgikI+19iibuilpmC8IiQobwkCgYAUizxv9RPjPzuouuVEYFGvWQmcFZ42eQukUvLaPscyO5Q2o7xFjF6G0IYJRDXidzH4IFlwWuWG/4J7lhZkIO7+XpxcKYb1Qf/dAaSaWd2huqctgwdXr2sEs/xy1Mky8zWwtcwxeRqoCrsYPYu/kaCEK57bGuzdn5MZYMPXFLVXsQKBgBxlh6xSxj6/kJMv9AmbGO+Z2TkSy5zwtNe5oniaaE+GTLow8EszYcLxhOnilUbgoPU/zYFW7AxJxnZrh7sCBnWf53nf4W7/PZaOl7bAZ1Yu14xl519K6X0QfWI27SDDfgX3hkUkzPj6FFZRbCcgGX2f6xSngoy8slR5HFE5lwCxAoGBAI6Cl1DzId6KH/9KK3Jn5xDlah9Nd1Ie9NwsLKVNUA4W2kJwg0u/22PDzdFYD/f/UyJsDL+mHF/N8PeSV5+9qVbri2SUDx9WOkPXs2FFaOk8lbCLXuXiJaxGQVITM37hdmtAmQjUVUM4tqyZJbAWdWVJh7/gMaASju1TTRphOjkb";
    public static final String APPID = " 2018092761542619";


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay1", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        L.e("跳转到Pay");

        initUI();
    }

    private void initUI() {
        mPaymentRadioGroup = (RadioGroup)findViewById(R.id.rg_payment_fragment);
        mPaymentButton = (Button)findViewById(R.id.bt_payment_confrim);

        mPaymentButton.setOnClickListener(this);

        mPaymentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_zfb:

                        break;
                    case R.id.rb_wx:

                        break;


                    default:
                        break;
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        		case R.id.bt_payment_confrim:

                    if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
                        new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        //
                                        finish();
                                    }
                                }).show();
                        return;
                    }

                    /**
                     * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
                     * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
                     * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
                     *
                     * orderInfo的获取必须来自服务端；
                     */
                    boolean rsa2 = (RSA2_PRIVATE.length() > 0);
                    Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
                    String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

                    String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
                    String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
                    final String orderInfo = orderParam + "&" + sign;

                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(PayActivity.this);
                            Map<String, String> result = alipay.payV2(orderInfo, true);
                            Log.i("msp", result.toString());

                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };

                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
        			break;

        		default:
        			break;
        		}
    }
}
