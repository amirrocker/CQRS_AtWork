package de.amirrocker.hadesGatekeeper.domain.policy

import java.time.LocalDate

class PolicyVersions(
        val versions:List<PolicyVersion>
) {

   fun effectiveAtDate(effectiveDate:LocalDate) =
           versions.parallelStream()
                   .filter {
                       it.isEffectiveOn(effectiveDate)
                   }
                   .max(Comparator.comparingInt {
                       it.versionNumber
                   })

    fun size() = versions.size

    fun maxVersionNumber() =
            versions.parallelStream()
                    .max(Comparator.comparingInt(PolicyVersion::versionNumber).reversed())
                    .get()
                    .versionNumber

    fun withNumber(versionNumber:Int) =
            versions.parallelStream()
                    .filter {
                        it.versionNumber == versionNumber
                    }
                    .findFirst()

    fun last():PolicyVersion =
            versions.parallelStream()
                    .max(Comparator.comparingInt(PolicyVersion::versionNumber))
                    .get()
}

