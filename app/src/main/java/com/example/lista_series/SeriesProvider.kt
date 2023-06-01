package com.example.lista_series

class SeriesProvider {
    companion object{
        var SeriesList = mutableListOf<Series>(
            Series(
                "Breaking Bad",
                "Jesse",
                R.drawable.bb,
                0
            ),

            Series(
                "Mushoku Tensei",
                "Compralo",
                R.drawable.mt,
                0
            ),

            Series(
                "Steins;Gate",
                "10/10",
                  R.drawable.sg,
                0
            ),

            Series(
                "Re;Zero",
                "Ta chido",
                R.drawable.rz,
                0
            ),

            Series(
                "Tengen Toppa Gurren Laggan",
                "Obra Maestra",
                R.drawable.ttgl,
                0
            ),

        )
    }
}