package com.moringaschool.date_me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.date_me.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.createUserButton)
    Button newUserButton;
    @BindView(R.id.nameEditText)
    EditText newUserName;
    @BindView(R.id.emailEditText)
    EditText newUserEmail;
    @BindView(R.id.passwordEditText)
    EditText newUserPassword;
    @BindView(R.id.confirmPasswordEditText)
    EditText passConfirmation;
    @BindView(R.id.loginTextView)
    TextView backToLogin;
    CircleImageView profile_image;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;
    private String name;
    TextView username;
    DatabaseReference reff;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
