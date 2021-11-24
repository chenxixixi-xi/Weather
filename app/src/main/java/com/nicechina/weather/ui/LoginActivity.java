package com.nicechina.weather.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nicechina.weather.R;
import com.nicechina.weather.db.Constants;
import com.nicechina.weather.db.DateBaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase sqLiteDatabase;
    DateBaseHelper helper;
    TextView tvRegister;
    TextView tvLogin;
    EditText account, psw;
    String mAccount;
    String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置状态栏和背景融合
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        helper = new DateBaseHelper(this);
        sqLiteDatabase = helper.getWritableDatabase();
        tvLogin = findViewById(R.id.tvLogin);
        tvRegister = findViewById(R.id.tvRegister);
        account = findViewById(R.id.et_input);
        psw = findViewById(R.id.et_input2);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //注册按钮功能
            case R.id.tvRegister:
                //注册相关的弹窗设定，如果确定提交，则完成注册，数据存入数据库
                //AlerDialog：对话框控件
                new AlertDialog.Builder(LoginActivity.this).setTitle("tip")
                        .setMessage("Are you sure to submit？")
                        //为确定按钮配置监听
                        .setPositiveButton("sure", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // 获取EditView中的内容，并将其转化成字符串型后存入变量中
                                mAccount = account.getText().toString();
                                mPassword = psw.getText().toString();

                                //给数据表设置游标，Cursor是一个游标，以name字段为依据，query是一种根据条件获取数据的方法
                                Cursor cursor = sqLiteDatabase.query(Constants.TABLE_NAME, new String[]{"name"}, "name=?", new String[]{mAccount}, null, null, null);
                                //如果游标找到了所需要的name，则返回已注册，否则就利用之前写的insert方法插入数据
                                if (cursor.getCount() != 0) {
                                    Toast.makeText(LoginActivity.this, "This user is already registered !", Toast.LENGTH_SHORT).show();
                                } else {
                                    helper.insert(sqLiteDatabase, mAccount, mPassword);
                                    Toast.makeText(LoginActivity.this, "Registered successfully, please log in !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("back", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        //这个是点击返回后的操作，因为不需要，所以不管他直接跳出就好。

                    }
                }).show();
                break;

            //登录按钮功能，上面解释过的已省略
            case R.id.tvLogin:
                String user_str = account.getText().toString();
                String psw_str = psw.getText().toString();
                //账号或密码为空时
                if (user_str.equals("")) {
                    Toast.makeText(this, "Account or password cannot be empty ", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = sqLiteDatabase.query(Constants.TABLE_NAME, new String[]{"password"}, "name=?", new String[]{user_str}, null, null, null);
                    //游标的遍历，寻找name对应的password的值
                    if (cursor.moveToNext()) {
                        String psw_query = cursor.getString(cursor.getColumnIndex("password"));
                        //用户名对应的密码与输入的密码相同时
                        if (psw_str.equals(psw_query)) {
                            Toast.makeText(this, "login successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "wrong password !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "The account does not exist, please register first ！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;
        }
    }
}