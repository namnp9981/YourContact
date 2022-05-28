package vn.namnp.userinfoapplication.network

import retrofit2.http.GET
import retrofit2.http.Query
import vn.namnp.userinfoapplication.data.model.UserResponse

interface UserApi {

    @GET(".")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): UserResponse
}