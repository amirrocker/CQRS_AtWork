package de.amirrocker.hadesGatekeeper.db

import de.amirrocker.hadesGatekeeper.domain.offer.Offer
import de.amirrocker.hadesGatekeeper.domain.offer.OfferRepository
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryOfferRepository : OfferRepository {

    val offers:ConcurrentHashMap<String, Offer> = ConcurrentHashMap<String, Offer>()

    override fun withNumber(offerNumber: String): Offer =
            offers[offerNumber] ?: error("No valid Offer found for offernumber: $offerNumber ")

    override fun all(): List<Offer> =
            ArrayList(offers.values)


    override fun add(offer: Offer) {
        offers.put(offer.number, offer)
    }

}

