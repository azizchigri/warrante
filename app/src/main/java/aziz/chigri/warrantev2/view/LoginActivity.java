package aziz.chigri.warrantev2.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import aziz.chigri.warrantev2.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CustomLoginDialog customLoginDialog = new CustomLoginDialog();
        customLoginDialog.show(getSupportFragmentManager(), "login_dialog");
    }
}