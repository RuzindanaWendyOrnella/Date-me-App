package com.moringaschool.date_me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.createUserButton) Button newUserButton;
    @BindView(R.id.nameEditText) EditText newUserName;
    @BindView(R.id.emailEditText) EditText newUserEmail;
    @BindView(R.id.passwordEditText) EditText newUserPassword;
    @BindView(R.id.confirmPasswordEditText) EditText passConfirmation;
    @BindView(R.id.loginTextView) TextView backToLogin;
    @BindView(R.id.profile) ImageView profile;
    @BindView(R.id.choose) Button choose;
    @BindView(R.id.upload) Button upload;


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;
    private String name;

    AlertDialog dialog;

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);


        backToLogin.setOnClickListener(this);
        newUserButton.setOnClickListener(this);
        choose.setOnClickListener(this);
        upload.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();
        dialog = new SpotsDialog.Builder().setContext(this).build();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void createAuthProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Authenticating with Firebase...");
        progressDialog.setCancelable(true);
    }

    @Override
    public void onClick(View view){
        if(view==backToLogin){
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        if (view == newUserButton){
            createNewUser();
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void createNewUser(){
        final String userName = newUserName.getText().toString();
        final String email = newUserEmail.getText().toString();
        String password = newUserPassword.getText().toString();
        String confirm = passConfirmation.getText().toString().trim();
        name = newUserName.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(userName);
        boolean validPassword = isValidPassword(password, confirm);
        if (!validEmail || !validName || !validPassword) return;
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    createFirebaseUserProfile(task.getResult().getUser());
                }
                else {
                    Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    private void createAuthStateListener(){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

    }
    private boolean isValidEmail(String email){
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail){
            newUserEmail.setError("Please enter a valid address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name){
        if (name.equals("")){
            newUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }
    private boolean isValidPassword(String password, String confirmPassword){
        if (password.length()<6){
            newUserPassword.setError("Please create a password containing at least 6 characters");
            return false;
        }
        else if (!password.equals(confirmPassword)){
            newUserPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void createFirebaseUserProfile(final FirebaseUser user){
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build();
        user.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "The displayed name has been set", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
