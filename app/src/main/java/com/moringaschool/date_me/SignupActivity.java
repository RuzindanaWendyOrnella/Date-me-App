
package com.moringaschool.date_me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = null;
    @BindView(R.id.createUserButton)
    Button newUserButton;
    @BindView(R.id.nameofEditText)
    EditText newUserName;
    @BindView(R.id.emailEditText)
    EditText newUserEmail;
    @BindView(R.id.passwordEditText)
    EditText newUserPassword;
    @BindView(R.id.confirmPasswordEditText)
    EditText passConfirmation;
    @BindView(R.id.loginTextView)
    TextView backToLogin;
    private String mName;
    private String Name;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;
    DatabaseReference reff;
    Member member;
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        member=new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        ButterKnife.bind(this);

        backToLogin.setOnClickListener(this);
        newUserButton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();
    }


   public void createAuthProgressDialog(){
       progressDialog = new ProgressDialog(this);
       progressDialog.setTitle("Loading...");
       progressDialog.setMessage("Authenticating with Firebase...");
       progressDialog.setCancelable(true);
    }
    @Override
    public void onClick(View v) {
        if(v==backToLogin){
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        if (v == newUserButton){
            createNewUser();
//            Log.d("hello", createNewUser());
             newUserName.getText().toString();
            String Email = newUserEmail.getText().toString();
            member.setName(newUserName.getText().toString().trim());
            member.setEmail(newUserEmail.getText().toString().trim());
            reff.push().setValue(member);
            Toast.makeText(SignupActivity.this,"succeess",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    //the below codes are for checking if the user entered valid credentials
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
    //I am going to set the username
    private void createFirebaseUserProfile(final FirebaseUser user){
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName).build();
        System.out.println(mName);
        user.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, user.getDisplayName());
                    System.out.println(user.getDisplayName());
                }
            }
        });

    }

    //this is creating a new user
    public void createNewUser(){
        mName= newUserName.getText().toString();
        final String name = newUserName.getText().toString();
        final String email = newUserEmail.getText().toString();
        String password = newUserPassword.getText().toString();
        String confirm = passConfirmation.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(mName);
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


    //this AuthListener is in charge of listening to the happening event so that it opens and close the activity depending on the current situation
    @Override
    public void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    private void createAuthStateListener(){
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final  FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!= null){
                    Intent intent=new Intent(SignupActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
}*/
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_signup);
       ButterKnife.bind(this);
       createAuthProgressDialog();
       firebaseAuth = FirebaseAuth.getInstance();
       createAuthStateListener();
       backToLogin.setOnClickListener(this);
       newUserButton.setOnClickListener(this);
   }
   @Override
    public void onClick(View account) {
        if (account == backToLogin) {
            Intent login = new Intent(SignupActivity.this, LoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
            finish();
        }
        if (account == newUserButton) {
            createNewUser();
         /*   String Name = newUserName.getText().toString();
            member.setName(newUserName.getText().toString().trim());
           System.out.println();
            reff.push().setValue(member);
            Toast.makeText(SignupActivity.this,"succeess",Toast.LENGTH_LONG).show();*/

        }
    }    private void createNewUser() {
        mName = newUserName.getText().toString().trim();
        final String name = newUserName.getText().toString().trim();
        final String email = newUserEmail.getText().toString().trim();
        String password = newUserPassword.getText().toString().trim();
        String confirm = passConfirmation.getText().toString().trim();
        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(mName);
        boolean validPassword = isValidPassword(password, confirm);
        if (!validEmail || !validName || !validPassword) return;
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Log.d(TAG, "Authentication successful");
                    createFirebaseUserProfile(task.getResult().getUser());
                }
                else {
                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
   }
        private void createAuthStateListener() {
            authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            final FirebaseUser owner = firebaseAuth.getCurrentUser();
            if (owner != null) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }        };
    }    private void createFirebaseUserProfile(final FirebaseUser user) {
       UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
            .setDisplayName(mName)
            .build();
       user.updateProfile(addProfileName)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, user.getDisplayName());
                }
            }
            });
    }
    private void createAuthProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("coming soon...");
        progressDialog.setMessage("on Authentication with Firebase...");
        progressDialog.setCancelable(false);
    }    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            newUserEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }    private boolean isValidName(String name) {
        if (name.equals("")) {
            newUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            newUserPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            newUserPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}

