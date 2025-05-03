package ru.vopros.foodies.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vopros.foodies.data.room.model.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun listenAll(): Flow<List<CategoryEntity>>

    @Insert
    suspend fun insert(entity: CategoryEntity)

    @Delete
    suspend fun delete(entity: CategoryEntity)

}