package de.amirrocker.hadesGatekeeper.cqs

import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import de.amirrocker.hadesGatekeeper.commands.buyadditionalcover.BuyAdditionalCoverCommand
import de.amirrocker.hadesGatekeeper.domain.json.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.isA
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.MathContext
import java.time.LocalDate
import java.time.Period
import java.util.*

class TestRegistry {

    @Test
    internal fun `test a command can successfully be registered in registry happy path `() {

        val cmd = BuyAdditionalCoverCommand()
        cmd.policyId = UUID.fromString("1dd25493-9c3b-4f3f-8c26-b78fd578646f")
        cmd.effectiveDateOfChange = LocalDate.of(2020, 3, 12)
        cmd.newCoverPriceUnit = Period.of(2020, 3, 4)

        assertTrue(cmd.policyId.toString() == "1dd25493-9c3b-4f3f-8c26-b78fd578646f")

    }

    val string = """ 
        {
        		policyId:1dd25493-9c3b-4f3f-8c26-b78fd578646f,
            	effectiveDateOfChange:12.12.2020,
            	newCoverCode: Value,
            	newCoverPrice: 123.32, 
                newCoverPriceUnit: {
                    years : 2019,
                    months : 11,
                    days : 4
                }
        }
    """.trimIndent()


    /**
     * remove this asap
     */
    @Test
    internal fun `test json gson convert`() {

        val gson = GsonBuilder()
                .setDateFormat("dd.MM.yyyy")
                .registerTypeAdapter(Boolean::class.java, BooleanSerializer())
                .registerTypeAdapter(UUID::class.java, UUIDSerializer())
                .registerTypeAdapter(UUID::class.java, UUIDDeserializer())
                .registerTypeAdapter(LocalDate::class.java, DateSerializer())
                .registerTypeAdapter(LocalDate::class.java, DateDeserializer())
                .create()

        val cmd = gson.fromJson<BuyAdditionalCoverCommand>(string.trimMargin(), BuyAdditionalCoverCommand::class.java)

        assertThat(cmd.effectiveDateOfChange, isA(LocalDate::class.java))
        assertTrue(cmd.effectiveDateOfChange.isEqual(LocalDate.of(2020, 12, 12)))
        assertTrue(cmd.newCoverPriceUnit.equals(Period.of(2019, 11, 4)))
        assertTrue(cmd.newCoverPrice.toInt().equals(123))
        assertTrue(cmd.newCoverCode.equals("Value"))


    }
}