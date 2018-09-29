package io.melbybaldove.investagramsexam.ui.home.epoxy

import android.support.annotation.StringRes
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import io.melbybaldove.investagramsexam.R
import kotlinx.android.synthetic.main.layout_search_results_heading.view.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@EpoxyModelClass(layout = R.layout.layout_search_results_heading)
abstract class SearchHeadingModel : EpoxyModelWithHolder<SearchHeadingModel.Holder>() {
    @EpoxyAttribute
    @StringRes
    lateinit var query: String

    override fun bind(holder: Holder) {
        holder.view.layout_search_results_heading.text = "Showing movies that match \"$query\""
    }

    class Holder : EpoxyHolder() {
        lateinit var view: View
        override fun bindView(itemView: View) {
            this.view = itemView
        }
    }
}