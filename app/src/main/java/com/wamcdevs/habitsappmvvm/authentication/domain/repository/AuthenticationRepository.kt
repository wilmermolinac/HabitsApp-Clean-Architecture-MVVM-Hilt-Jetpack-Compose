package com.wamcdevs.habitsappmvvm.authentication.domain.repository

import android.provider.ContactsContract.CommonDataKinds.Email

interface AuthenticationRepository {

    suspend fun login(email:String, password:String):Result<Unit>

    suspend fun forgotPassword(email: String):Result<Unit>
}