package com.qy.guowang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static com.qy.guowang.R.id.wendu;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.daoxian)
    RadioButton mDaoxian;
    @BindView(R.id.dixian)
    RadioButton mDixian;
    @BindView(R.id.guanglan)
    RadioButton mGuanglan;
    @BindView(R.id.group1)
    RadioGroup mGroup1;
    @BindView(R.id.dangduanfa)
    RadioButton mJiduanfa;
    @BindView(R.id.dangwaifa)
    RadioButton mDangwaifa;
    @BindView(R.id.group2)
    RadioGroup mGroup2;
    @BindView(R.id.du1)
    EditText mDu1;
    @BindView(R.id.fen1)
    EditText mFen1;
    @BindView(R.id.miao1)
    EditText mMiao1;
    @BindView(R.id.du2)
    EditText mDu2;
    @BindView(R.id.fen2)
    EditText mFen2;
    @BindView(R.id.miao2)
    EditText mMiao2;
    @BindView(wendu)
    EditText mWendu;
    @BindView(R.id.dangju)
    EditText mDangju;
    @BindView(R.id.juli)
    EditText mJuli;
    @BindView(R.id.jieguo)
    TextView mJieguo;
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.er)
    LinearLayout er;


    private StateGridCalculation mCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCalculation = new StateGridCalculation();


    }

    private void initView() {

    }

    @OnClick({R.id.dangduanfa, R.id.dangwaifa})
    public void group2(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();
        switch (radioButton.getId()) {
            case R.id.dangduanfa:
                if (checked) {
                    er.setVisibility(View.GONE);
                }
                break;
            case R.id.dangwaifa:
                if (checked) {
                    er.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @OnClick(R.id.button)
    public void jisuan() {
        check();
    }


    private void check() {
        if (er.getVisibility() == View.GONE) {
            if (TextUtils.isEmpty(mDu1.getText())) {
                Toasty.warning(this, "请输入度").show();
                return;
            }
            if (TextUtils.isEmpty(mFen1.getText())) {
                Toasty.warning(this, "请输入分").show();
                return;
            }
            if (TextUtils.isEmpty(mMiao1.getText())) {
                Toasty.warning(this, "请输入秒").show();
                return;
            }
            if (TextUtils.isEmpty(mWendu.getText())) {
                Toasty.warning(this, "请输入观测温度").show();
                return;
            } else {
                int wendu = Integer.valueOf(mWendu.getText().toString());
                if (wendu < -40 || wendu > 40) {
                    Toasty.warning(this, "请输入正确-40～40观测温度").show();
                    return;
                }
            }
            if (TextUtils.isEmpty(mDangju.getText())) {
                Toasty.warning(this, "请输入观测档距").show();
                return;
            } else {
                int dangju = Integer.valueOf(mDangju.getText().toString());
                if (dangju < 200 || dangju > 890) {
                    Toasty.warning(this, "请输入200～890观测档距").show();
                    return;
                }
            }
            if (TextUtils.isEmpty(mJuli.getText())) {
                Toasty.warning(this, "请输入垂直距离").show();
                return;
            }

            dangduan();
        } else {
            if (TextUtils.isEmpty(mDu1.getText())) {
                Toasty.warning(this, "请输入度").show();
                return;
            }
            if (TextUtils.isEmpty(mFen1.getText())) {
                Toasty.warning(this, "请输入分").show();
                return;
            }
            if (TextUtils.isEmpty(mMiao1.getText())) {
                Toasty.warning(this, "请输入秒").show();
                return;
            }
            if (TextUtils.isEmpty(mDu2.getText())) {
                Toasty.warning(this, "请输入度").show();
                return;
            }
            if (TextUtils.isEmpty(mFen2.getText())) {
                Toasty.warning(this, "请输入分").show();
                return;
            }
            if (TextUtils.isEmpty(mMiao2.getText())) {
                Toasty.warning(this, "请输入秒").show();
                return;
            }
            if (TextUtils.isEmpty(mWendu.getText())) {
                Toasty.warning(this, "请输入观测温度").show();
                return;
            } else {
                int wendu = Integer.valueOf(mWendu.getText().toString());
                if (wendu < -40 || wendu > 40) {
                    Toasty.warning(this, "请输入-40～40观测温度").show();
                    return;
                }
            }
            if (TextUtils.isEmpty(mDangju.getText())) {
                Toasty.warning(this, "请输入观测档距").show();
                return;
            } else {
                int dangju = Integer.valueOf(mDangju.getText().toString());
                if (dangju < 200 || dangju > 890) {
                    Toasty.warning(this, "请输入200～890观测档距").show();
                    return;
                }
            }
            if (TextUtils.isEmpty(mJuli.getText())) {
                Toasty.warning(this, "请输入垂直距离").show();
                return;
            }
            dangwai();

        }
    }


    private void dangduan() {
        int dangju = Integer.valueOf(mDangju.getText().toString());
        int wendu = Integer.valueOf(mWendu.getText().toString());
        double juli = Double.valueOf(mJuli.getText().toString());
        int du = Integer.valueOf(mDu1.getText().toString());
        int fen = Integer.valueOf(mFen1.getText().toString());
        int miao = Integer.valueOf(mMiao1.getText().toString());
        String jieguo = mCalculation.sideOfItemCalculationWithDegrees(dangju, wendu, juli, du, fen, miao);
        mJieguo.setText(jieguo);
    }

    private void dangwai() {
        int dangju = Integer.valueOf(mDangju.getText().toString());
        int wendu = Integer.valueOf(mWendu.getText().toString());
        double juli = Double.valueOf(mJuli.getText().toString());
        int du1 = Integer.valueOf(mDu1.getText().toString());
        int fen1 = Integer.valueOf(mFen1.getText().toString());
        int miao1 = Integer.valueOf(mMiao1.getText().toString());
        int du2 = Integer.valueOf(mDu2.getText().toString());
        int fen2 = Integer.valueOf(mFen2.getText().toString());
        int miao2 = Integer.valueOf(mMiao2.getText().toString());
        String jieguo = mCalculation.outSideOfItemCalculation(dangju, wendu, juli, du1, fen1, miao1, du2, fen2, miao2);
        mJieguo.setText(jieguo);
    }
}
