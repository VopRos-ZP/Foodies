package com.vopros.foodies.data

import android.content.Context
import com.vopros.foodies.domain.category.Category

class CategoryRepositoryImpl(context: Context): RepositoryImpl<Category>(
    context, "Categories.json", Category.serializer()
)