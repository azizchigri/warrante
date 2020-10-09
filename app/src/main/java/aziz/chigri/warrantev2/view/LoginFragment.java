package aziz.chigri.warrantev2.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.io.IOException;

import aziz.chigri.warrantev2.R;
import aziz.chigri.warrantev2.requests.AccountDataService;
import aziz.chigri.warrantev2.requests.RetrofitClientInstance;
import aziz.chigri.warrantev2.utils.Constants;
import aziz.chigri.warrantev2.view.HomeActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {


    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        final ViewPager superViewPager = requireActivity().findViewById(R.id.viewPager);

        final ProgressBar pgsBar = (ProgressBar)view.findViewById(R.id.pBar);
        pgsBar.setVisibility(View.GONE);
        final TextInputEditText emailText = view.findViewById(R.id.email);
        final TextInputEditText passwordText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.loginButton);
        final AccountDataService service = RetrofitClientInstance.getInstance().create(AccountDataService.class);
        final TextView registerTxt = view.findViewById(R.id.registerSentence);
        final TextView forgotPasswordTxt = view.findViewById(R.id.forgotPassword);

        SharedPreferences mPreferences = requireContext().getSharedPreferences(Constants.PREFERENCE_FILE, Context.MODE_PRIVATE);
        emailText.setText(mPreferences.getString(Constants.PREFERENCE_USERNAME, ""));

        registerTxt.setOnClickListener(v -> superViewPager.setCurrentItem(1));
        forgotPasswordTxt.setOnClickListener(v -> forgotPassword());

        loginButton.setOnClickListener(v -> {
            hideComponents(view);
            final Context context = v.getContext();
            JsonObject body = new JsonObject();
            body.addProperty("email", emailText.getText().toString());
            body.addProperty("password", passwordText.getText().toString());
            Call<ResponseBody> call = service.login(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        Toast.makeText(v.getContext(), "OK", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(context, HomeActivity.class);
                        try {
                            myIntent.putExtra("rid", response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        context.startActivity(myIntent);
                        view.findViewById(R.id.pBar).setVisibility(View.GONE);
                        requireActivity().finish();
                    } else {
                        Toast.makeText(v.getContext(), "KO", Toast.LENGTH_SHORT).show();
                        showComponents(view);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Toast.makeText(v.getContext(), "KO", Toast.LENGTH_SHORT).show();
                    showComponents(view);
                }
            });

        });
        return view;
    }

    private void hideComponents(View v) {
        v.findViewById(R.id.emailLayout).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.passwordLayout).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.loginButton).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.forgotPassword).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.registerSentence).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.pBar).setVisibility(View.VISIBLE);
    }

    private void showComponents(View v) {
        v.findViewById(R.id.emailLayout).setVisibility(View.VISIBLE);
        v.findViewById(R.id.passwordLayout).setVisibility(View.VISIBLE);
        v.findViewById(R.id.loginButton).setVisibility(View.VISIBLE);
        v.findViewById(R.id.forgotPassword).setVisibility(View.VISIBLE);
        v.findViewById(R.id.registerSentence).setVisibility(View.VISIBLE);
        v.findViewById(R.id.pBar).setVisibility(View.INVISIBLE);
    }

    private void forgotPassword() {
        Toast.makeText(getContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
    }
}