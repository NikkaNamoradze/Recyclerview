package com.example.recyclerview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val firstname: String, val lastName: String, val email: String) : Parcelable
