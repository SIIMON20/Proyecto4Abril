package com.example.proyecto25febrero.Screens

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto25febrero.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel() : ViewModel() {
    private val isLoading = MutableLiveData(false)
    private val enviar = MutableLiveData(false)

    fun isLoading() : LiveData<Boolean> = isLoading
    fun enviar(): LiveData<Boolean> = enviar

    fun LoginWithGoogle(activity: Activity){
        isLoading.postValue(true)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        //Validamos el cliente y se obtiene de google
        val client = GoogleSignIn.getClient(activity, gso)

        val signInIntent: Intent = client.getSignInIntent()
        activity.startActivityForResult(signInIntent, 1 )


//        //para retrasar
//        viewModelScope.launch{
//           delay(3000)
//            isLoading.postValue(false)
//        }
    }
    fun finishLogin(task: Task<GoogleSignInAccount>){
        try {
            val account:GoogleSignInAccount? = task.getResult(ApiException::class.java)
            //signed in succesfully
            account?.idToken?.let {
                token ->
                val auth = FirebaseAuth.getInstance()
                val credential = GoogleAuthProvider.getCredential(token, null)
                auth.signInWithCredential(credential).addOnCompleteListener {
                        task ->
                    if (task.isSuccessful){
                        var user = auth.currentUser
                        enviar.postValue(true)
                        Log.d("OK", "Ingreso ${user?.displayName}")
                    }else{
                        Log.d("Error", "NO se pudo conectar")
                    }
                    isLoading.postValue(false)
                }
            }
            //TODO
        } catch (e:ApiException){
            Log.d(TAG, "SignInResult:failed code=" + e.statusCode)
            isLoading.postValue(false)
        }//fin de la funcion finishLogin
    }
}//fin del viewModel