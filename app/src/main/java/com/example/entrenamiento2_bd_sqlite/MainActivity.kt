package com.example.entrenamiento2_bd_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.entrenamiento2_bd_sqlite.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //lateinit var nombre:EditText
    //lateinit var edad:EditText
    //lateinit var codigo:EditText
    //lateinit var mensaje:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // nombre = findViewById(R.id.Nombre)
        // edad = findViewById(R.id.Edad)
        // codigo = findViewById(R.id.Codigo)
        // mensaje = findViewById(R.id.Lista)

        // campos de escritura
        binding.Nombre.showSoftInputOnFocus = false
        binding.Edad.showSoftInputOnFocus = false
        binding.Codigo.showSoftInputOnFocus = false

        // Botón insertar
        binding.btnInsert.setOnClickListener {
            // Acción para el botón insertar
            if(binding.Nombre.text.toString().length>0 && binding.Edad.text.toString().length>0) {
                var usuario = Usuario(binding.Nombre.text.toString(), binding.Edad.text.toString().toInt());
                var bd =BaseDatos(this);
                binding.Lista.setText(bd.insertarDatos(usuario));
            }
        }
        // Botón borrar
        binding.btnDelete.setOnClickListener {
            // Acción para el botón borrar
            var bd = BaseDatos(this);
            if(binding.Codigo.text.toString().length>0){
                bd.borrarDatos(binding.Codigo.text.toString());
            }
        }
        // Botón leer
        binding.btnRead.setOnClickListener {
            // Acción para el botón leer
            var bd=BaseDatos(this);
            var datos = bd.traerDatos()
            binding.Lista.text=""
            for(i in 0..datos.size-1){
                binding.Lista.append("Código " + datos.get(i).id.toString() + ", nombre "+ datos.get(i).nombre + ", edad "+ datos.get(i).edad.toString())
            }
        }
        // Botón actualizar
        binding.btnUpdate.setOnClickListener {
            // Acción para el botón actualizar
            var bd=BaseDatos(this);
            if(binding.Codigo.text.toString().length>0 && binding.Nombre.text.toString().length>0 && binding.Edad.text.toString().length>0){
                bd.actualizaDatos(binding.Codigo.text.toString(),binding.Nombre.text.toString(),binding.Edad.text.toString().toInt())
            }
        }
    }
}