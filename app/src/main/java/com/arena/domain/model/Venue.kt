package com.arena.domain.model

data class Venue(val name: String, val location: String, val rating: Double, val priceRange: String, val image: Int, val tags: List<String>)
