package io.melbybaldove.local.token

import android.content.SharedPreferences
import com.google.gson.Gson
import io.melbybaldove.data.auth.LocalTokenSource
import io.melbybaldove.data.auth.Token
import io.melbybaldove.data.exception.NotFoundException
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class LocalTokenSourceImpl @Inject constructor(private val sharedPref: SharedPreferences, private val gson: Gson) : LocalTokenSource {
    companion object {
        const val TOKEN = "TOKEN"
        const val NO_TOKEN = "NO_TOKEN"
    }

    override fun saveToken(token: Token) = Single.create<Token> {
        with(sharedPref.edit()) {
            val json = gson.toJson(token)
            putString(TOKEN, json)
            if (commit()) {
                it.onSuccess(token)
            } else {
                it.onError(Throwable("Shared pref error."))
            }
        }
    }!!

    override fun deleteToken() = Completable.fromAction {
        with(sharedPref.edit()) {
            remove(TOKEN)
            if (!commit()) {
                error("Shared pref error.")
            }
        }
    }!!

    override fun getToken() = Single.create<Token> {
        val json = sharedPref.getString(TOKEN, NO_TOKEN)
        if (json == NO_TOKEN) {
            it.onError(NotFoundException())
        } else {
            it.onSuccess(gson.fromJson(json, Token::class.java))
        }
    }!!
}