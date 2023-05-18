package com.vopros.foodies.data

import android.content.Context
import com.vopros.foodies.domain.tag.Tag

class TagRepositoryImpl(context: Context): RepositoryImpl<Tag>(
    context, "Tags.json", Tag.serializer()
)