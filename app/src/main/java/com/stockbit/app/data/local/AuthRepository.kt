package com.stockbit.app.data.local

import com.stockbit.app.data.model.Auth
import io.paperdb.Paper

object AuthRepository {

    private const val BOOK_AUTH = "BOOK_AUTH"
    private const val KEY_AUTH = "KEY_AUTH"

    fun setAuth(auth : Auth) {
        Paper.book(BOOK_AUTH).write(KEY_AUTH, auth)
    }

    fun getAuth() : Auth? {
        return Paper.book(BOOK_AUTH).read(KEY_AUTH, null)
    }

    fun isLogin() : Boolean{
        return getAuth() != null
    }

    fun logout(){
        Paper.book(KEY_AUTH).destroy()
    }

}