package com.wamcdevs.habitsappmvvm.authentication.data.repository

import android.provider.ContactsContract
import com.google.firebase.auth.FirebaseAuth
import com.wamcdevs.habitsappmvvm.authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthenticationRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try {

           firebaseAuth.signInWithEmailAndPassword(email, password).await()

            Result.success(Unit)

        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun forgotPassword(email: String): Result<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun signup(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }
}