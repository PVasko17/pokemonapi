package com.vasko.pokemonapi.ui.pokemon

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vasko.pokemonapi.core.Common
import com.vasko.pokemonapi.data.model.Pokemon
import com.vasko.pokemonapi.data.model.PokemonItem
import com.vasko.pokemonapi.data.model.PokemonList
import com.vasko.pokemonapi.data.repository.PokemonRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PokemonViewModel @Inject constructor() : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    /**
     * Variable to save latest response from list endpoint
     */
    var pokemonList = PokemonList()

    /**
     * Counter of total items received with paging
     */
    var totalLoadedItems = 0

    /**
     * All loaded items with paging
     */
    var loadedList = ArrayList<PokemonItem>()

    @Inject
    lateinit var pokemonRepository: PokemonRepository

    val listLiveData = MutableLiveData<PokemonList>()
    val detailsLiveData = MutableLiveData<Pokemon>()

    /**
     * Load pokemon list with paging
     *
     * @param offset Int - Paging offset
     * @param limit Int - Number of items per page
     */
    fun loadPokemonList(offset: Int = 0, limit: Int = Common.PER_PAGE) {
        pokemonRepository.getList(offset, limit)
            .subscribe(object : SingleObserver<PokemonList> {
                override fun onError(e: Throwable) {}
                override fun onSuccess(value: PokemonList) {
                    val prevList = pokemonList.results
                    pokemonList = value
                    loadedList.addAll(0, prevList)
                    totalLoadedItems += value.results.size
                    listLiveData.postValue(pokemonList)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }
            })
    }

    /**
     * Load pokemon list with paging using "next" parameter of the previous page response
     *
     * @param nextPage Url of the next page provided by API endpoint
     */
    fun loadPokemonList(nextPage: String) {
        val url = Uri.parse(nextPage)
//        Fetching GET parameters from the url
        val args: Set<String> = url.queryParameterNames
        val params = HashMap<String, Int>()
//        Collecting all available non-empty parameters
        args.forEach {
            if (url.getQueryParameter(it).isNullOrBlank().not())
                params[it] = url.getQueryParameter(it)!!.toInt()
        }
//        Calling base paging function
        loadPokemonList(params["offset"]!!, params["limit"]!!)
    }

    /**
     * Load pokemon details data by name
     *
     * @param name Pokemon name to fetch details
     */
    fun loadPokemonDetails(name: String) {
        pokemonRepository.getDetails(name)
            .subscribe(object : SingleObserver<Pokemon> {
                override fun onError(e: Throwable) { }
                override fun onSuccess(value: Pokemon) = detailsLiveData.postValue(value)
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }
            })
    }

    /**
     * Releasing JavaRX disposable object on ViewModel destroy
     */
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
