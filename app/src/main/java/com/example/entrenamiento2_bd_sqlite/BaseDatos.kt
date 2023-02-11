package com.example.entrenamiento2_bd_sqlite

import android.content.ContentValues
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
    }

    // CRUD
    // INSERT
    fun insertarDatos(usuario: Usuario):String{
        val db = this.writableDatabase // prepara la base de datos editable
        var contenedordeValores = ContentValues();
        // put clave-valor:
        contenedordeValores.put("nombre", usuario.nombre)
        contenedordeValores.put("edad", usuario.edad)

        var resultado=db.insert("usuario", null, contenedordeValores)
        // si el resultado fue igual -1 hubo fallo
       if(resultado==-1.toLong()) {
           return "fallo al insertar";
       } else {
           return "Datos insertados (ok)"
       }
    }//insertarDatos

    // READ
    fun traerDatos():MutableList<Usuario>{
        var lista:MutableList<Usuario> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM Usuario";
        var resultado = db.rawQuery(sql, null)
        if(resultado.moveToFirst()){
            do {
                var usuario=Usuario()
                usuario.id = resultado.getString(resultado.getColumnIndexOrThrow("id")).toInt()
                usuario.nombre = resultado.getString(resultado.getColumnIndexOrThrow("nombre"))
                usuario.edad = resultado.getString(resultado.getColumnIndexOrThrow("edad")).toInt()
                lista.add(usuario)
            } while (resultado.moveToNext())
            resultado.close()
            db.close()
         return (lista)
        }//if
    return  lista
    }//traerDatos

    // UPDATE
    fun actualizaDatos(id:String, nombre:String, edad:Int):String {
        val db = this.writableDatabase
        var contenedor = ContentValues();
         contenedor.put("nombre", nombre)
         contenedor.put("edad", edad)
         var resultado = db.update("Usuario", contenedor, "id=", arrayOf(id))
        if (resultado > 0) {
            // Validar la actualización
            return "Actualización realizada"
        } else {
            //No se pudo actualizar
            return "No se pudo actualizar"
        }
    }//actualizaDatos

    // DELETE
    fun borrarDatos(id:String){
        val db = this.writableDatabase
        if(id.length>0){
            db.delete("Usuario","id=?", arrayOf(id))
            // para borrar todos
            // db.delete("Usuaro", null, null)
            db.close()
        }

    }//borrarDatos

}//class BaseDatos