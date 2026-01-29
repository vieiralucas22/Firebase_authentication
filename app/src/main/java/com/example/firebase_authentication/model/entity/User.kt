package com.example.firebase_authentication.model.entity

data class User(
    val email: String,
    val isVerifiedEmail: Boolean,
    val uid: String,
)