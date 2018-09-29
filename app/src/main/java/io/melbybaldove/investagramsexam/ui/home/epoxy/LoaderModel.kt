package io.melbybaldove.investagramsexam.ui.home.epoxy

import android.content.Context
import com.airbnb.epoxy.ModelView
import me.zhanghai.android.materialprogressbar.MaterialProgressBar

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LoaderModel(context: Context) : MaterialProgressBar(context)