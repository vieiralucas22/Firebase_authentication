package com.example.firebase_authentication.services.implementation

import com.example.firebase_authentication.model.entity.User
import com.example.firebase_authentication.services.interfaces.IAuthenticationService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationServiceImpl @Inject constructor(
    private val auth : FirebaseAuth
) : IAuthenticationService {

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User> {

        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()

            val firebaseUser =
                result.user ?: return Result.failure(IllegalStateException("User is null"))
            val firebaseUserEmail =
                firebaseUser.email ?: return Result.failure(IllegalStateException("Email is null"))

            val user = User(firebaseUserEmail, firebaseUser.isEmailVerified, firebaseUser.uid)

            return Result.success(user)

        } catch (e: Exception) {
            return Result.failure(Exception(e.message))
        }
    }

    override suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<Unit> {

        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()

            if (result.user == null || result.user?.email == null)
                return Result.failure(IllegalStateException("Failed to create user!"))

            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(Exception(e.message))
        }
    }

}