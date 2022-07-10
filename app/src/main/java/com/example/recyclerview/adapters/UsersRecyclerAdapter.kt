package com.example.recyclerview.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.databinding.UsersListItemBinding
import com.example.recyclerview.model.User

class UsersRecyclerAdapter() :
    RecyclerView.Adapter<UsersRecyclerAdapter.UsersRecyclerViewHolder>() {

    //როგორც ვიცი კონსტრუქტორში ლისტის ჩაწოდება არაა კარგი პრაქტიკა და მაგიტომ ვქმნი აქ ლისტს
    //და შემდეგ ვამატებ setData ფუნქციას

    private val userList = mutableListOf<User>()

    inner class UsersRecyclerViewHolder(val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersRecyclerAdapter.UsersRecyclerViewHolder {
        return UsersRecyclerViewHolder(
            UsersListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: UsersRecyclerAdapter.UsersRecyclerViewHolder,
        position: Int
    ) {
        holder.binding.tvFirstName.text = userList[position].firstname
        holder.binding.tvLastName.text = userList[position].lastName
        holder.binding.tvEmail.text = userList[position].email
    }

    override fun getItemCount() = userList.size

    fun setData(data: MutableList<User>) {
        this.userList.clear()
        this.userList.addAll(data)
        notifyDataSetChanged()
    }

}