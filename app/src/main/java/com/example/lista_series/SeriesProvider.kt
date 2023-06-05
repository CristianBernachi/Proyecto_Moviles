package com.example.lista_series

class SeriesProvider {
    companion object{
        var SeriesList = mutableListOf<Series>(
            Series(
                "Breaking Bad",
                "Breaking Bad es una serie Americana",
                R.drawable.bb,
                0,
                48,
                false,
                "Breaking Bad es una serie estadounidense que cuenta la historia de Walter White, un profesor de química que se convierte en un peligroso narcotraficante de metanfetaminas.",

                ),

            Series(
                "Mushoku Tensei",
                "Mushoku Tensei es un anime Isekai",
                R.drawable.mt,
                0,
                35,
                false,
                "Mushoku Tensei es una serie de novelas ligeras japonesas y anime que sigue la vida de Rudeus Greyrat, un hombre que renace en un mundo de fantasía. Con su conocimiento previo, Rudeus se convierte en un poderoso mago mientras enfrenta desafíos y busca su redención. La serie combina aventuras, magia y temas de crecimiento personal en un emocionante mundo de fantasía.",

                ),

            Series(
                "Steins;Gate",
                "Steins;Gate es un anime de viajes en el tiempo",
                R.drawable.sg,
                0,
                24,
                false,
                "Steins;Gate es un anime que sigue la historia de Rintarou Okabe y sus amigos mientras experimentan con viajes en el tiempo. Descubren las consecuencias de sus acciones y se enfrentan a una conspiración que amenaza su existencia. La serie combina ciencia ficción, intriga y emoción en una trama cautivadora.",
                ),

            Series(
                "Re;Zero",
                "Re:Zero es un anime Isekai",
                R.drawable.rz,
                0,
                48,
                false,
                "Re:Zero es un anime que sigue la historia de Subaru Natsuki, un joven que es transportado a un mundo de fantasía y tiene la habilidad de regresar en el tiempo cada vez que muere. En su búsqueda por proteger a quienes le importan, Subaru enfrenta desafíos y tragedias, explorando temas de determinación y consecuencias. Es un emocionante viaje de redención y superación personal.",

                ),

            Series(
                "Tengen Toppa",
                "Tengen Toppa Gurren Laggan es un anime de mechas",
                R.drawable.ttgl,
                0,
                25,
                false,
                "Tengen Toppa Gurren Lagann es un anime de acción y aventuras en el que Simon y Kamina luchan contra un régimen opresivo. Con su mecha Gurren Lagann, buscan liberar a la humanidad y alcanzar la superficie. La serie se destaca por su energía explosiva y un mensaje inspirador sobre la determinación humana.",
                ),

        )
    }
}