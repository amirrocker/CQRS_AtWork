package de.amirrocker.hadesGatekeeper.db

import de.amirrocker.hadesGatekeeper.domain.product.Product
import de.amirrocker.hadesGatekeeper.domain.product.ProductRepository
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryProductRepository : ProductRepository {

    private val products = ConcurrentHashMap<String, Product>()

    override fun add(product: Product) {
        products.put(product.code, product)
    }

    override fun withCode(code: String): Product =
            products[code]!!

    override fun all(): List<Product> =
            ArrayList(products.values)

}