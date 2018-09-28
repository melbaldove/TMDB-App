package io.melbybaldove.investagramsexam.ui.util

import io.melbybaldove.commons.ErrorModel
import io.melbybaldove.commons.LoadingOptions

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
interface DialogHelper {
    fun showConfirmDialog(title: String, content: String, buttonText: String, listener: () -> Unit)

    fun shouldShowLoading(loadingOptions: LoadingOptions)

    fun showError(errorModel: ErrorModel)

}