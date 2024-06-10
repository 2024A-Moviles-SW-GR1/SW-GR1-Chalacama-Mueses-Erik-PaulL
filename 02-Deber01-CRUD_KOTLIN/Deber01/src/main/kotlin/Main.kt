import entities.Universidad
import entities.Carrera
import java.time.LocalDate
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val universidades = Universidad.cargarUniversidades()

    while (true) {
        mostrarMenu()
        val opcion = getIntInput(scanner, "Por favor, seleccione una opción del menú: ")
        when (opcion) {
            1 -> crearUniversidad(scanner, universidades)
            2 -> crearCarrera(scanner, universidades)
            3 -> Universidad.leerUniversidades(universidades)
            4 -> leerCarrerasDeUniversidad(scanner, universidades)
            5 -> leerTodasLasCarreras(universidades)
            6 -> actualizarUniversidad(scanner, universidades)
            7 -> actualizarCarrera(scanner, universidades)
            8 -> eliminarUniversidad(scanner, universidades)
            9 -> eliminarCarrera(scanner, universidades)
            10 -> {
                println("El programa ha finalizado exitosamente. ¡Gracias por usar nuestra aplicación!")
                break
            }
            else -> println("Lo siento, la opción que ingresaste no es válida. Por favor, intenta de nuevo con una opción válida.")
        }
    }
}

fun mostrarMenu() {
    println("Bienvenido al Menú de Opciones CRUD para Universidades y Carreras")
    println("1. Crear una nueva Universidad")
    println("2. Registrar una nueva Carrera")
    println("3. Visualizar todas las Universidades existentes junto con sus carreras")
    println("4. Visualizar las Carreras de una Universidad específica")
    println("5. Visualizar todas las Carreras registradas")
    println("6. Actualizar la información de una Universidad")
    println("7. Actualizar la información de una Carrera")
    println("8. Eliminar una Universidad existente")
    println("9. Eliminar una Carrera registrada")
    println("10. Finalizar y salir del Programa")
}

fun getIntInput(scanner: Scanner, prompt: String): Int {
    println(prompt)
    while (!scanner.hasNextInt()) {
        println("Por favor, ingrese un número válido.")
        scanner.next()
    }
    return scanner.nextInt()
}

fun crearUniversidad(scanner: Scanner, universidades: MutableList<Universidad>) {
    val id = getIntInput(scanner, "Ingrese el ID de la Universidad:")
    if (universidades.any { it.id == id }) {
        println("Error: Ya existe una universidad con el ID proporcionado.")
        return
    }
    scanner.nextLine()
    println("Ingrese el Nombre de la Universidad:")
    val nombre = scanner.nextLine()
    println("Ingrese la Ubicación de la Universidad:")
    val ubicacion = scanner.nextLine()
    val fechaFundacion = LocalDate.now() // Ejemplo de fecha de fundación
    val esPrivada = true // Ejemplo, modificar según sea necesario
    val nuevaUniversidad = Universidad(id, nombre, ubicacion, fechaFundacion, esPrivada, mutableListOf())
    Universidad.crearUniversidad(universidades, nuevaUniversidad)
}

fun crearCarrera(scanner: Scanner, universidades: MutableList<Universidad>) {
    val universidadId = getIntInput(scanner, "Ingrese el ID de la Universidad a la que pertenece la Carrera:")
    val universidad = universidades.find { it.id == universidadId }
    if (universidad != null) {
        val id = getIntInput(scanner, "Ingrese el ID de la Carrera:")
        if (universidad.carreras.any { it.id == id }) {
            println("Error: Ya existe una carrera con el ID proporcionado en esta universidad.")
            return
        }
        scanner.nextLine()
        println("Ingrese el Nombre de la Carrera:")
        val nombre = scanner.nextLine()
        val creditos = getIntInput(scanner, "Ingrese el número de créditos de la Carrera:")
        val duracion = getIntInput(scanner, "Ingrese la duración en años de la Carrera:")
        val costo = getIntInput(scanner, "Ingrese el costo de la Carrera:")
        val nuevaCarrera = Carrera(id, nombre, creditos, duracion, costo)
        Carrera.crearCarrera(universidad.carreras, nuevaCarrera)
        Universidad.guardarUniversidades(universidades) // Guardar cambios
    } else {
        println("Universidad no encontrada.")
    }
}

fun leerCarrerasDeUniversidad(scanner: Scanner, universidades: MutableList<Universidad>) {
    val universidadId = getIntInput(scanner, "Ingrese el ID de la Universidad cuyas carreras desea ver:")
    val universidad = universidades.find { it.id == universidadId }
    if (universidad != null) {
        Universidad.leerCarreras(universidad.carreras)
    } else {
        println("Universidad no encontrada.")
    }
}

