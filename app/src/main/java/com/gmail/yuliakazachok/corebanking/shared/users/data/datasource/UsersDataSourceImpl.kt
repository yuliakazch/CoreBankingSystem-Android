package com.gmail.yuliakazachok.corebanking.shared.users.data.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle.TOKEN
import com.gmail.yuliakazachok.corebanking.shared.users.data.api.UsersApi
import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.CredentialsDto
import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.TokenDto
import javax.inject.Inject

class UsersDataSourceImpl @Inject constructor(
    private val api: UsersApi,
    private val sharedPreferences: SharedPreferences
) : UsersDataSource {

    override fun isTokenExist(): Boolean =
        !sharedPreferences.getString(TOKEN, null).isNullOrEmpty()

    override fun getToken(): TokenDto = TokenDto(
        sharedPreferences.getString(TOKEN, null) ?: throw IllegalStateException("Token doesn't exists")
    )

    override fun saveToken(tokenDto: TokenDto) {
        sharedPreferences.edit(commit = true) {
            putString(TOKEN, tokenDto.value)
        }
    }

    override fun clearToken() {
        if (isTokenExist()) {
            sharedPreferences.edit(commit = true) {
                remove(TOKEN)
            }
        }
    }

    override suspend fun auth(credentials: CredentialsDto): TokenDto =
        api.auth(credentials)
}