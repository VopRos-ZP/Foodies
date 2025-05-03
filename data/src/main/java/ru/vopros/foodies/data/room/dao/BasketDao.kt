package ru.vopros.foodies.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vopros.foodies.data.room.model.BasketEntity
import ru.vopros.foodies.data.room.model.ProductWithCountEntity

@Dao
interface BasketDao {

    @Query("SELECT product_id, count(product_id) as count FROM baskets GROUP BY product_id")
    fun listenAll(): Flow<List<ProductWithCountEntity>>

    @Query("SELECT SUM(p.price_current) as price_current FROM baskets b JOIN products p ON b.product_id = p.id")
    fun listenSum(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: BasketEntity)

    @Query("DELETE FROM baskets WHERE id = (SELECT id FROM baskets WHERE product_id = :id LIMIT 1)")
    suspend fun delete(id: Int)

}