fun leerTodasLasCarreras(universidades: List<Universidad>) {
    if (universidades.isEmpty()) {
        println("No hay universidades para mostrar.")
    } else {
        universidades.forEach { universidad ->
            if (universidad.carreras.isEmpty()) {
                println("No hay carreras en esta universidad.")
            } else {
                universidad.carreras.forEach { carrera ->
                    println("Carrera ID: ${carrera.id}, Nombre: ${carrera.nombre}, Créditos: ${carrera.creditos}, Duración: ${carrera.duracion} años, Costo: ${carrera.costo}")
                }
            }
        }
    }
}

fun actualizarUniversidad(scanner: Scanner, universidades: MutableList<Universidad>) {
    val id = getIntInput(scanner, "Ingrese el ID de la Universidad a actualizar:")
    val universidad = universidades.find { it.id == id }
    if (universidad != null) {
        scanner.nextLine() // Consumir el salto de línea
        println("Ingrese el Nuevo Nombre de la Universidad:")
        val nombre = scanner.nextLine()
        println("Ingrese la Nueva Ubicación de la Universidad:")
        val ubicacion = scanner.nextLine()
        val fechaFundacion = LocalDate.now() // Ejemplo de fecha de actualización
        val esPrivada = true // Ejemplo, modificar según sea necesario
        if (nombre.isNotBlank() && ubicacion.isNotBlank()) {
            universidad.nombre = nombre
            universidad.ubicacion = ubicacion
            universidad.fechaFundacion = fechaFundacion
            universidad.esPrivada = esPrivada
            Universidad.guardarUniversidades(universidades) // Guardar cambios
        } else {
            println("Nombre y ubicación no pueden estar vacíos.")
        }
    } else {
        println("Universidad no encontrada.")
    }
}

fun actualizarCarrera(scanner: Scanner, universidades: MutableList<Universidad>) {
    val universidadId = getIntInput(scanner, "Ingrese el ID de la Universidad a la que pertenece la Carrera:")
    val universidad = universidades.find { it.id == universidadId }
    if (universidad != null) {
        val id = getIntInput(scanner, "Ingrese el ID de la Carrera a actualizar:")
        val carrera = universidad.carreras.find { it.id == id }
        if (carrera != null) {
            scanner.nextLine()
            println("Ingrese el Nuevo Nombre de la Carrera:")
            val nombre = scanner.nextLine()
            val creditos = getIntInput(scanner, "Ingrese el nuevo número de créditos de la Carrera:")
            val duracion = getIntInput(scanner, "Ingrese la nueva duración en años de la Carrera:")
            val costo = getIntInput(scanner, "Ingrese el nuevo costo de la Carrera:")
            if (nombre.isNotBlank() && creditos > 0 && duracion > 0 && costo > 0) {
                carrera.nombre = nombre
                carrera.creditos = creditos
                carrera.duracion = duracion
                carrera.costo = costo
                Universidad.guardarUniversidades(universidades) // Guardar cambios
            } else {
                println("Nombre no puede estar vacío y los valores numéricos deben ser positivos.")
            }
        } else {
            println("Carrera no encontrada.")
        }
    } else {
        println("Universidad no encontrada.")
    }
}

fun eliminarUniversidad(scanner: Scanner, universidades: MutableList<Universidad>) {
    val id = getIntInput(scanner, "Ingrese el ID de la Universidad a eliminar:")
    val universidad = universidades.find { it.id == id }
    if (universidad != null) {
        universidades.removeIf { it.id == id }
        Universidad.guardarUniversidades(universidades) // Guardar cambios
        println("Universidad eliminada correctamente.")
    } else {
        println("Universidad no encontrada.")
    }
}

fun eliminarCarrera(scanner: Scanner, universidades: MutableList<Universidad>) {
    val universidadId = getIntInput(scanner, "Ingrese el ID de la Universidad a la que pertenece la Carrera:")
    val universidad = universidades.find { it.id == universidadId }
    if (universidad != null) {
        val id = getIntInput(scanner, "Ingrese el ID de la Carrera a eliminar:")
        val wasRemoved = universidad.carreras.removeIf { it.id == id }
        if (wasRemoved) {
            Universidad.guardarUniversidades(universidades) // Guardar cambios
            println("Carrera eliminada correctamente.")
        } else {
            println("Carrera no encontrada.")
        }
    } else {
        println("Universidad no encontrada.")
    }
}