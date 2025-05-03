package ru.vopros.foodies.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vopros.foodies.data.room.model.TagEntity

@Dao
interface TagDao {

    @Query("SELECT * FROM tags")
    fun listenAll(): Flow<List<TagEntity>>

    @Insert
    suspend fun insert(entity: TagEntity)

    @Delete
    suspend fun delete(entity: TagEntity)

}