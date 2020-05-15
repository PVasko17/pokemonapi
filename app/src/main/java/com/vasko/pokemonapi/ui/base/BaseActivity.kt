package com.vasko.pokemonapi.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.vasko.pokemonapi.di.HasComponent
import com.vasko.pokemonapi.di.Injector
import com.vasko.pokemonapi.di.component.ActivityComponent
import com.vasko.pokemonapi.di.component.DaggerActivityComponent

abstract class BaseActivity : AppCompatActivity(), HasComponent<ActivityComponent> {
    override val component: ActivityComponent
        get() = activityComponent

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {

//        Initialising Dagger activity component
        activityComponent = DaggerActivityComponent.builder()
            .bindApplicationComponent(Injector.INSTANCE.applicationComponent)
            .bindActivity(this)
            .build()

        inject(activityComponent)

        super.onCreate(savedInstanceState)

        setContentView(findContentView())
    }

    /**
     * Returns ID of the layout associated with current activity
     *
     * @return Activity's layout resource file ID
     */
    @LayoutRes
    abstract fun findContentView(): Int

    /**
     * Inject activity component to activity module
     */
    abstract fun inject(activityComponent: ActivityComponent)
}