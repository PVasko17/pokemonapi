package com.vasko.pokemonapi.di.module

import androidx.fragment.app.FragmentManager
import com.vasko.pokemonapi.di.PerActivity
import com.vasko.pokemonapi.ui.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    @PerActivity
    internal fun fragmentManager(baseActivity: BaseActivity): FragmentManager {
        return baseActivity.supportFragmentManager
    }
}