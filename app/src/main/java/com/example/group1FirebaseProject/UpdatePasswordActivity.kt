package com.example.homework3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.group1FirebaseProject.LoginActivity
import com.example.group1FirebaseProject.ProfileActivity
import com.example.group1FirebaseProject.R
import com.google.firebase.auth.FirebaseAuth

class UpdatePasswordActivity : AppCompatActivity() {

    private lateinit var updatePasswordET : EditText
    private lateinit var saveUpdateBtn : Button
    private lateinit var goBackBtn : Button

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)

        init()
        listeners()
    }

    private fun init(){
        updatePasswordET = findViewById(R.id.updatePasswordET)
        saveUpdateBtn = findViewById(R.id.saveUpdateBtn)
        goBackBtn = findViewById(R.id.goBackBtn)
    }

    private fun listeners(){

        saveUpdateBtn.setOnClickListener {

            val user = FirebaseAuth.getInstance().currentUser
            val password = updatePasswordET.text.toString()

            if (password.isEmpty() || password.length < 7)
                return@setOnClickListener

            user!!.updatePassword(password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "You have successfully updated the password!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java ))
                    finish()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        goBackBtn.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))

        }
    }

}