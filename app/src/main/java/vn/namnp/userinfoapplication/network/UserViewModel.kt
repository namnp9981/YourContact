package vn.namnp.userinfoapplication.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import vn.namnp.userinfoapplication.data.repostitory.UserRepository
import vn.namnp.userinfoapplication.presentation.ui.UserUiItemState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val userItemUiSates = userRepository.getUsers()
        .map { pagingData ->
            pagingData.map { userModel -> UserUiItemState(userModel)
            }
        }.cachedIn(viewModelScope)

    init {

    }


}