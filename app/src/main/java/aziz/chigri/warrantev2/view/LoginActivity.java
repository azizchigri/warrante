package aziz.chigri.warrantev2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import aziz.chigri.warrantev2.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText emailText = findViewById(R.id.email);
        final EditText passwordText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast.makeText(LoginActivity.this, "Email is " + emailText.getText().toString() +
                        "and password is"+passwordText.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(context, HomeActivity.class);
                myIntent.putExtra("key", "value"); //Optional parameters
                context.startActivity(myIntent);
            }
        });
    }
}