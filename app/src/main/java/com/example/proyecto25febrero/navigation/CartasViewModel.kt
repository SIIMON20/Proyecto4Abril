package com.example.proyecto25febrero.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class CartasViewModel : ViewModel() {
    private val _cartas = mutableStateOf<List<Cartas>>(emptyList())
    //para consultar creamos un metodo get de la clase
    
    val cartas : State<List<Cartas>>
    get()=_cartas
    //creamos una variable para el query a la coleccion de la base de datos

    private val query = Firebase.firestore.collection("cartas")
    //creamos el metodo constructor del viemodel

    init{
        query.addSnapshotListener{ value, _ ->
            if(value != null){
                _cartas.value = value.toObjects()
            }
        }
    }
}