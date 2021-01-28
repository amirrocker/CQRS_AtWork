package de.amirrocker.hadesGatekeeper.domain.policy

interface PolicyState {

    fun getPolicyStatus():PolicyStatus

    fun getCoverPeriod():DateRange

    fun getVersionPeriod():DateRange

    fun getCovers():Collection<PolicyCover>



}