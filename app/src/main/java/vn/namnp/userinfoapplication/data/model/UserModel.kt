package vn.namnp.userinfoapplication.data.model

data class UserModel(
    val name: NameModel,
    val email: String,
    val phone: String,
    val picture: PictureModel
)
