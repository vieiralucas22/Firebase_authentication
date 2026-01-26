package com.example.firebase_authentication.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.firebase_authentication.enums.ErrorCode

class LoginViewModel(application: Application) : BaseViewModel(application) {

    var mEmail by mutableStateOf("")
    var mPassword by mutableStateOf("")

    var mIsMissingEmail by mutableStateOf(false)
    var mIsMissingPassword by mutableStateOf(false)

    fun login() {
        if (!canLogin()) return
    }

    fun showIsMissingTextAlert(error: ErrorCode) {
        when (error)
        {
            ErrorCode.EmailIsMissing -> mIsMissingEmail = true
            ErrorCode.PasswordIsMissing -> mIsMissingPassword = true
            else -> {}
        }
    }

    fun canLogin() : Boolean
    {
        if (mEmail.isEmpty())
            showIsMissingTextAlert(ErrorCode.EmailIsMissing)

        if (mPassword.isEmpty())
            showIsMissingTextAlert(ErrorCode.PasswordIsMissing)

        return mEmail.isNotEmpty() && mPassword.isNotEmpty()
    }

}