package com.example.recyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapters.UsersRecyclerAdapter
import com.example.recyclerview.databinding.ActivityUsersBinding
import com.example.recyclerview.model.User

class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var userAdapter: UsersRecyclerAdapter
    private val usersList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val user1 = User("ბიძინა", "თაბაგარი", "hotdog1@gmail.com")
        val user2 = User("გურამ", "ჯინორია", "hotdog2@gmail.com")
        val user3 = User("უჩა", "ზერაგია", "hotdog3@gmail.com")
        val user4 = User("ჰასან", "ცეცხლაძე", "hotdog4@gmail.com")

        //ვინაიდან თავდაპირველ ფეიჯზე უნდა შეგვეყვანა იუზერი და დამატებაზე დაჭერისაას ინტენტი მომხდარიყო
        //რისაიქლერში მექნებოდა ის ერთი აითემი და ხელით მაგიტომ ვამატებ ამ 4 იუზერს რომ სქროლვადი იყოს
        //და დიალოგზე გამოსული ინფუთებით მოვახდენ იუზერის ამოშლას და რედაქტირებას

        val user: User = intent.getParcelableExtra("userList")!!
        usersList.add(user)
        usersList.add(user1)
        usersList.add(user2)
        usersList.add(user3)
        usersList.add(user4)

        setRecycler(usersList)

        listeners()

    }

    private fun setRecycler(data: MutableList<User>) {
        userAdapter = UsersRecyclerAdapter()
        binding.recyclerUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
        userAdapter.setData(data)
    }

    private fun listeners() {

        binding.btnDialog.setOnClickListener {

            val dialog = LayoutInflater.from(this).inflate(R.layout.changes_dialog, null)
            val dialogBuilder = AlertDialog.Builder(this)
                .setView(dialog)
            val alertDialog = dialogBuilder.show()

            dialog.findViewById<AppCompatButton>(R.id.dialog_btnEditUser).setOnClickListener {

                //იუზერის ინფოს რედაქტირებისთვის მთავარია მეილი შეიყვანოს სწორად და სახელს და გვარს
                //როდესაც შეიყვანს განსხვავებულს თუ დააჭერს შეცვლის ღილაკს მეილი დარჩება და სახელი გვარი შეიცვლება

                val email =
                    dialog.findViewById<AppCompatEditText>(R.id.dialog_etEmail).text.toString()
                val firstName =
                    dialog.findViewById<AppCompatEditText>(R.id.dialog_etFirstName).text.toString()
                val lastName =
                    dialog.findViewById<AppCompatEditText>(R.id.dialog_etLastName).text.toString()

                val user = User(firstName, lastName, email)

                if (isAccountExist(user)) {
                    usersList.forEach {
                        if (it.email == email) {
                            usersList[usersList.indexOf(it)] = user
                            setRecycler(usersList)
                            alertDialog.dismiss()
                        }
                    }
                } else {
                    Toast.makeText(this, "მსგავსი მომხმარებელი არ არსებობს", Toast.LENGTH_SHORT)
                        .show()
                }

            }

            dialog.findViewById<AppCompatButton>(R.id.dialog_btnDeleteUser).setOnClickListener {

                //უზერის ამოსაშლელად საჭიროა
                //იუზერის ყველა პარამეტრი იყოს სწორად შეყვანილი

                val email =
                    dialog.findViewById<AppCompatEditText>(R.id.dialog_etEmail).text.toString()
                val firstName =
                    dialog.findViewById<AppCompatEditText>(R.id.dialog_etFirstName).text.toString()
                val lastName =
                    dialog.findViewById<AppCompatEditText>(R.id.dialog_etLastName).text.toString()

                val user = User(firstName, lastName, email)
//                d("delete","$usersList")
                if (isAccountExist(user)) {
                    usersList.remove(user)
                    setRecycler(usersList)
                    alertDialog.dismiss()
//                    d("deleted","$usersList")
                } else {
                    Toast.makeText(this, "მსგავსი მომხმარებელი არ არსებობს", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

    }

    private fun isAccountExist(userInfo: User): Boolean {
        var isExist = false
        for (user in usersList) {
            if (user.email == userInfo.email) {
                isExist = true
                break
            }
        }
        return isExist
    }
}
