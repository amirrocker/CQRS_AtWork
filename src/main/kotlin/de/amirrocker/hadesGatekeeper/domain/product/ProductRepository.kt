package de.amirrocker.hadesGatekeeper.domain.product

interface ProductRepository {

    fun add(product:Product)

    fun withCode(code:String):Product

    fun all():List<Product>

}