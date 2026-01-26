package com.example.firebase_authentication.services

import com.google.firebase.auth.FirebaseAuth

class AuthenticationService {

    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

    private var _currentAuthenticationState : AuthenticationState = AuthenticationState.Unauthenticated

    fun getAuthenticationState() : AuthenticationState
    {
        return _currentAuthenticationState
    }

    fun signInWithEmailAndPassword(email : String, password : String)
    {
        _currentAuthenticationState = AuthenticationState.Loading

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful)
                    _currentAuthenticationState = AuthenticationState.Authenticated
                else
                {
                    _currentAuthenticationState = AuthenticationState.Unauthenticated
                }
            }
    }

    fun createUserWithEmailAndPassword(email : String, password : String)
    {
        _currentAuthenticationState = AuthenticationState.Loading

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful)
                    _currentAuthenticationState = AuthenticationState.Authenticated
                else
                {
                    _currentAuthenticationState = AuthenticationState.Unauthenticated
                }
            }
    }

    sealed class AuthenticationState {
        object Authenticated : AuthenticationState()
        object Unauthenticated : AuthenticationState()
        object Loading : AuthenticationState()
    }
}