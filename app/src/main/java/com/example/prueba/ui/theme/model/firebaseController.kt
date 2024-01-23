package com.example.prueba.ui.theme.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class firebaseController {
    val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://reloj-mundial-f29a5-default-rtdb.europe-west1.firebasedatabase.app/")
    var ref: DatabaseReference = database.getReference("citys")
    var usersRef = ref.child("citys")
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
        fun downloadImg(){

        }
    }
}