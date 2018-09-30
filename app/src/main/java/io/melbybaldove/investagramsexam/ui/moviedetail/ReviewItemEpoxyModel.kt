package io.melbybaldove.investagramsexam.ui.moviedetail

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.presentation.movie.detail.model.ReviewModel
import kotlinx.android.synthetic.main.layout_review_item.view.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@EpoxyModelClass(layout = R.layout.layout_review_item)
abstract class ReviewItemEpoxyModel : EpoxyModelWithHolder<ReviewItemEpoxyModel.Holder>() {
    @EpoxyAttribute
    lateinit var review: ReviewModel

    override fun bind(holder: Holder) {
        holder.view.apply {
            layout_review_item_tv.text = "A review by ${review.author}"
            layout_review_item_content.text = review.content
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var view: View
        override fun bindView(itemView: View) {
            view = itemView
        }
    }
}