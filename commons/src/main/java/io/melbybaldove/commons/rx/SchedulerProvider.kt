package io.melbybaldove.commons.rx

import io.reactivex.Scheduler

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun <T> ioToUi(): StreamTransformer<T> = StreamTransformer(io(), ui())
}