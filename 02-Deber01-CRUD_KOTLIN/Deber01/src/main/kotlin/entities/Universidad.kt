package entities;

import java.io.*;
import java.time.LocalDate;

data class Universidad(
        var id: Int,
        var nombre: String,
        var ubicacion: String,
        var fechaFundacion: LocalDate,
        var esPrivada: Boolean,
        var carreras: MutableList<Carrera>
) : Serializable {
    companion object {
        private const val FILE_NAME = "src/main/kotlin/files/universidades.txt";

        fun guardarUniversidades(universidades: MutableList<Universidad>) {
            try {
                ObjectOutputStream(FileOutputStream(FILE_NAME)).use { it.writeObject(universidades) }
            } catch (e: IOException) {
                println("Error al guardar las universidades: ${e.message}")
            }
        }

        fun cargarUniversidades(): MutableList<Universidad> {
            val file = File(FILE_NAME)
            return if (file.exists() && file.length() > 0) {
                try {
                    ObjectInputStream(FileInputStream(FILE_NAME)).use { it.readObject() as MutableList<Universidad> }
                } catch (e: IOException) {
                    println("Error al cargar las universidades: ${e.message}")
                    mutableListOf()
                }
            } else {
                mutableListOf()
            }
        }

        fun validarUniversidad(universidad: Universidad): Boolean {
            return if (universidad.nombre.isNotBlank() && universidad.ubicacion.isNotBlank()) {
                true
            } else {
                println("Nombre y ubicación no pueden estar vacíos.")
                false
            }
        }

        fun crearUniversidad(universidades: MutableList<Universidad>, universidad: Universidad) {
            if (validarUniversidad(universidad)) {
                universidades.add(universidad)
                guardarUniversidades(universidades)
            }
        }

        fun actualizarUniversidad(universidades: MutableList<Universidad>, id: Int, nuevaUniversidad: Universidad) {
            val universidad = universidades.find { it.id == id }
            if (universidad != null) {
                if (validarUniversidad(nuevaUniversidad)) {
                    universidad.nombre = nuevaUniversidad.nombre
                    universidad.ubicacion = nuevaUniversidad.ubicacion
                    universidad.fechaFundacion = nuevaUniversidad.fechaFundacion
                    universidad.esPrivada = nuevaUniversidad.esPrivada
                    universidad.carreras = nuevaUniversidad.carreras
                    guardarUniversidades(universidades)
                }
            }
        }

        fun eliminarUniversidad(universidades: MutableList<Universidad>, id: Int) {
            universidades.removeIf { it.id == id }
            guardarUniversidades(universidades)
        }

        fun leerUniversidades(universidades: MutableList<Universidad>) {
            universidades.forEach { universidad ->
                println("Universidad ID: ${universidad.id}, Nombre: ${universidad.nombre}, Ubicación: ${universidad.ubicacion}, Fecha de Fundación: ${universidad.fechaFundacion}, Es privada: ${universidad.esPrivada}")
                leerCarreras(universidad.carreras)
            }
        }

        fun leerCarreras(carreras: MutableList<Carrera>) {
            carreras.forEach { carrera ->
                println("\tCarrera ID: ${carrera.id}, Nombre: ${carrera.nombre}, Créditos: ${carrera.creditos}, Duración: ${carrera.duracion}, Costo: ${carrera.costo}")
            }
        }
    }
}
