package com.example.entrenamiento2_bd_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.example.entrenamiento2_bd_sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding
    lateinit var nombre:EditText
    lateinit var edad:EditText
    lateinit var codigo:EditText
    lateinit var mensaje:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nombre = findViewById(R.id.txtViewNombre)
        edad = findViewById(R.id.textViewEdad)
        codigo = findViewById(R.id.textViewID)
        mensaje = findViewById(R.id.Lista)
    }
    // Botón insertar
    fun Insert(view:View) {
        if(nombre.text.toString().length>0 && edad.text.toString().length>0) {
            var usuario = Usuario(nombre.text.toString(), edad.text.toString().toInt());
            var bd =BaseDatos(this);
            mensaje.setText(bd.insertarDatos(usuario));
        }
    }

    // Botón borrar
    fun Delete(view: View) {
        var bd = BaseDatos(this);
        if(codigo.text.toString().length>0){
        bd.borrarDatos(codigo.text.toString());
        }
    }

    // Botón leer
    fun Read(view: View) {
        var bd=BaseDatos(this);
        var datos = bd.traerDatos()
        mensaje.text="";
        for(i in 0..datos.size-1){
                mensaje.append("Código" + datos.get(i).id.toString() + " Nombre "+ datos.get(i).nombre + "edad"+ datos.get(i).edad.toString())
            }
    }

    // Botón actualizar
    fun Update(view: View) {
        var bd=BaseDatos(this);
        if(codigo.text.toString().length>0 && edad.text.toString().length>0 && codigo.text.toString().length>0){
            bd.actualizaDatos(codigo.text.toString(),nombre.text.toString(),edad.text.toString().toInt())
        }
    }




}