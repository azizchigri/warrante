package aziz.chigri.warrantev2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.io.IOException;

import aziz.chigri.warrantev2.R;
import aziz.chigri.warrantev2.requests.AccountDataService;
import aziz.chigri.warrantev2.requests.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ProgressBar pgsBar = (ProgressBar)findViewById(R.id.pBar);
        pgsBar.setVisibility(View.GONE);
        final TextInputEditText emailText = findViewById(R.id.email);
        final TextInputEditText passwordText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.loginButton);
        final AccountDataService service = RetrofitClientInstance.getInstance().create(AccountDataService.class);

        loginButton.setOnClickListener(v -> {
            hideComponents();
            final Context context = v.getContext();
            JsonObject body = new JsonObject();
            body.addProperty("email", emailText.getText().toString());
            body.addProperty("password", passwordText.getText().toString());
            Call<ResponseBody> call = service.login(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        Toast.makeText(LoginActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(context, HomeActivity.class);
                        try {
                            myIntent.putExtra("rid", response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        context.startActivity(myIntent);
                        findViewById(R.id.pBar).setVisibility(View.GONE);
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "KO", Toast.LENGTH_SHORT).show();
                        showComponents();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Toast.makeText(LoginActivity.this, "KO", Toast.LENGTH_SHORT).show();
                    showComponents();
                }
            });

        });
    }

    private void hideComponents() {
        findViewById(R.id.emailLayout).setVisibility(View.GONE);
        findViewById(R.id.passwordLayout).setVisibility(View.GONE);
        findViewById(R.id.loginButton).setVisibility(View.GONE);
        findViewById(R.id.forgotPassword).setVisibility(View.GONE);
        findViewById(R.id.registerSentence).setVisibility(View.GONE);
        findViewById(R.id.pBar).setVisibility(View.VISIBLE);
    }

    private void showComponents() {
        findViewById(R.id.emailLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.passwordLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.loginButton).setVisibility(View.VISIBLE);
        findViewById(R.id.forgotPassword).setVisibility(View.VISIBLE);
        findViewById(R.id.registerSentence).setVisibility(View.VISIBLE);
        findViewById(R.id.pBar).setVisibility(View.GONE);
    }

    public void forgotPassword(View view) {
    }
}