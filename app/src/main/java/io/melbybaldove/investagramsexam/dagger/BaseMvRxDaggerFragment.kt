package io.melbybaldove.investagramsexam.dagger

import android.content.Context
import android.support.v4.app.Fragment
import com.airbnb.mvrx.BaseMvRxFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
abstract class BaseMvRxDaggerFragment : BaseMvRxFragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }
}