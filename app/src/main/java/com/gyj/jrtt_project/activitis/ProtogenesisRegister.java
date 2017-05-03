package com.gyj.jrtt_project.activitis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gyj.jrtt_project.R;

import java.util.HashMap;

import utils.Url_Login;
import utils.VolleyUtils;

public class ProtogenesisRegister extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_pr_fahui;
    private EditText ed_pr_id;
    private EditText ed_pr_pwd;
    private EditText ed_pr_pwd_too;
    private EditText ed_pr_email;
    private Button bt_pr_pr;
    private LinearLayout activity_protogenesis_register;
    private String id;
    private String pwd;
    private String too;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protogenesis_register);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {

        image_pr_fahui = (ImageView) findViewById(R.id.image_pr_fahui);
        image_pr_fahui.setOnClickListener(this);
        ed_pr_id = (EditText) findViewById(R.id.ed_pr_id);
        ed_pr_id.setOnClickListener(this);
        ed_pr_pwd = (EditText) findViewById(R.id.ed_pr_pwd);
        ed_pr_pwd.setOnClickListener(this);
        ed_pr_pwd_too = (EditText) findViewById(R.id.ed_pr_pwd_too);
        ed_pr_pwd_too.setOnClickListener(this);
        ed_pr_email = (EditText) findViewById(R.id.ed_pr_email);
        ed_pr_email.setOnClickListener(this);
        bt_pr_pr = (Button) findViewById(R.id.bt_pr_pr);
        bt_pr_pr.setOnClickListener(this);
        activity_protogenesis_register = (LinearLayout) findViewById(R.id.activity_protogenesis_register);
        activity_protogenesis_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_pr_pr:

                submit();
                break;
        }
    }

    private void submit() {
        // validate
        id = ed_pr_id.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "请输入帐号", Toast.LENGTH_SHORT).show();
            return;
        }

        pwd = ed_pr_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        too = ed_pr_pwd_too.getText().toString().trim();
        if (TextUtils.isEmpty(too)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        email = ed_pr_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("username",id);
        hashMap.put("password",pwd);
        hashMap.put("password_confirm",too);
        hashMap.put("email",email);
        hashMap.put("client","android");
        VolleyUtils.Send(this, Url_Login.LINK_MOBILE_REG,hashMap);


    }
}
