package com.example.prueba.ui.theme.model

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class firebaseController {
    val database = Firebase.database
    val ref = database.getReference("citys")
    var citysRef = ref.child("citys")
    val storage = FirebaseStorage.getInstance()
    var storageRef = storage.reference
    companion object {
        /*fun uploadCiudades() {
            val database = Firebase.database
            val ref = database.getReference("citys")

            val cities = listOf(
                "New York", "Los Angeles", "Chicago", "Houston", "London", "Paris",
                "Berlin", "Tokyo", "Beijing", "Sydney", "Moscow", "Istanbul",
                "Rio de Janeiro", "Cairo", "Mumbai", "Cape Town", "Toronto", "Dubai",
                "Mexico City", "Seoul", "Madrid", "Granada", "Sydney", "New Delhi", "Rome"
            )

            for (city in cities) {
                val cityRef = ref.child(city.replace(" ", "_")) // Reemplaza espacios con guiones bajos
                val lista = listOf("prueba1", "prueba2", "prueba3")
                cityRef.setValue(lista)
            }
        }*/
    }
}