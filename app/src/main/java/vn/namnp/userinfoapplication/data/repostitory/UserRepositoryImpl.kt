package vn.namnp.userinfoapplication.data.repostitory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.namnp.userinfoapplication.data.datasource.UserPagingDataSource
import vn.namnp.userinfoapplication.data.model.UserModel
import vn.namnp.userinfoapplication.network.UserApi
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {
    override fun getUsers(): Flow<PagingData<UserModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
            ),
            initialKey = INITIAL_KEY,
            pagingSourceFactory = { UserPagingDataSource(userApi) }
        ).flow
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
        private const val INITIAL_KEY = 1
    }
}