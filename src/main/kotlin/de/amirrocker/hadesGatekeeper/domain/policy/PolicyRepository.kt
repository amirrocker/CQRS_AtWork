package de.amirrocker.hadesGatekeeper.domain.policy

import java.util.*

interface PolicyRepository {

    fun getById(number:UUID):Policy

    fun save(policy:Policy, expectedVersion:Int)

}