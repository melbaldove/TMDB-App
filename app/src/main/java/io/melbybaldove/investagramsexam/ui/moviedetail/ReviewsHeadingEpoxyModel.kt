package io.melbybaldove.investagramsexam.ui.moviedetail

import android.support.annotation.StringRes
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import io.melbybaldove.investagramsexam.R
import kotlinx.android.synthetic.main.layout_reviews_heading.view.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@EpoxyModelClass(layout = R.layout.layout_reviews_heading)
abstract class ReviewsHeadingEpoxyModel : EpoxyModelWithHolder<ReviewsHeadingEpoxyModel.Holder>() {
    @EpoxyAttribute
    @StringRes
    lateinit var headingText: String

    override fun bind(holder: Holder) {
        holder.view.layout_reviews_heading_text.text = headingText
    }

    class Holder : EpoxyHolder() {
        lateinit var view: View
        override fun bindView(itemView: View) {
            view = itemView
        }
    }
}