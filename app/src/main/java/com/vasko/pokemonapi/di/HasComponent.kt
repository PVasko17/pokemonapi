package com.vasko.pokemonapi.di

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
interface HasComponent<out C> {
    val component: C
}
