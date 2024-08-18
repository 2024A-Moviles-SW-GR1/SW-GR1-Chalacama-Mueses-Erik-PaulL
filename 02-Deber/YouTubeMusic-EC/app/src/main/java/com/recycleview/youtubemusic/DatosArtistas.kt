package com.recycleview.youtubemusic

class DatosArtistas {

    val cancionesAEscucharList = arrayListOf<Cancion>()
    val categoriasList = arrayListOf<String>()


    init {

        cancionesAEscucharList.add(
            Cancion(
                "Smooth Operator",
                "Sade",
                "https://upload.wikimedia.org/wikipedia/en/1/1d/Sade_-_Smooth_Operator.png",
                "Escuchar"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Purple Rain",
                "Prince",
                "https://upload.wikimedia.org/wikipedia/en/8/86/Purple-rain-cover.jpg",
                "Escuchar"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Dreams",
                "Fleetwood Mac",
                "https://i.ytimg.com/vi/Yam5uK6e-bQ/mqdefault.jpg",
                "Escuchar"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Billie Jean",
                "Michael Jackson",
                "https://muzikalia.com/wp-content/uploads/2021/06/michael-jackson-billie-jean-portada.jpg",
                "Escuchar"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Take On Me",
                "a-ha",
                "https://i1.sndcdn.com/artworks-MuznBOvNShxmspwv-9SXLYg-t500x500.jpg",
                "Escuchar"
            )
        )


        cancionesAEscucharList.add(
            Cancion(
                "In the End",
                "Linkin Park",
                "https://i.scdn.co/image/ab67616d00001e02e2f039481babe23658fc719a",
                "Otra Vez"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Wonderwall",
                "Oasis",
                "https://f4.bcbits.com/img/a2577749088_65",
                "Otra Vez"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Bohemian Rhapsody",
                "Queen",
                "https://m.media-amazon.com/images/M/MV5BMTA2NDc3Njg5NDVeQTJeQWpwZ15BbWU4MDc1NDcxNTUz._V1_.jpg",
                "Otra Vez"
            )
        )


        cancionesAEscucharList.add(
            Cancion(
                "The Wall",
                "Pink Floyd",
                "https://i.scdn.co/image/ab67616d0000b2735d48e2f56d691f9a4e4b0bdf",
                "Albumes Destacados"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Abbey Road",
                "The Beatles",
                "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg",
                "Albumes Destacados"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Nevermind",
                "Nirvana",
                "https://upload.wikimedia.org/wikipedia/en/b/b7/NirvanaNevermindalbumcover.jpg",
                "Albumes Destacados"
            )
        )

        cancionesAEscucharList.add(
            Cancion(
                "Greatest Hits of the 80s",
                "Various Artists",
                "https://m.media-amazon.com/images/I/71-T3JmXruL._UF1000,1000_QL80_.jpg",
                "MixParaTi"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "90s Dance Party",
                "Various Artists",
                "https://example.com/imagen_90s_dance.jpg",
                "MixParaTi"
            )
        )

// Canciones para la categoría "Artistas Más Escuchadas"
        cancionesAEscucharList.add(
            Cancion(
                "Hotel California",
                "Eagles",
                "https://www.lavanguardia.com/files/content_image_mobile_filter/uploads/2016/12/08/5fa2f9876c5dd.jpeg",
                "ArtistasMasEscuchadas"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Imagine",
                "John Lennon",
                "https://i.scdn.co/image/ab67616d0000b27399581550ef9746ca582bb3cc",
                "ArtistasMasEscuchadas"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Stairway to Heaven",
                "Led Zeppelin",
                "https://m.media-amazon.com/images/M/MV5BNjdlMTk5ODItYTlhZC00OTZmLWJiOGYtY2NkMTk5OWU1MmU0XkEyXkFqcGdeQXVyMTUyNjc3NDQ4._V1_.jpg",
                "ArtistasMasEscuchadas"
            )
        )

// Canciones para la categoría "Listas Recomendadas"
        cancionesAEscucharList.add(
            Cancion(
                "Classic Rock Essentials",
                "Various Artists",
                "https://i.scdn.co/image/ab67616d0000b27390e3a1e1cf7cc810fb315590",
                "ListasRecomendadas"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Best of the 70s",
                "Various Artists",
                "https://i.scdn.co/image/ab67616d0000b27365d1f3dcfe7047c6c8e5ee58",
                "ListasRecomendadas"
            )
        )
        cancionesAEscucharList.add(
            Cancion(
                "Jazz Classics",
                "Various Artists",
                "https://i.scdn.co/image/ab67706f00000002d6717501a3dc7fdef7aa1694",
                "ListasRecomendadas"
            )
        )


        // Categorías a la lista de categorías
        categoriasList.add("Escuchar")
        categoriasList.add("Otra Vez")
        categoriasList.add("Albumes Destacados")
        categoriasList.add("MixParaTi")
        categoriasList.add("ArtistasMasEscuchadas")
        categoriasList.add("ListasRecomendadas")
    }


    fun generarCategoriasArtistasList(): List<Artista> {
        val categoriasArtistasList = mutableListOf<Artista>()

        for (categoria in categoriasList) {
            val artistasEnCategoria = mutableListOf<Cancion>()
            for (artista in cancionesAEscucharList) {
                // Aquí debes implementar la lógica para verificar si el artista pertenece a la categoría actual
                if (artista.categoria == categoria) {
                    artistasEnCategoria.add(artista)
                }
            }
            categoriasArtistasList.add(Artista(categoria, artistasEnCategoria))
        }
        return categoriasArtistasList
    }
}
