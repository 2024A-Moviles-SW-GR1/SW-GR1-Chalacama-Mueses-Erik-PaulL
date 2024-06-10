package entities;

import java.io.*;

data class Carrera(
    var id: Int,
    var nombre: String,
    var creditos: Int,
    var duracion: Int, // Duración en años de la carrera
    var costo: Int
) : Serializable {
    companion object {
        private const val FILE_NAME = "src/main/kotlin/files/carreras.txt";

        // Guarda la lista de carreras en un archivo
        fun guardarCarreras(carreras: List<Carrera>) {
            try {
                ObjectOutputStream(FileOutputStream(FILE_NAME)).use { it.writeObject(carreras) }
            } catch (e: IOException) {
                println("Error al guardar las carreras: ${e.message}")
            }
        }

        // Carga la lista de carreras desde un archivo
        fun cargarCarreras(): MutableList<Carrera> {
            val file = File(FILE_NAME);
            return if (file.exists() && file.length() > 0) {
                try {
                    ObjectInputStream(FileInputStream(FILE_NAME)).use { it.readObject() as MutableList<Carrera> }
                } catch (e: IOException) {
                    println("Error al cargar las carreras: ${e.message}");
                    mutableListOf();
                }
            } else {
                mutableListOf();
            }
        }

        // Valida los datos de la carrera
        fun validarCarrera(carrera: Carrera): Boolean {
            return if (carrera.nombre.isNotBlank() && carrera.creditos > 0 && carrera.duracion > 0 && carrera.costo > 0) {
                true
            } else {
                println("Nombre no puede estar vacío y los valores numéricos deben ser positivos.");
                false
            }
        }

        // Crea una nueva carrera y la agrega a la lista de carreras
        fun crearCarrera(carreras: MutableList<Carrera>, carrera: Carrera) {
            if (validarCarrera(carrera)) {
                carreras.add(carrera);
                guardarCarreras(carreras);
            }
        }

        // Actualiza una carrera existente en la lista de carreras
        fun actualizarCarrera(carreras: MutableList<Carrera>, id: Int, nuevaCarrera: Carrera) {
            val carrera = carreras.find { it.id == id }
            if (carrera != null) {
                if (validarCarrera(nuevaCarrera)) {
                    carrera.nombre = nuevaCarrera.nombre;
                    carrera.creditos = nuevaCarrera.creditos;
                    carrera.duracion = nuevaCarrera.duracion;
                    carrera.costo = nuevaCarrera.costo;
                    guardarCarreras(carreras);
                }
            } else {
                println("Error: No se encontró una carrera con el ID proporcionado.");
            }
        }

        // Elimina una carrera de la lista de carreras
        fun eliminarCarrera(carreras: MutableList<Carrera>, id: Int) {
            if (carreras.removeIf { it.id == id }) {
                guardarCarreras(carreras);
            } else {
                println("Error: No se encontró una carrera con el ID proporcionado.");
            }
        }

        // Muestra todas las carreras en la lista de carreras
        fun leerCarreras(carreras: List<Carrera>) {
            if (carreras.isEmpty()) {
                println("No hay carreras para mostrar.");
            } else {
                carreras.forEach { carrera ->
                    println("Carrera ID: ${carrera.id}, Nombre: ${carrera.nombre}, Créditos: ${carrera.creditos}, Duración: ${carrera.duracion} años, Costo: ${carrera.costo}");
                }
            }
        }
    }
}