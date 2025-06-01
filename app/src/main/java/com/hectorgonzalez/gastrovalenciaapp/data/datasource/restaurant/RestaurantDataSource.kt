package com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.api.RestaurantApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

class RestaurantDataSource {
    private val restaurantApi = NetworkClient.instance.create(RestaurantApi::class.java)

    suspend fun getRestaurants(): List<RestaurantDto> {
        //return restaurantApi.getRestaurants()
        val imagesUrl = listOf(
            "https://content.arquitecturaydiseno.es/medio/2023/11/23/3-la-sastreria_cee9b985_231123155623_1900x1266.jpg",
            "https://content.arquitecturaydiseno.es/medio/2023/11/23/3-la-sastreria_cee9b985_231123155623_1900x1266.jpg",
            "https://content.arquitecturaydiseno.es/medio/2023/11/23/3-la-sastreria_cee9b985_231123155623_1900x1266.jpg"
        )
        var menuUrl =             "https://content.arquitecturaydiseno.es/medio/2023/11/23/3-la-sastreria_cee9b985_231123155623_1900x1266.jpg"

        return listOf(
            return listOf(
                RestaurantDto(
                    1,
                    "La Riuà",
                    "Tradicional valenciana",
                    "Carrer del Mar, 27, 46003 València",
                    4.7,
                    35.0,
                    imagesUrl,
                    menuUrl,
                    "Restaurante clásico valenciano con más de 30 años de historia que ofrece la auténtica paella y otros platos tradicionales en un ambiente acogedor"
                ),
                RestaurantDto(
                    2,
                    "Panorama",
                    "Mediterránea fusión",
                    "Paseo de la Alameda, 34, 46023 València",
                    4.8,
                    45.0,
                    imagesUrl,
                    menuUrl,
                    "Elegante restaurante con vistas al río Turia que combina la cocina mediterránea tradicional con técnicas modernas y toques de fusión internacional"
                ),
                RestaurantDto(
                    3,
                    "El Racó de la Paella",
                    "Arroces y paellas",
                    "Calle Císcar, 12, 46005 València",
                    4.5,
                    28.0,
                    imagesUrl,
                    menuUrl,
                    "Especializado en arroces valencianos, este acogedor local familiar ofrece hasta 15 variedades diferentes de paella y otros arroces tradicionales"
                ),
                RestaurantDto(
                    4,
                    "Mercatbar",
                    "Cocina de autor",
                    "Calle Joaquín Costa, 27, 46005 València",
                    4.6,
                    42.0,
                    imagesUrl,
                    menuUrl,
                    "Restaurante del reconocido chef Quique Dacosta que ofrece tapas creativas y platos de cocina de autor basados en productos de temporada y mercado"
                ),
                RestaurantDto(
                    5,
                    "Casa Carmela",
                    "Tradicional valenciana",
                    "Calle Isabel de Villena, 155, 46011 València",
                    4.9,
                    38.0,
                    imagesUrl,
                    menuUrl,
                    "Icónico restaurante junto a la playa de la Malvarrosa con más de 100 años de historia, famoso por su paella cocinada a leña siguiendo recetas tradicionales"
                ),
                RestaurantDto(
                    6,
                    "Saona Colón",
                    "Mediterránea moderna",
                    "Calle Colón, 15, 46004 València",
                    4.3,
                    25.0,
                    imagesUrl,
                    menuUrl,
                    "Restaurante moderno con ambiente acogedor que ofrece cocina mediterránea de calidad a precios razonables, ideal para comidas de trabajo o cenas casuales"
                ),
                RestaurantDto(
                    7,
                    "El Poblet",
                    "Alta cocina valenciana",
                    "Calle Correos, 8, 46002 València",
                    4.9,
                    85.0,
                    imagesUrl,
                    menuUrl,
                    "Restaurante con dos estrellas Michelin del chef Quique Dacosta que ofrece una experiencia gastronómica excepcional con menús degustación que reinterpretan la cocina valenciana"
                ),
                RestaurantDto(
                    8,
                    "La Salita",
                    "Creativa contemporánea",
                    "Calle Maestro José Serrano, 5, 46005 València",
                    4.8,
                    70.0,
                    imagesUrl,
                    menuUrl,
                    "Restaurante con estrella Michelin dirigido por la chef Begoña Rodrigo que ofrece una cocina creativa basada en productos locales y de temporada"
                ),
                RestaurantDto(
                    9,
                    "Albufera",
                    "Arroces y mariscos",
                    "Calle Pintor Salvador Abril, 30, 46005 València",
                    4.4,
                    30.0,
                    imagesUrl,
                    menuUrl,
                    "Especializado en arroces, fideuàs y mariscos frescos, este restaurante con ambiente marinero ofrece los sabores auténticos de la Albufera valenciana"
                ),
                RestaurantDto(
                    10,
                    "Tinto Fino Ultramarino",
                    "Tapas gourmet",
                    "Calle del Conde de Altea, 18, 46005 València",
                    4.6,
                    32.0,
                    imagesUrl,
                    menuUrl,
                    "Gastrobar con excelente selección de vinos y tapas gourmet que combina la tradición española con toques innovadores en un ambiente elegante y acogedor"
                )
            )
        )
    }
}