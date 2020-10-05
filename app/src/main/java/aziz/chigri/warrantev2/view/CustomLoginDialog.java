package aziz.chigri.warrantev2.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import aziz.chigri.warrantev2.R;

public class CustomLoginDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.custom_login, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //builder.setTitle("Login");
        builder.setView(view);

        final EditText emailText = view.findViewById(R.id.email);
        final EditText passwordText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast.makeText(getActivity(), "Email is " + emailText.getText().toString() +
                        "and password is"+passwordText.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(context, HomeActivity.class);
                myIntent.putExtra("key", "value"); //Optional parameters
                context.startActivity(myIntent);
            }
        });

        Dialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }
}