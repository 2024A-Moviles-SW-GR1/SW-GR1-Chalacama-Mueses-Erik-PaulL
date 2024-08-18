package com.recycleview.youtubemusic

import ArtistaAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val datosArtistas = DatosArtistas()
        val categoriasArtistasList = datosArtistas.generarCategoriasArtistasList()


        val recyclerViewEscuchar = findViewById<RecyclerView>(R.id.idRVcategoriaAEscuchar)
        recyclerViewEscuchar.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val artistaAdapterEscuchar = ArtistaAdapter(
            (categoriasArtistasList.find { it.categoria == "Escuchar" }?.artistas
                ?: ArrayList()) as ArrayList<Cancion>, this
        )
        recyclerViewEscuchar.adapter = artistaAdapterEscuchar


        val recyclerViewOtraVez = findViewById<RecyclerView>(R.id.idRVcategoriaOtraVez)
        recyclerViewOtraVez.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val artistaAdapterOtraVez = ArtistaAdapter(
            (categoriasArtistasList.find { it.categoria == "Otra Vez" }?.artistas
                ?: ArrayList()) as ArrayList<Cancion>, this
        )
        recyclerViewOtraVez.adapter = artistaAdapterOtraVez


        val recyclerViewAlbumes = findViewById<RecyclerView>(R.id.idRVcategoriaAlbumDestacados)
        recyclerViewAlbumes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val artistaAdapterAlbumes = ArtistaAdapter(
            (categoriasArtistasList.find { it.categoria == "Albumes Destacados" }?.artistas
                ?: ArrayList()) as ArrayList<Cancion>, this
        )
        recyclerViewAlbumes.adapter = artistaAdapterAlbumes


        val recyclerViewMixes = findViewById<RecyclerView>(R.id.idRVcategoriaMixesParaTi)
        recyclerViewMixes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val artistaAdapterMixes = ArtistaAdapter(
            (categoriasArtistasList.find { it.categoria == "MixParaTi" }?.artistas
                ?: ArrayList()) as ArrayList<Cancion>, this
        )
        recyclerViewMixes.adapter = artistaAdapterMixes


        val recyclerViewArtistasMasEscuchadas =
            findViewById<RecyclerView>(R.id.idRVcategoriaArtistasMasEscuchadas)
        recyclerViewArtistasMasEscuchadas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val artistaAdapterArtistasMasEscuchadas = ArtistaAdapter(
            (categoriasArtistasList.find { it.categoria == "ArtistasMasEscuchadas" }?.artistas
                ?: ArrayList()) as ArrayList<Cancion>, this
        )
        recyclerViewArtistasMasEscuchadas.adapter = artistaAdapterArtistasMasEscuchadas


        val recyclerViewListasRecomendas =
            findViewById<RecyclerView>(R.id.idRVcategoriaListasRecomendas)
        recyclerViewListasRecomendas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val artistaAdapterListasRecomendas = ArtistaAdapter(
            (categoriasArtistasList.find { it.categoria == "ListasRecomendadas" }?.artistas
                ?: ArrayList()) as ArrayList<Cancion>, this
        )
        recyclerViewListasRecomendas.adapter = artistaAdapterListasRecomendas

    }
}
