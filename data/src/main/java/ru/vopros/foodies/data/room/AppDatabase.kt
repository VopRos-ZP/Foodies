package ru.vopros.foodies.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.vopros.foodies.data.room.converters.ListTypeConverter
import ru.vopros.foodies.data.room.dao.BasketDao
import ru.vopros.foodies.data.room.dao.CategoryDao
import ru.vopros.foodies.data.room.dao.ProductDao
import ru.vopros.foodies.data.room.dao.TagDao
import ru.vopros.foodies.data.room.model.BasketEntity
import ru.vopros.foodies.data.room.model.CategoryEntity
import ru.vopros.foodies.data.room.model.ProductEntity
import ru.vopros.foodies.data.room.model.TagEntity

@Database(
    version = 1,
    entities = [
        TagEntity::class,
        CategoryEntity::class,
        ProductEntity::class,
        BasketEntity::class,
    ],
)
@TypeConverters(ListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val tagDao: TagDao
    abstract val categoryDao: CategoryDao
    abstract val productDao: ProductDao
    abstract val basketDao: BasketDao

    companion object {
        const val NAME = "app_database"
    }
}