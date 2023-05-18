package com.vopros.foodies.domain

import com.vopros.foodies.domain.basket.Basket
import com.vopros.foodies.domain.basket.BasketRepository
import com.vopros.foodies.domain.product.Product
import org.junit.Test
import org.junit.Assert.*
import org.mockito.kotlin.mock

class BasketRepositoryTest {

    private val testProduct = Product(
        id = 1,
        categoryId = 676168,
        name = "Авокадо Кранч Маки 8шт",
        description = "Ролл с нежным мясом камчатского краба, копченой курицей и авокадо.Украшается соусом\"Унаги\" и легким майонезом  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        image = "1.jpg",
        priceCurrent = 47000,
        priceOld = null,
        measure = 250,
        measureUnit = "г",
        energy = 307.8,
        proteins = 6.1,
        fats = 11.4,
        carbohydrates = 45.1,
        tagIds = emptyList()
    )

    private val productList = mutableListOf<Basket>()

    private val repository: BasketRepository = mock {
        on { fetchAllProducts() }.thenReturn(productList)
    }

    @Test
    fun addProductToBasket() {
        val testData = Basket(
            product = testProduct,
            count = 1
        )
        repository.addProduct(testProduct)
        assertArrayEquals(repository.fetchAllProducts().toTypedArray(), arrayOf(testData))
    }

}