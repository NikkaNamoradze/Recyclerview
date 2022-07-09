package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.example.recyclerview.databinding.ActivityUserBinding
import com.example.recyclerview.model.User
import kotlinx.parcelize.Parcelize
import java.io.Serializable

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        listeners()
    }

    private fun listeners() {
        binding.btnAdd.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()
            val user = User(firstName, lastName, email)

            if (!email.contains("@")) {
                Toast.makeText(this, "ელ ფოსტა უნდა შეიცავდეს @ სიმბოლოს", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this, UsersActivity::class.java)
                intent.putExtra("userList", user)
                startActivity(intent)
            }
        }
    }
}