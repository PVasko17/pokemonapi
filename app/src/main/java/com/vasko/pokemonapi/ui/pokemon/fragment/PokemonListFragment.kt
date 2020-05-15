package com.vasko.pokemonapi.ui.pokemon.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.vasko.pokemonapi.R
import com.vasko.pokemonapi.data.model.Pokemon
import com.vasko.pokemonapi.data.model.PokemonItem
import com.vasko.pokemonapi.di.component.FragmentComponent
import com.vasko.pokemonapi.ui.base.BaseFragment
import com.vasko.pokemonapi.ui.base.adapter.OnRecycleItemClick
import com.vasko.pokemonapi.ui.pokemon.PokemonViewModel
import com.vasko.pokemonapi.ui.pokemon.adapter.PokemonListAdapter
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class PokemonListFragment : BaseFragment(), OnRecycleItemClick<Pokemon>,
    SwipeRefreshLayout.OnRefreshListener {
    override fun createLayout(): Int = R.layout.fragment_pokemon_list

    override fun inject(fragmentComponent: FragmentComponent) = component.inject(this)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val pokemonViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PokemonViewModel::class.java)
    }

    /**
     * Adapter to display pokemon list data
     */
    private val listAdapter by lazy { PokemonListAdapter(this) }

    override fun bindViewModel() {
//        Pokemon list callback
        pokemonViewModel.listLiveData.observe(this, Observer {
//            Hiding progress indicator
            swipeRefresh.isRefreshing = false

            /*
                As pokemon listing endpoint doesn't provide more information except name,
                it's required to load such data using "details" endpoint.

                Since system can't handle many background requests at the same time,
                neither on emulator, nor on real device,
                I had to use 250ms delay, so all background threads started properly.
             */
            it.results.forEachIndexed { index, pokemonItem ->
                GlobalScope.launch {
                    delay(index.toLong() * 250)
                    pokemonViewModel.loadPokemonDetails(pokemonItem.name)
                }
            }
        })

//        Single pokemon details callback
        pokemonViewModel.detailsLiveData.observe(this, Observer {
            listAdapter.addItem(it)
        })

//        Requesting first page of the list
        pokemonViewModel.loadPokemonList()
    }

    override fun bindData() {
//        RecyclerView initialisation
        val gridLayoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.list_columns))
        recycleViewPokemon.apply {
            layoutManager = gridLayoutManager
            adapter = listAdapter
        }

//        Scroll listener to handle paging requests
        recycleViewPokemon?.viewTreeObserver?.addOnScrollChangedListener {
            if (recycleViewPokemon != null) {
//                Check last visible item position equal to the last array index
//                +1 operation required as indexing starts on zero
                if (pokemonViewModel.totalLoadedItems == gridLayoutManager.findLastCompletelyVisibleItemPosition() + 1) {
//                    Display progress indicator
                    swipeRefresh.isRefreshing = true
//                    Next page request
                    pokemonViewModel.loadPokemonList(pokemonViewModel.pokemonList.next!!)
                }
            }
        }

//        Appending action listener to SwipeRefreshLayout
        swipeRefresh.setOnRefreshListener(this)
    }

    /**
     * List adapter click handler
     */
    override fun onClick(t: Pokemon, view: View) {
        val action = PokemonListFragmentDirections.destListToDetails(t)
        findNavController().navigate(action)
    }

    /**
     * Callback function of SwipeRefreshLayout
     */
    override fun onRefresh() {
        listAdapter.items = null
        pokemonViewModel.loadPokemonList()
    }
}
