package de.amirrocker.hadesGatekeeper.domain.json

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDate
import java.util.*

class BooleanSerializer : JsonSerializer<Boolean> {

    override fun serialize(aBoolean: Boolean, type: Type?, context: JsonSerializationContext?): JsonElement {
        return if( aBoolean ) {
                JsonPrimitive(1)
            }else{
                JsonPrimitive(0)
            }
    }
}


class UUIDSerializer : JsonSerializer<UUID> {
    override fun serialize(uuid: UUID?, type: Type?, context: JsonSerializationContext?): JsonElement {
        val json = uuid?.let {
            JsonPrimitive(it.toString())
        }
        return json as JsonElement
    }
}

class UUIDDeserializer : JsonDeserializer<UUID> {
    override fun deserialize(jsonElement: JsonElement?, type: Type?, context: JsonDeserializationContext?): UUID {

        val uuid = jsonElement?.let {
            UUID.fromString(it.asString)
        }
        return uuid as UUID

    }
}

class DateSerializer : JsonSerializer<LocalDate> {
    override fun serialize(localDate: LocalDate?, type: Type?, p2: JsonSerializationContext?): JsonElement {
        val json = localDate?.let {
            JsonPrimitive(it.toString())
        }
        return json as JsonElement
    }
}

class DateDeserializer : JsonDeserializer<LocalDate> {
    override fun deserialize(jsonElement: JsonElement?, type: Type?, context: JsonDeserializationContext?): LocalDate {
        val localDate = jsonElement?.let {
            val array = it.asString.split(".")
            LocalDate.of(array[2].toInt(), array[1].toInt(), array[0].toInt())
        }
        return localDate as LocalDate
    }
}

