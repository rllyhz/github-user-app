package id.rllyhz.githubuser.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var avatar: Int,
    var fullname: String,
    var username: String,
    var companyName: String,
    var totalFollowers: String,
    var totalFollowings: String,
    var totalRepositories: String,
    var location: String
) : Parcelable