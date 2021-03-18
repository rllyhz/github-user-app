package id.rllyhz.githubuser.utils

import android.content.Context
import id.rllyhz.githubuser.R
import id.rllyhz.githubuser.data.User

object DataUtils {
    private val users = ArrayList<User>()

    fun getUsers(context: Context): List<User> {
        val usernames = context.resources.getStringArray(R.array.username)
        val names = context.resources.getStringArray(R.array.name)
        val locations = context.resources.getStringArray(R.array.location)
        val repositories = context.resources.getStringArray(R.array.repository)
        val companyNames = context.resources.getStringArray(R.array.company)
        val followers = context.resources.getStringArray(R.array.followers)
        val followings = context.resources.getStringArray(R.array.following)

        for (position in usernames.indices) {
            val user = User(
                usernames[position].toInt(),
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

        return users
    }
}