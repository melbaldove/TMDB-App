package io.melbybaldove.investagramsexam.ui.home.moviedetail

import android.graphics.Bitmap
import android.os.CountDownTimer
import android.support.v4.graphics.ColorUtils
import android.support.v7.graphics.Palette
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import io.melbybaldove.investagramsexam.GlideApp
import io.melbybaldove.investagramsexam.R
import io.melbybaldove.presentation.movie.detail.model.MovieDetailModel
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import kotlinx.android.synthetic.main.layout_movie_overview.view.*

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
@EpoxyModelClass(layout = R.layout.layout_movie_overview)
abstract class MovieOverviewEpoxyModel : EpoxyModelWithHolder<MovieOverviewEpoxyModel.Holder>() {
    @EpoxyAttribute
    lateinit var movieDetailModel: MovieDetailModel
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: MovieDetailEpoxyController.Listener

    override fun bind(holder: Holder) {
        holder.view.apply {
            layout_movie_overview_title.text = movieDetailModel.movieModel.title
            loadBackdrop()
            loadPoster()
            layout_movie_overview_overview.text = movieDetailModel.movieModel.desc
            layout_movie_overview_cast.text = movieDetailModel.starring
            layout_movie_overview_director.text = movieDetailModel.director
            layout_movie_overview_rating.text = movieDetailModel.movieModel.rating
            layout_movie_overview_release_date.text = movieDetailModel.movieModel.date
            layout_movie_overview_totalRates.text = movieDetailModel.movieModel.totalRates
            layout_movie_overview_ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
                if (fl > 0) {
                    listener.rate(movieDetailModel.movieModel, fl)
                    val rating = if (fl == 10f) "10" else fl.toString()
                    showRated(rating)
                } else {
                    listener.deleteRating(movieDetailModel.movieModel)
                    showUnrated()
                }
                object : CountDownTimer(120, 1000) {
                    override fun onFinish() {
                        layout_movie_overview_rating_layout.visibility = View.GONE
                    }

                    override fun onTick(p0: Long) = Unit
                }.start()
            }
            holder.view.setOnTouchListener { _, _ ->
                layout_movie_overview_rating_layout.visibility = View.GONE
                false
            }
            if (movieDetailModel.myRating.toFloat() > 0) {
                showRated(movieDetailModel.myRating)
                layout_movie_overview_ratingBar.rating = movieDetailModel.myRating.toFloat()
            } else {
                showUnrated()
            }
            layout_movie_overview_myRating_icon.setOnClickListener {
                layout_movie_overview_rating_layout.visibility = if (layout_movie_overview_rating_layout.visibility == View.VISIBLE) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
        }
    }

    private fun View.showUnrated() {
        layout_movie_overview_tv2.visibility = View.VISIBLE
        layout_movie_overview_tv3.visibility = View.INVISIBLE
        layout_movie_overview_myRating.visibility = View.INVISIBLE
        layout_movie_overview_myRating_icon.setImageResource(R.drawable.ic_star_unrated)
    }

    private fun View.showRated(myRating: String) {
        layout_movie_overview_tv2.visibility = View.INVISIBLE
        layout_movie_overview_tv3.visibility = View.VISIBLE
        layout_movie_overview_myRating.visibility = View.VISIBLE
        layout_movie_overview_myRating.text = myRating
        layout_movie_overview_myRating_icon.setImageResource(R.drawable.ic_star_rated)
    }

    private fun View.loadBackdrop() {
        GlideApp.with(this)
                .asBitmap()
                .load(movieDetailModel.backdropPath)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val palette = Palette.from(resource).generate()
                        val muted = palette.darkMutedSwatch?.rgb
                        GlideApp.with(this@loadBackdrop)
                                .load(resource)
                                // tint dat nice background
                                .apply(bitmapTransform(MultiTransformation(
                                        ColorFilterTransformation(ColorUtils.setAlphaComponent(muted!!, 240)),
                                        CenterCrop())))
                                .into(layout_movie_overview_backdrop)
                    }
                })
    }

    private fun View.loadPoster() {
        GlideApp.with(this)
                .load(movieDetailModel.movieModel.poster.toString())
                .into(layout_movie_overview_poster)
    }

    class Holder : EpoxyHolder() {
        lateinit var view: View
        override fun bindView(itemView: View) {
            view = itemView
        }
    }
}