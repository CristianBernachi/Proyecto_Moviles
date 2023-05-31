package com.example.lista_series

class SeriesProvider {
    companion object{
        var SeriesList = mutableListOf<Series>(
            Series(
                "Breaking Bad",
                "Jesse",
                R.drawable.bb
            ),

            Series(
                "Mushoku Tensei",
                "Compralo",
                R.drawable.mt
            ),

            Series(
                "Steins;Gate",
                "10/10",
                  R.drawable.sg
            ),

            Series(
                "Re;Zero",
                "Ta chido",
                R.drawable.rz
            ),

            Series(
                "Tengen Toppa Gurren Laggan",
                "Obra Maestra",
                R.drawable.ttgl
            ),

        )
    }
}