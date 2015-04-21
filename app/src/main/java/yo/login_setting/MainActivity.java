package yo.login_setting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText et_account, et_password;
    private Button btn_login, btn_cancel, btn_clear;
    private CheckBox ckb_remeber;
    private final static String PREF_NAME = "login_info";
    private final static boolean DEFAULT_logininfo = false;
    private final static String DEFAULT_account = "";
    private final static String DEFAULT_password = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        loadPref();
    }

    private void findViews(){
        et_account = (EditText)findViewById(R.id.et_account);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_login = (Button)findViewById(R.id.btn_login);
        ckb_remeber = (CheckBox)findViewById(R.id.ckb_remeber);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if記住儲存傳送到下一個layout
                if(ckb_remeber.isChecked()){
                savePref();
                trans();
                }
                else if(!ckb_remeber.isChecked()){
                    trans();
                    et_account.setText("");
                    et_password.setText("");
                    savePref();


                }

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_account.setText("");
                et_password.setText("");
                savePref();
            }
        });



    }

    private void trans(){

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(MainActivity.this, user_info.class);
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        bundle.putString("password", password);
        bundle.putString("account", account);
        intent.putExtras(bundle);
        startActivity(intent);



    }

    private void loadPref() {
//        讀取設定
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
//        沒有設定則讀取預設值，預設取消
        boolean ck_login = preferences.getBoolean("logininfo", DEFAULT_logininfo);
        if (ck_login)
            ckb_remeber.setChecked(true);

        String account = preferences.getString("account", DEFAULT_account);
        String password = preferences.getString("password", DEFAULT_password);
        et_account.setText(account);
        et_password.setText(password);




    }

    private void savePref() {
//        取得設定(PREF_NAME, MODE_PRIVATE)，設定名稱、設定開放權限
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean ck_login = ckb_remeber.isChecked();
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();

//          編輯設定，將當前設定儲存
        preferences.edit()
//                (K,V)
                .putString("password", password)
                .putString("account", account)
                .putBoolean("logininfo", ck_login)
//                最後apply
                .apply();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
