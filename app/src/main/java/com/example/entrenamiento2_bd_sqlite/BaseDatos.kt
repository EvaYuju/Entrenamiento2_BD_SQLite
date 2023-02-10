package com.example.entrenamiento2_bd_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

var BD="baseDatos";
// var tabla="Usuario";

class BaseDatos(contexto: Context):SQLiteOpenHelper(contexto, BD,null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
         // Sentencia sql para crear tabla Usuario
         val crearTablasql="CREATE TABLE " + "Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nombre VARCHAR(25), edad  INTEGER)";
        // ?para los nulos // Ejecute sentencia
        db?.execSQL(crearTablasql);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }



}