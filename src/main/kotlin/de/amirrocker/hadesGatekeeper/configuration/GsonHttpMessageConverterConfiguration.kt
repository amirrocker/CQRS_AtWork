package de.amirrocker.hadesGatekeeper.configuration

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import de.amirrocker.hadesGatekeeper.domain.json.*
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.GsonHttpMessageConverter
import java.time.LocalDate
import java.util.*

@Configuration
@ConditionalOnClass(Gson::class)
@ConditionalOnMissingClass(value = ["com.fasterxml.jackson.core.JsonGenerator"])
@ConditionalOnBean(Gson::class)
class GsonHttpMessageConverterConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun gsonHttpMessageConverter(gson:Gson):GsonHttpMessageConverter {
        val converter = GsonHttpMessageConverter()
        converter.gson = GsonBuilder()
                .setDateFormat("dd.MM.yyyy")
                .registerTypeAdapter(Boolean::class.java, BooleanSerializer())
                .registerTypeAdapter(UUID::class.java, UUIDSerializer())
                .registerTypeAdapter(UUID::class.java, UUIDDeserializer())
                .registerTypeAdapter(LocalDate::class.java, DateSerializer())
                .registerTypeAdapter(LocalDate::class.java, DateDeserializer())
                .create()
        return converter
    }

}