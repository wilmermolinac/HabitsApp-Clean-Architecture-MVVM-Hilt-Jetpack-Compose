package com.wamcdevs.habitsappmvvm.authentication.domain.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {

    suspend fun login(email:String, password:String):Result<Unit>

    suspend fun forgotPassword(email: String):Result<Unit>

    suspend fun signup(email: String, password: String):Result<Unit>

    fun getUser():FirebaseUser?
}