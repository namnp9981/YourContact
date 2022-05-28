package vn.namnp.userinfoapplication.data.repostitory

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.namnp.userinfoapplication.data.model.UserModel

interface UserRepository {
    fun getUsers(): Flow<PagingData<UserModel>>
}