package com.example.cryptoapp.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class WorkerFactory @Inject constructor(
    private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            DataRefreshWorker::class.qualifiedName -> {
                workerProviders[DataRefreshWorker::class.java]
                    ?.get()
                    ?.create(context = appContext, workerParameters)
            }

            else -> {
                null
            }
        }
    }
}