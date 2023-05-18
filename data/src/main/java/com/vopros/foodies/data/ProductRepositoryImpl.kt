package com.vopros.foodies.data

import android.content.Context
import com.vopros.foodies.domain.product.Product

class ProductRepositoryImpl(context: Context): RepositoryImpl<Product>(
    context, "Products.json", Product.serializer()
)