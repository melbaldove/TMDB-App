package io.melbybaldove.presentation.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@AssistedModule
@Module(includes = [AssistedInject_PresentationAssistedModule::class])
abstract class PresentationAssistedModule