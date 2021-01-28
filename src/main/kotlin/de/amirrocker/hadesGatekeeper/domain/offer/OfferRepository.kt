package de.amirrocker.hadesGatekeeper.domain.offer

interface OfferRepository {

    fun withNumber(offerNumber:String):Offer

    fun all():List<Offer>

    fun add(offer: Offer)

}