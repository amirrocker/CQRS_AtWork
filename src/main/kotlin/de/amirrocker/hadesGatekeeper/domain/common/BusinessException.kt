package de.amirrocker.hadesGatekeeper.domain.common

import java.lang.RuntimeException

data class BusinessException(
        val msg:String
): RuntimeException(msg) {}