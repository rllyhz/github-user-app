package id.rllyhz.githubuser.utils

import android.annotation.SuppressLint
import android.content.Context
import id.rllyhz.githubuser.R
import id.rllyhz.githubuser.data.User

object DataUtils {
    @SuppressLint("Recycle")
    fun getUsers(context: Context): MutableList<User> {
        val users: MutableList<User> = mutableListOf()

        val usernames = context.resources.getStringArray(R.array.username)
        val names = context.resources.getStringArray(R.array.name)
        val locations = context.resources.getStringArray(R.array.location)
        val repositories = context.resources.getStringArray(R.array.repository)
        val companyNames = context.resources.getStringArray(R.array.company)
        val followers = context.resources.getStringArray(R.array.followers)
        val followings = context.resources.getStringArray(R.array.following)
        val avatars = context.resources.obtainTypedArray(R.array.avatar)

        for (position in usernames.indices) {

            val user = User(
                avatars.getResourceId(position, R.drawable.ic_launcher_background),
                names[position],
                usernames[position],
                companyNames[position],
                followers[position],
                followings[position],
                repositories[position],
                locations[position]
            )

            users.add(user)
        }

        // avoiding memory leaks
        avatars.recycle()

        return users
    }
}