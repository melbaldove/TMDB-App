package io.melbybaldove.investagramsexam.ui.home.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import io.melbybaldove.investagramsexam.GlideApp
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.presentation.movie.model.MovieModel
import kotlinx.android.synthetic.main.layout_movie_item.view.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */

@EpoxyModelClass(layout = R.layout.layout_movie_item)
abstract class MovieEpoxyModel : EpoxyModelWithHolder<MovieEpoxyModel.Holder>() {
    @EpoxyAttribute
    lateinit var movie: MovieModel
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: (MovieModel) -> Unit

    override fun bind(holder: Holder) {
        holder.movieItemView.apply {
            layout_movie_item_title.text = movie.title
            layout_movie_item_date.text = movie.date
            layout_movie_item_desc.text = movie.desc
            layout_movie_item_more_info.setOnClickListener {
                listener(movie)
            }
            GlideApp.with(this)
                    .load(movie.poster.toString())
                    .centerCrop()
                    .into(layout_movie_item_poster)
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var movieItemView: View
        override fun bindView(itemView: View) {
            movieItemView = itemView
        }
    }
}