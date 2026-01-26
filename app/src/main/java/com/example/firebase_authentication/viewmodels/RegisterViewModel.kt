package com.example.firebase_authentication.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.firebase_authentication.enums.ErrorCode
import com.example.firebase_authentication.services.AuthenticationService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterViewModel(application: Application) : BaseViewModel(application) {

    var mEmail by mutableStateOf("")
    var mPassword by mutableStateOf("")
    var mFullName by mutableStateOf("")

    var mIsMissingEmail by mutableStateOf(false)
    var mIsMissingPassword by mutableStateOf(false)
    var mIsMissingFullName by mutableStateOf(false)

    val mAuthenticationService : AuthenticationService = AuthenticationService()

    fun createAccount() {
        if (!canCreateAccount()) return

        viewModelScope.launch {
            mAuthenticationService.createUserWithEmailAndPassword(mEmail, mPassword)
        }
    }

    fun showIsMissingTextAlert(error: ErrorCode) {
        when (error)
        {
            ErrorCode.EmailIsMissing -> mIsMissingEmail = true
            ErrorCode.PasswordIsMissing -> mIsMissingPassword = true
            ErrorCode.FullNameIsMissing -> mIsMissingFullName = true
        }
    }

    fun canCreateAccount() : Boolean
    {
        if (mEmail.isEmpty())
            showIsMissingTextAlert(ErrorCode.EmailIsMissing)

        if (mPassword.isEmpty())
            showIsMissingTextAlert(ErrorCode.PasswordIsMissing)

        if (mFullName.isEmpty())
            showIsMissingTextAlert(ErrorCode.FullNameIsMissing)

        return mEmail.isNotEmpty() && mPassword.isNotEmpty() && mFullName.isNotEmpty()
    }
}