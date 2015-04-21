package yo.login_setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 202 on 2015/4/21.
 */
public class user_info extends Activity{
        private TextView tv_result;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data);
        findViews();



    }
    private void findViews(){
        tv_result = (TextView)findViewById(R.id.tv_result);
        btn_back = (Button)findViewById(R.id.btn_back);
        Bundle bundle = this.getIntent().getExtras();
        String account = bundle.getString("account");
        String password = bundle.getString("password");
        StringBuilder sb = new StringBuilder();
        sb.append("account:").append(account).append("\n").append("password:").append(password);
        tv_result.setText(sb);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
}
