package de.amirrocker.hadesGatekeeper.init

import de.amirrocker.hadesGatekeeper.domain.common.Car

class InitCarsBuilder {
    companion object {
        fun oldMercedesBenz() =
                Car(
                        "Mercedes Benz",
                        "vanityPlate4Real",
                        1924
                )


    }
}
