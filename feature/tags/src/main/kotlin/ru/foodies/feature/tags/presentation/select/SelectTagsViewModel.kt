package ru.foodies.feature.tags.presentation.select

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.foodies.feature.tags.domain.ApplyFiltersUseCase
import ru.foodies.feature.tags.domain.GetTagListUseCase
import ru.foodies.feature.tags.domain.Tag

class SelectTagsViewModel(
    private val getTagListUseCase: GetTagListUseCase,
    private val applyFiltersUseCase: ApplyFiltersUseCase,
) : ViewModel(), ContainerHost<SelectTagsState, SelectTagsSideEffect> {

    override val container = container<SelectTagsState, SelectTagsSideEffect>(
        initialState = SelectTagsState(),
        onCreate = {
            val tags = getTagListUseCase()
            reduce { state.copy(tags = tags) }
        }
    )

    fun onTagClick(tag: Tag) = intent {
        reduce {
            val selectedTags = state.selectedTags.toMutableList().apply {
                when (contains(tag)) {
                    true -> remove(tag)
                    else -> add(tag)
                }
            }.toList()
            state.copy(selectedTags = selectedTags)
        }
    }

    fun onDismiss() = intent {
        postSideEffect(SelectTagsSideEffect.OnDismiss)
    }

    fun onApplyClick() = intent {
        applyFiltersUseCase(state.selectedTags)
        postSideEffect(SelectTagsSideEffect.OnDismiss)
    }

}