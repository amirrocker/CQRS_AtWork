package de.amirrocker.hadesGatekeeper.controllers

import de.amirrocker.hadesGatekeeper.commands.buyadditionalcover.BuyAdditionalCoverCommand
import de.amirrocker.hadesGatekeeper.cqs.Bus
import de.amirrocker.hadesGatekeeper.commands.createpolicy.CreatePolicyCommand
import de.amirrocker.hadesGatekeeper.commands.createpolicy.CreatePolicyResult
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequiredArgsConstructor
class PoliciesController(
        val bus: Bus
) {

    @PostMapping
    fun createPolicy(@RequestBody command: CreatePolicyCommand):ResponseEntity<CreatePolicyResult> =
            ResponseEntity.ok(bus.executeCommand(command))

    @PostMapping("/buyAdditionalCover")
    fun buyAdditionalCover(@RequestBody command: BuyAdditionalCoverCommand ):String {
        ResponseEntity.ok(bus.executeCommand(command))
        return "BuyAdditionalCover"
    }

}