package com.vasko.pokemonapi.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vasko.pokemonapi.di.HasComponent
import com.vasko.pokemonapi.di.component.ActivityComponent
import com.vasko.pokemonapi.di.component.FragmentComponent
import com.vasko.pokemonapi.di.module.FragmentModule
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment(), HasComponent<FragmentComponent> {

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Call child implementation of ViewModel initialises
        bindViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(createLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Call child implementation of UI listeners
        bindData()
    }

    override fun onAttach(context: Context) {
//        Inject fragment module related to this fragment
        inject(component)

        super.onAttach(context)
        compositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {
//        Releasing JavaRX object on activity destroy
        compositeDisposable!!.dispose()
        super.onDestroy()
    }

    /**
     * Returns Dagger component object associated with this fragment
     */
    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((requireActivity() as HasComponent<C>).component)!!
    }

    /**
     * Getter for parent activity component along with the current fragment component
     *
     */
    override val component: FragmentComponent
        get() {
            return getComponent(ActivityComponent::class.java).plus(FragmentModule(this))
        }

//    Helper methods to reduce override calls

    /**
     * Populate fragment layout with specified resource by ID
     */
    protected abstract fun createLayout(): Int

    /**
     * Inject fragment component to the fragment module
     */
    protected abstract fun inject(fragmentComponent: FragmentComponent)

    /**
     * Init ViewModel related listeners and requests
     */
    protected abstract fun bindViewModel()

    /**
     * Init UI-related listeners
     */
    protected abstract fun bindData()
}