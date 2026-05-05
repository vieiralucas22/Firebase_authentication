package com.example.firebase_authentication.di

import com.example.firebase_authentication.services.implementation.AuthenticationServiceImpl
import com.example.firebase_authentication.services.interfaces.IAuthenticationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticatorModule {

    @Binds
    fun bindAuthenticatorService(impl: AuthenticationServiceImpl) : IAuthenticationService

}