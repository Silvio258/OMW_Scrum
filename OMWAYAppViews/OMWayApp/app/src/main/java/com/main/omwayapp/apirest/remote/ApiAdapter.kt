package com.main.omwayapp.apirest.remote

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

object ApiAdapter {

    private lateinit var applicationContext: Context

    fun initialize(context: Context) {
        applicationContext = context.applicationContext
    }
    val BASE_URL = "http://3.133.121.91:8181"

    @RequiresApi(Build.VERSION_CODES.O)
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .registerTypeAdapter(Time::class.java, TimeDeserializer())
        .registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer())
        //.registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
        .create()


    @RequiresApi(Build.VERSION_CODES.O)
    fun getInstance(): Retrofit {
        // Create an instance of the logging interceptor
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Create an OkHttpClient and add the logging interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)  // Set the OkHttpClient with the logging interceptor
            .build()
    }

    class DateDeserializer : JsonDeserializer<Date> {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Date {
            val dateString = json?.asString
            return dateFormat.parse(dateString)
        }
    }

    // Custom Gson deserializer for the time format
    class TimeDeserializer : JsonDeserializer<Time> {
        private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Time {
            val timeString = json?.asString
            val date = timeFormat.parse("$timeString")
            return Time(date.time)
        }
    }
    class LocalDateDeserializer : JsonDeserializer<LocalDate> {
        @RequiresApi(Build.VERSION_CODES.O)
        private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        @RequiresApi(Build.VERSION_CODES.O)
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): LocalDate {
            val dateString = json?.asString
            return LocalDate.parse(dateString, dateFormatter)
        }
    }
    /*
    class LocalDateTypeAdapter : TypeAdapter<LocalDate>() {
        @RequiresApi(Build.VERSION_CODES.O)
        private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(IOException::class)
        override fun write(out: JsonWriter, value: LocalDate?) {
            if (value == null) {
                out.nullValue()
            } else {
                out.value(formatter.format(value))
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(IOException::class)
        override fun read(`in`: JsonReader): LocalDate? {
            if (`in`.peek() == JsonToken.NULL) {
                `in`.nextNull()
                return null
            }
            val dateString = `in`.nextString()
            return LocalDate.parse(dateString, formatter)
        }
    }*/
}