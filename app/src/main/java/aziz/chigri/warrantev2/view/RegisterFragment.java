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
import java.util.Objects;

import aziz.chigri.warrantev2.R;
import aziz.chigri.warrantev2.requests.AccountDataService;
import aziz.chigri.warrantev2.requests.RetrofitClientInstance;
import aziz.chigri.warrantev2.utils.Constants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aziz.chigri.warrantev2.utils.Constants.PREFERENCE_USERNAME;

public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final ViewPager superViewPager = requireActivity().findViewById(R.id.viewPager);

        final ProgressBar pgsBar = (ProgressBar)view.findViewById(R.id.pBar);
        pgsBar.setVisibility(View.GONE);
        final TextInputEditText emailText = view.findViewById(R.id.email);
        final TextInputEditText passwordText = view.findViewById(R.id.password);
        final TextInputEditText confirmPasswordText = view.findViewById(R.id.confirmPassword);
        final Button registerButton = view.findViewById(R.id.registerButton);
        final AccountDataService service = RetrofitClientInstance.getInstance().create(AccountDataService.class);
        final TextView connectSentence = view.findViewById(R.id.connectSentence);

        connectSentence.setOnClickListener(v -> superViewPager.setCurrentItem(0));

        registerButton.setOnClickListener(v -> {
            hideComponents(view);
            JsonObject body = new JsonObject();
            final String email = emailText.getText().toString();
            body.addProperty("email", email);
            body.addProperty("password", passwordText.getText().toString());
            Call<ResponseBody> call = service.createAccount(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        Toast.makeText(v.getContext(), "OK", Toast.LENGTH_SHORT).show();
                        view.findViewById(R.id.pBar).setVisibility(View.GONE);
                        SharedPreferences mPreferences = requireContext().getSharedPreferences(Constants.PREFERENCE_FILE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPreferences.edit();
                        editor.putString(PREFERENCE_USERNAME, email);
                        editor.apply();
                        superViewPager.setCurrentItem(0);
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
        v.findViewById(R.id.confirmPasswordLayout).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.registerButton).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.connectSentence).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.pBar).setVisibility(View.VISIBLE);
    }

    private void showComponents(View v) {
        v.findViewById(R.id.emailLayout).setVisibility(View.VISIBLE);
        v.findViewById(R.id.passwordLayout).setVisibility(View.VISIBLE);
        v.findViewById(R.id.confirmPasswordLayout).setVisibility(View.VISIBLE);
        v.findViewById(R.id.registerButton).setVisibility(View.VISIBLE);
        v.findViewById(R.id.connectSentence).setVisibility(View.VISIBLE);
        v.findViewById(R.id.pBar).setVisibility(View.INVISIBLE);
    }
}