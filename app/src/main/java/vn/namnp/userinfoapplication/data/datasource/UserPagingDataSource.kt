package vn.namnp.userinfoapplication.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import vn.namnp.userinfoapplication.data.model.UserModel
import vn.namnp.userinfoapplication.network.UserApi

class UserPagingDataSource(private val userApi: UserApi) : PagingSource<Int, UserModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = userApi.getUsers(page, params.loadSize)
            LoadResult.Page(
                data = response.results,
                prevKey = if(page == DEFAULT_PAGE_INDEX) null else page.minus(1),
                nextKey = if(response.results.isEmpty()) null else page.plus(1),
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val DEFAULT_PAGE_INDEX = 1
    }
}