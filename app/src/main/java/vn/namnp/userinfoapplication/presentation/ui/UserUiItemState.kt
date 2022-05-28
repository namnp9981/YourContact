package vn.namnp.userinfoapplication.presentation.ui

import vn.namnp.userinfoapplication.data.model.UserModel

data class UserUiItemState(private val userModel: UserModel) {

    fun getImageUrl() = userModel.picture.thumbnail

    fun getName() = "${userModel.name.first} ${userModel.name.last}"

    fun getPhone() = userModel.phone

    fun getMail() = userModel.email

}