package com.vasko.pokemonapi.di.component

import com.vasko.pokemonapi.ui.activity.MainActivity
import com.vasko.pokemonapi.di.PerActivity
import com.vasko.pokemonapi.di.module.ActivityModule
import com.vasko.pokemonapi.di.module.FragmentModule
import com.vasko.pokemonapi.ui.base.BaseActivity
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun activity(): BaseActivity

    operator fun plus(fragmentModule: FragmentModule): FragmentComponent

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        fun bindApplicationComponent(applicationComponent: ApplicationComponent): Builder

        @BindsInstance
        fun bindActivity(baseActivity: BaseActivity): Builder

        fun build(): ActivityComponent
    }
}