package com.example.recyclerview.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.User

class UsersRecyclerAdapter() :
    RecyclerView.Adapter<UsersRecyclerAdapter.UsersRecyclerViewHolder>() {

    //როგორც ვიცი კონსტრუქტორში ლისტის ჩაწოდება არაა კარგი პრაქტიკა და მაგიტომ ვქმნი აქ ლისტს
    //და შემდეგ ვამატებ setData ფუნქციას

    private val userList = mutableListOf<User>()

    inner class UsersRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstName: TextView = itemView.findViewById(R.id.tvFirstName)
        var lastName: TextView = itemView.findViewById(R.id.tvLastName)
        var email: TextView = itemView.findViewById(R.id.tvEmail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersRecyclerAdapter.UsersRecyclerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent, false)
        return UsersRecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: UsersRecyclerAdapter.UsersRecyclerViewHolder,
        position: Int
    ) {
        holder.firstName.text = userList[position].firstname
        holder.lastName.text = userList[position].lastName
        holder.email.text = userList[position].email
    }

    override fun getItemCount() = userList.size

    fun setData(data: MutableList<User>) {
        this.userList.clear()
        this.userList.addAll(data)
        notifyDataSetChanged()
    }

}