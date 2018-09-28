package io.melbybaldove.investagramsexam.authentication

import dagger.Module
import dagger.Provides
import io.melbybaldove.authentication.Authenticator
import io.melbybaldove.authentication.AuthenticatorImpl
import io.melbybaldove.authentication.api.AuthenticationApi
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
class AuthenticationModule {
    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticator(authenticatorImpl: AuthenticatorImpl): Authenticator = authenticatorImpl
}