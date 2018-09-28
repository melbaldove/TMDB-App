package io.melbybaldove.investagramsexam.local

import dagger.Module
import dagger.Provides
import io.melbybaldove.data.auth.LocalTokenSource
import io.melbybaldove.local.token.LocalTokenSourceImpl
import javax.inject.Singleton

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@Module
class LocalModule {
    @Singleton
    @Provides
    fun provideLocalTokenSource(localTokenSourceImpl: LocalTokenSourceImpl): LocalTokenSource = localTokenSourceImpl
}