package com.example.parkingnext.data

import com.example.parkingnext.model.Car
import com.example.parkingnext.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseDAO {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    @OptIn(ExperimentalCoroutinesApi::class)
    public suspend fun register(email: String, password: String): User? {
        return suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // User registration successful
                        val user = User(email, password, listOf<Car>())
                        continuation.resume(user)
                    } else {
                        // User registration failed
                        continuation.resume(null)
                    }
                }
                .addOnFailureListener { exception ->
                    // User registration failed with an exception
                    continuation.resumeWithException(exception)
                }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    public suspend fun login(email: String, password: String): User? {
        return suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // User registration successful
                        val user = User(email, password, listOf<Car>())
                        continuation.resume(user)
                    } else {
                        // User registration failed
                        continuation.resume(null)
                    }
                }
                .addOnFailureListener { exception ->
                    // User registration failed with an exception
                    continuation.resumeWithException(exception)
                }
        }
    }

    public fun getCurrentUser(): User? {
        val currentUser = firebaseAuth.currentUser
        var result: User? = null
        if (currentUser != null)
            result =  User(currentUser.email.toString(), "", listOf<Car>())

        return result
    }
}