package com.example.b07projectapplication.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07projectapplication.CustomerAccountActivity;
import com.example.b07projectapplication.CustomerHomePage;
import com.example.b07projectapplication.Person;
import com.example.b07projectapplication.R;
import com.example.b07projectapplication.StoreOwnerHomepage;
import com.example.b07projectapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginViewModel_AUTOGEN loginViewModelAUTOGEN;
    private ActivityLoginBinding binding;

    private EditText input_email;
    private EditText input_password;
    private Button btn_login;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference ref;

    LoginContract.Presenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        LoginContract.Model model = new LoginModel();
        presenter = new LoginPresenter(this, model);


        input_email = findViewById(R.id.username);
        input_password = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user != null) {
            String userUID = user.getUid();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });



        /**--------------------------------------------START OF AUTO-GENERATED CODE------------------------------------------------------------*/
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModelAUTOGEN = new ViewModelProvider(this, new LoginViewModelFactory_AUTOGEN())
                .get(LoginViewModel_AUTOGEN.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModelAUTOGEN.getLoginFormState().observe(this, new Observer<LoginFormState_AUTOGEN>() {
            @Override
            public void onChanged(@Nullable LoginFormState_AUTOGEN loginFormStateAUTOGEN) {
                if (loginFormStateAUTOGEN == null) {
                    return;
                }
                loginButton.setEnabled(loginFormStateAUTOGEN.isDataValid());
                if (loginFormStateAUTOGEN.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormStateAUTOGEN.getUsernameError()));
                }
                if (loginFormStateAUTOGEN.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormStateAUTOGEN.getPasswordError()));
                }
            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModelAUTOGEN.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    login();
                }
                return false;
            }
        });
        /**----------------------------------------------END OF AUTO-GENERATED CODE-----------------------------------------------------------------------*/

    }




    public void login(){
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser newUser = task.getResult().getUser();
                    String userUID = newUser.getUid();

                    ref = FirebaseDatabase.getInstance().getReference("users");
                    ref.child(userUID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()){
                                Person person = task.getResult().getValue(Person.class);
                                boolean isOwner = person.getOwnerCheck();

                                if ( isOwner ) {
                                    sendUserToOwner();
                                    Toast.makeText(LoginActivity.this, "Logged in Successfully AS OWNER", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    sendUserToCustomer();
                                    Toast.makeText(LoginActivity.this, "Logged in Successfully AS USER", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(LoginActivity.this, "Login Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void validateLogin(View view){
        presenter.checkInput();
    }

    @Override
    public String getEmail(){
        EditText input_email = findViewById(R.id.username);
        return input_email.getText().toString();
    }

    @Override
    public String getPassword(){
        EditText input_password = findViewById(R.id.password);
        return input_password.getText().toString();
    }

    @Override
    public void sendUserToCustomer(){
        Intent intent = new Intent(LoginActivity.this, CustomerHomePage.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void sendUserToOwner(){
        Intent intent = new Intent(LoginActivity.this, StoreOwnerHomepage.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void displayMessage(boolean isOwner){
        if ( isOwner ){
            Toast.makeText(LoginActivity.this, "Logged in Successfully AS OWNER", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(LoginActivity.this, "Logged in Successfully AS CUSTOMER", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayError(String error){
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }


    public void sendUserToCreateAccount(View view){
        Intent intent = new Intent (this, CustomerAccountActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}