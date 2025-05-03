package ru.vopros.foodies.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vopros.foodies.data.room.model.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    suspend fun fetchAll(): List<ProductEntity>

    @Query("SELECT * FROM products")
    fun listenAll(): Flow<List<ProductEntity>>

    @Insert
    suspend fun insert(entity: ProductEntity)

    @Delete
    suspend fun delete(entity: ProductEntity)

}