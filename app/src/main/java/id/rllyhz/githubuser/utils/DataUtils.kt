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
            val avatar = when (position) {
                0 -> R.drawable.user1
                1 -> R.drawable.user2
                2 -> R.drawable.user3
                3 -> R.drawable.user4
                4 -> R.drawable.user5
                5 -> R.drawable.user6
                6 -> R.drawable.user7
                7 -> R.drawable.user8
                8 -> R.drawable.user9
                9 -> R.drawable.user10
                else -> R.drawable.ic_launcher_foreground
            }

            val user = User(
                avatar,
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