package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.musicapp.databinding.ActivitySignupBinding;

import java.util.Locale;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);


        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if(email.equals("")||password.equals("")||confirmPassword.equals(""))
                    Toast.makeText(SignupActivity.this,"Không được để trống",Toast.LENGTH_LONG).show();
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkMail(email);

                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email,password);

                            if(insert == true){
                                Toast.makeText(SignupActivity.this,"Đăng kí thành công",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(SignupActivity.this,"Đăng kí thất bại",Toast.LENGTH_LONG).show();

                            }
                        }else {
                            Toast.makeText(SignupActivity.this,"Người dùng đã tồn tại , làm ơn đăng nhập",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(SignupActivity.this,"Mật khẩu không hợp lệ",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        binding.loginRedirectTexxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}