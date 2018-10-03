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
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.wang.xiaoyu.R;
import com.wang.xiaoyu.Zfb.PayResult;

import java.util.Map;

public class PayActivity extends Activity implements View.OnClickListener{
    private RadioGroup mPaymentRadioGroup;
    private Button mPaymentButton;
    private IWXAPI api;

    private static final int SDK_PAY_FLAG = 1001;
    public static final String RSA_PRIVATE = "";
    //private String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbO3sDD0OCfIGEhTouVh7DCMoZc30Ursmol2efnMUnFJYHmPKAL4ZwkRK3SO4m8O0F2izTz84840poZ7H/vgwrmoOkB/M6Rs9UGkrMJhn6oZgd86FYzEOAVoXLT+6v/BbRLG6YCUlhbQ7FUldqAPEDOHtmrsxeYe3/7TjcxAalUruEeNIcKS9XuPxVRQlVAPiMFlYCQU2b2ST4qljQdtxjPhWngIbi7rWYGseNy1FUatFm0Y+B4rg8SIEMDxWiT3gggI6ny+jMiEksjsvcN7yYl2OaalsxTjLRgJCzPKwtSydkpj3HmduDn3jOJ4StWob+eQXWqdVZkshzv5f+pLmRAgMBAAECggEBAI4KBAoNqmk4gc5/lXgWpzkwzrQxH4+QS8dvGotLCH807/7trV8rnWlc5XI+UmPhahBDzWbWq9C87TXRRGdNP5eaX7/04lM9vbIoCK76YiUaPbWSWt2EsnM5OF2Lu0zebwZhEKUfQjzabHyTVpcRW3bl9MxFP41RMFRQfQpMeARG3PKMnKDLY5LDIJhgAggupwkIPtpw+Z0R1oIIQTjgxDKRRrKkipqCmOZcVD1eapyO8AqzKujfoRd/HWlrLEHVcqLqB4W7aTl2s2o+nCQuxik6CRaCsZk/KJFxmAeCOWBOZ/ihD6f1aLQb9DquwTnUzGWgCnx8E9penZRLiopYVcECgYEA1EvtZi/JcEMf4zOBtNJ4RtSvjkx3AIkBltSdJuXSBhYOyF+0KgGeHs0XugQAl6hXzwI+MRku+0dYhq7xzMn0ggrtn9gVQ5xsI+fYcPZkae5v5pPvUT8pGOfoYO2DgEyPNvUjQBKS0P1NDqNmflHdhPhz4RIp11Q7dPmCiW1okEkCgYEAuzBBjdbDoZkWk2chxZQFbONwfzPGG9FI/BkwGxxmOSC1SbzOg2qTWJxYqlg/Rys0ArKQvaESd7rbSVV94GzNkuI16dbIgctHyLHvWVpvGQhwWb+EiXUTntJH29scKWvBakgasYsnB8B8BOLdJkUgikI+19iibuilpmC8IiQobwkCgYAUizxv9RPjPzuouuVEYFGvWQmcFZ42eQukUvLaPscyO5Q2o7xFjF6G0IYJRDXidzH4IFlwWuWG/4J7lhZkIO7+XpxcKYb1Qf/dAaSaWd2huqctgwdXr2sEs/xy1Mky8zWwtcwxeRqoCrsYPYu/kaCEK57bGuzdn5MZYMPXFLVXsQKBgBxlh6xSxj6/kJMv9AmbGO+Z2TkSy5zwtNe5oniaaE+GTLow8EszYcLxhOnilUbgoPU/zYFW7AxJxnZrh7sCBnWf53nf4W7/PZaOl7bAZ1Yu14xl519K6X0QfWI27SDDfgX3hkUkzPj6FFZRbCcgGX2f6xSngoy8slR5HFE5lwCxAoGBAI6Cl1DzId6KH/9KK3Jn5xDlah9Nd1Ie9NwsLKVNUA4W2kJwg0u/22PDzdFYD/f/UyJsDL+mHF/N8PeSV5+9qVbri2SUDx9WOkPXs2FFaOk8lbCLXuXiJaxGQVITM37hdmtAmQjUVUM4tqyZJbAWdWVJh7/gMaASju1TTRphOjkb";
    private String RSA2_PRIVATE ="MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDgB5Mx1OBD+24Vm2lvtxDYQe2aNWSzBwobDTVymATp+N5SHTGArwql+H7/1YUGlu3o+3nUUlfqbxy2569netZfrLjoaW6dWVOBM/H6EBLBPBJGrQJ4NQSUIjFJkEeNhFtDzSPW4HwKHdABz9MkZsubelCJ1MhRtqLf/AOqSg7sYD083DCsSMR7LEcT+qfGET42BhfPrgW66rQvrlvMf0gVrrETgcod5ncnw1VX4q1ggJsN0ChJmOc9HuCW/LOkCctuifsUsCLDe6LqPuA9zqHft6/SafPJljRYpANRlhpW5kqVHpssIpGQ/xx7b74T1Jgx6G5yYw3bJI/ZtyvWNtpZAgMBAAECggEBANo8RtWKTBWtfqWpyWI8cTpZrv5D+R61QtDrYx+Im3DZMKnJKony+mqH0h+ifn3W1Vfacem1dVM4fj5nH+WsMdztwwsUed7qP4Bje9tfgQWgYSVcMEWtUBKF5IGG/U0MnGNGGR2ffPXV6OV68HJLeRiyZHaAQ8vmIHAVbfSbREJaA2zkx79qkpbvvSSS5z+vtkV54+LWuudWlsptVOtWslf4kavvBxDl/IaowUGYD+tAXc5+6sqIxQPDJ46cvP88CYnqtpiiz6ChlnpaRGMOvNJL8NSTr3pXnmJY3rhFAsuDA+FSN00CsW0K4tq1tCiI4IcCZojrrYNHW3yG2HpIDgECgYEA8tArXFOWeFaaDzNHP1WJ+Vgk0MjkoJAzWEyaAxQydd8tGMOwClk9MnHg4ltCxDL3B3Psdm8BYnIXuGczqdoqYmhPs8Wo88GR80qq0D6RTFg2vq4ZeT4Ib2cSz5Egtfp4XkvlF8os0jY/dEVperbk/JcnsvLCoRWjAr+f5/N4dTkCgYEA7DJB9hGl1asYDWdUOMBgr5jy/NtCwoRdFFzFe4PgTngQfkDiN76kYvuOMYksot79h9UnIMky3TF0xjnK9u0VX1Qda9dsVAmAscPPD6m0NDwnlLIP72S7ysITsi/3iHbOqQXLNuSWIjk/q2WB6K6lQpGz6ym1rwLevPj5ze8briECgYEAiQ0EnG01riC7fj7kRRRD93BP/2SU3yflQz6hY4F/5dYdPzAc6gpqp1FtrHX0XoehtRwyaLrJHkblqpjdqU5RxA+6hO7ZKRSmC5umhDn8iwnzhHcCO9POsQqNrIju3KhOTnMqEN51pAUolbkkCza2UvgCWVf6kS+EPeXpHOPopskCgYEAoYVo6C5/WpPLOfdMeBbOx6ZvnI0QpVbBFFiwz9uKO/8NQFl8maRsZCgkwClPdf3KIOaaAGi2uN7GPXxuGNhegjdSe1nxAbMR5/7AjbR+wC3W32+5R/t6FJkNwQ/eVdrxTlLg+iPtq2+846fh483yw4hCInH+hbUPvukAY1y9cCECgYEAoWIcGhklqaj4Abw0FaV+awnil0ZWqmJYg9P7nixUfV2wTS8EHYkghNv9bUq/3p3aqDTthfgAGecUns7n0IUB4nxbepKwbcXyoVvDfffMswEhYyjUl/ANIAd/xIvzvql2L/GeVrTUdsW9OSeVu6ze7nYMAiohA7vgsfxrL0luBMo=";
    //public static final String APPID = " 2018092761542619";
    public static final String APPID = " 2018012202032332";


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
    private RadioButton mZfbRadioButton;
    private RadioButton mWxRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题,
        setContentView(R.layout.activity_pay);

