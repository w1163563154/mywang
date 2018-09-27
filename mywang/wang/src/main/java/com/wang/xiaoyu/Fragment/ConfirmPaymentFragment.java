package com.wang.xiaoyu.Fragment;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.wang.xiaoyu.R;

/**
 * Created by 小 on 2018/9/23.
 */

public class ConfirmPaymentFragment extends BaseFragment {

    private RadioGroup mPaymentRadioGroup;
    private Button mPaymentButton;


    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbO3sDD0OCfIGEhTouVh7DCMoZc30Ursmol2efnMUnFJYHmPKAL4ZwkRK3SO4m8O0F2izTz84840poZ7H/vgwrmoOkB/M6Rs9UGkrMJhn6oZgd86FYzEOAVoXLT+6v/BbRLG6YCUlhbQ7FUldqAPEDOHtmrsxeYe3/7TjcxAalUruEeNIcKS9XuPxVRQlVAPiMFlYCQU2b2ST4qljQdtxjPhWngIbi7rWYGseNy1FUatFm0Y+B4rg8SIEMDxWiT3gggI6ny+jMiEksjsvcN7yYl2OaalsxTjLRgJCzPKwtSydkpj3HmduDn3jOJ4StWob+eQXWqdVZkshzv5f+pLmRAgMBAAECggEBAI4KBAoNqmk4gc5/lXgWpzkwzrQxH4+QS8dvGotLCH807/7trV8rnWlc5XI+UmPhahBDzWbWq9C87TXRRGdNP5eaX7/04lM9vbIoCK76YiUaPbWSWt2EsnM5OF2Lu0zebwZhEKUfQjzabHyTVpcRW3bl9MxFP41RMFRQfQpMeARG3PKMnKDLY5LDIJhgAggupwkIPtpw+Z0R1oIIQTjgxDKRRrKkipqCmOZcVD1eapyO8AqzKujfoRd/HWlrLEHVcqLqB4W7aTl2s2o+nCQuxik6CRaCsZk/KJFxmAeCOWBOZ/ihD6f1aLQb9DquwTnUzGWgCnx8E9penZRLiopYVcECgYEA1EvtZi/JcEMf4zOBtNJ4RtSvjkx3AIkBltSdJuXSBhYOyF+0KgGeHs0XugQAl6hXzwI+MRku+0dYhq7xzMn0ggrtn9gVQ5xsI+fYcPZkae5v5pPvUT8pGOfoYO2DgEyPNvUjQBKS0P1NDqNmflHdhPhz4RIp11Q7dPmCiW1okEkCgYEAuzBBjdbDoZkWk2chxZQFbONwfzPGG9FI/BkwGxxmOSC1SbzOg2qTWJxYqlg/Rys0ArKQvaESd7rbSVV94GzNkuI16dbIgctHyLHvWVpvGQhwWb+EiXUTntJH29scKWvBakgasYsnB8B8BOLdJkUgikI+19iibuilpmC8IiQobwkCgYAUizxv9RPjPzuouuVEYFGvWQmcFZ42eQukUvLaPscyO5Q2o7xFjF6G0IYJRDXidzH4IFlwWuWG/4J7lhZkIO7+XpxcKYb1Qf/dAaSaWd2huqctgwdXr2sEs/xy1Mky8zWwtcwxeRqoCrsYPYu/kaCEK57bGuzdn5MZYMPXFLVXsQKBgBxlh6xSxj6/kJMv9AmbGO+Z2TkSy5zwtNe5oniaaE+GTLow8EszYcLxhOnilUbgoPU/zYFW7AxJxnZrh7sCBnWf53nf4W7/PZaOl7bAZ1Yu14xl519K6X0QfWI27SDDfgX3hkUkzPj6FFZRbCcgGX2f6xSngoy8slR5HFE5lwCxAoGBAI6Cl1DzId6KH/9KK3Jn5xDlah9Nd1Ie9NwsLKVNUA4W2kJwg0u/22PDzdFYD/f/UyJsDL+mHF/N8PeSV5+9qVbri2SUDx9WOkPXs2FFaOk8lbCLXuXiJaxGQVITM37hdmtAmQjUVUM4tqyZJbAWdWVJh7/gMaASju1TTRphOjkb";
    public static final String APPID = " 2018092761542619";


    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_payment,null);

        mPaymentRadioGroup = (RadioGroup) view.findViewById(R.id.rg_payment_fragment);
        mPaymentButton = (Button) view.findViewById(R.id.bt_payment_confrim);

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

        mPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return view;
    }

    @Override
    public void initData() {

    }
}
