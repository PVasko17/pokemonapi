package com.vasko.pokemonapi.di.module

import androidx.fragment.app.FragmentManager
import com.vasko.pokemonapi.di.PerFragment
import com.vasko.pokemonapi.ui.base.BaseFragment
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FragmentModule(private val baseFragment: BaseFragment) {
    @Provides
    @PerFragment
    internal fun provideBaseFragment(): BaseFragment {
        return baseFragment
    }

    @Provides
    @Named("child_manager")
    internal fun fragmentManager(baseFragment: BaseFragment): FragmentManager {
        return baseFragment.childFragmentManager
    }
}