package com.example.parkingnext.data

import com.example.parkingnext.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseDAO {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun register(email: String, password: String): User? {
        return suspendCancellableCoroutine { continuation ->
            val auth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = User(email, password, listOf())
                        if (continuation.isActive) {
                            continuation.resume(user)
                        }
                    } else {
                        if (continuation.isActive) {
                            continuation.resume(null)
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    if (continuation.isActive) {
                        continuation.resumeWithException(exception)
                    }
                }

            continuation.invokeOnCancellation {
                // Clean up resources if needed
            }
        }
    }

    suspend fun login(email: String, password: String): User? {
        return suspendCancellableCoroutine { continuation ->
            val auth = FirebaseAuth.getInstance()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = User(email, password, listOf())
                        if (continuation.isActive) {
                            continuation.resume(user)
                        }
                    } else {
                        if (continuation.isActive) {
                            continuation.resume(null)
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    if (continuation.isActive) {
                        continuation.resumeWithException(exception)
                    }
                }

            continuation.invokeOnCancellation {
                // Clean up resources if needed
            }
        }
    }

    fun getCurrentUser(): User? {
        val currentUser = firebaseAuth.currentUser
        var result: User? = null
        if (currentUser != null)
            result =  User(currentUser.email.toString(), "", listOf())

        return result
    }
}