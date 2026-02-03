package com.example.firebase_authentication.services.interfaces

import com.example.firebase_authentication.model.entity.User

interface IAuthenticationService {

    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User>

    suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<Unit>

}