        //L.e("跳转到Pay");

        initUI();
    }

    private void initUI() {
        mPaymentRadioGroup = (RadioGroup)findViewById(R.id.rg_payment_fragment);
        mPaymentButton = (Button)findViewById(R.id.bt_payment_confrim);

        mZfbRadioButton = (RadioButton) findViewById(R.id.rb_zfb);
        mWxRadioButton = (RadioButton) findViewById(R.id.rb_wx);
        mPaymentButton.setOnClickListener(this);

        mPaymentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_zfb:
                        mZfbRadioButton.setChecked(true);
                        break;
                    case R.id.rb_wx:
                        mWxRadioButton.setChecked(true);
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

        		    //选择支付宝支付
        		    if(mZfbRadioButton.isChecked()){
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
                       // Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
                        //String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                        //String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
                        //String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
                        final String orderInfo ="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018012202032332&biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%2211%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95Java%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.zglcfn.com%3A8764%2Fali%2Fnotify&sign=rWVsGKuv8vz25Sk%2FCQ03DpHhyJTn%2FcXFOfobhQ%2F%2BKGPYZ8MOcOHnW7HwTQiq%2F5CSGsa6JNzRfpSfanN9fuA2HCBRtnX1gHHQ79dEALzMyxb5IdB%2FM52is5dnsrkudxHSsLLmOaiFN7%2B2ur9JEL3i7NXDH68pcZc6r%2FcCsXLz8Bp5ogNKnsFejC6tXI2Hto4pJj38dSWn8s%2Bg%2B2M5M9076AKgmPVvyGL9b1sc9Ndh2lOxF9iKqtF87pGCUeyT6fxSoNls4LJzblTccQUwdbNWTq00RqYkD%2BbyJ%2F3dFZnKj9UxpTsi8XGhRE4O70Y0puNtecnRMAiCgSm48uHuCOnuQA%3D%3D&sign_type=RSA2&timestamp=2018-10-02+16%3A06%3A54&version=1.0";

                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(PayActivity.this);
                                Map<String, String> result = alipay.payV2(orderInfo, true);
                                //Log.i("msp", result.toString());

                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };

                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }else {
                        //调取微信支付
                        //Toast.makeText(PayActivity.this, "没有微信支付", Toast.LENGTH_SHORT).show();
                        //IWXAPI api;
                        PayReq request = new PayReq();
                        request.appId = "wxd930ea5d5a258f4f";
                        request.partnerId = "1900000109";
                        request.prepayId= "1101000000140415649af9fc314aa427";
                        request.packageValue = "Sign=WXPay";
                        request.nonceStr= "1101000000140429eb40476f8896f4c9";
                        request.timeStamp= "1398746574";
                        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
                        api.sendReq(request);

                    }
        			break;

        		default:
        			break;
        		}
    }
}
