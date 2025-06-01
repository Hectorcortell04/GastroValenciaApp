package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api.EventApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

class EventDataSource {
    private val eventApi = NetworkClient.instance.create(EventApi::class.java)

    suspend fun getAllEvents(): List<EventDto> {
   return eventApi.getEvents()
        /*return listOf(
            EventDto(
                id = 1,
                name = "Feria Gastronómica del Mediterráneo",
                category = "Gastronomía",
                location = "Marina de Valencia",
                date = "2025-06-15",
                price = 12.50,
                time = "18:00",
                description = "La mayor feria gastronómica de Valencia donde los mejores chefs locales presentan platos tradicionales e innovadores. Incluye degustaciones, showcookings y talleres culinarios para todos los públicos.",
                duration = "3 días",
                eventImage = "https://example.com/images/feriagastronomica.jpg"
            ),
            EventDto(
                id = 2,
                name = "Ruta de la Tapa Valenciana",
                category = "Gastronomía",
                location = "Barrio del Carmen",
                date = "2025-07-10",
                price = 25.00,
                time = "18:00",
                description = "Recorrido por los mejores bares y restaurantes del centro histórico, donde podrás degustar tapas tradicionales valencianas con un toque creativo, acompañadas de vinos locales.",
                duration = "1 día",
                eventImage = "https://example.com/images/rutatapas.jpg"
            ),
            EventDto(
                id = 3,
                name = "Festival de Arroces Tradicionales",
                category = "Gastronomía",
                location = "Playa de la Malvarrosa",
                date = "2025-08-22",
                price = 30.00,
                time = "18:00",
                description = "Un evento único donde los maestros arroceros valencianos compiten por crear el mejor arroz. Los asistentes pueden degustar más de 20 variedades diferentes y votar por su favorito.",
                duration = "2 días",
                eventImage = "https://example.com/images/festivalarroces.jpg"
            ),
            EventDto(
                id = 4,
                name = "Valencia Culinary Week",
                category = "Gastronomía",
                location = "Diversos restaurantes de la ciudad",
                date = "2025-09-05",
                price = 45.00,
                time = "18:00",
                description = "Semana dedicada a la alta cocina valenciana con menús especiales a precio reducido en restaurantes con estrellas Michelin y reconocidos por la guía Repsol.",
                duration = "7 días",
                eventImage = "https://example.com/images/culinaryweek.jpg"
            ),
            EventDto(
                id = 5,
                name = "Cata de Vinos Valencianos",
                category = "Enología",
                location = "Mercado de Colón",
                date = "2025-06-28",
                price = 35.00,
                time = "18:00",
                description = "Evento enológico donde podrás degustar los mejores vinos de las denominaciones de origen valencianas: Utiel-Requena, Valencia y Alicante, guiado por sumilleres expertos.",
                duration = "5 horas",
                eventImage = "https://example.com/images/catavinos.jpg"
            ),
            EventDto(
                id = 6,
                name = "Festival de la Horchata y Fartons",
                category = "Gastronomía",
                location = "Alboraya",
                date = "2025-07-25",
                price = 8.00,
                time = "18:00",
                description = "Celebración del dulce más emblemático de Valencia. Incluye visitas a campos de chufa, demostración del proceso de elaboración de la horchata artesanal y degustaciones.",
                duration = "1 día",
                eventImage = "https://example.com/images/horchata.jpg"
            ),
            EventDto(
                id = 7,
                name = "Concierto Gastronómico Sensorial",
                category = "Gastronomía y Música",
                location = "Palau de les Arts Reina Sofía",
                date = "2025-10-12",
                price = 65.00,
                time = "18:00",
                description = "Experiencia única que combina música clásica en directo con un menú degustación diseñado para potenciar la experiencia sensorial. Cada plato se sincroniza con piezas musicales específicas.",
                duration = "3 horas",
                eventImage = "https://example.com/images/conciertogastronomico.jpg"
            ),
            EventDto(
                id = 8,
                name = "Workshop de Paella Valenciana",
                category = "Taller Gastronómico",
                location = "Escuela de Arroces y Paellas",
                date = "2025-08-05",
                price = 50.00,
                time = "18:00",
                description = "Taller práctico donde aprenderás a cocinar la auténtica paella valenciana de la mano de cocineros profesionales. Incluye almuerzo con la paella preparada y certificado de participación.",
                duration = "4 horas",
                eventImage = "https://example.com/images/workshoppaella.jpg"
            ),
            EventDto(
                id = 9,
                name = "Mercado Nocturno Gastronómico",
                category = "Gastronomía",
                location = "Jardines del Turia",
                date = "2025-07-15",
                price = 5.00,
                time = "18:00",
                description = "Mercado al aire libre con los mejores food trucks y puestos gastronómicos de la ciudad. Disfruta de comida internacional y local en un ambiente festivo con música en directo.",
                duration = "5 horas",
                eventImage = "https://example.com/images/mercadonocturno.jpg"
            ),
            EventDto(
                id = 10,
                name = "Día de la Fideuà",
                category = "Gastronomía",
                location = "Puerto de Valencia",
                date = "2025-09-18",
                price = 20.00,
                description = "Jornada dedicada a la fideuà, plato típico valenciano similar a la paella pero elaborado con fideos. Incluye concurso entre restaurantes locales y degustación para el público.",
                duration = "1 día",
                time = "18:00",
                eventImage = "https://example.com/images/fideua.jpg"
            )
        )*/
    }
}