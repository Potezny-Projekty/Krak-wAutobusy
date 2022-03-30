package com.example.krakowautobusy.ui.map

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ArraySerializer
import kotlinx.serialization.builtins.ListSerializer

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


object VehicleListSerializer : KSerializer<AllVehicles> {
    private val listSerializer = AllVehicles.serializer().descriptor
    override val descriptor: SerialDescriptor = listSerializer


    override fun deserialize(decoder: Decoder): AllVehicles {
        return decoder.decodeSerializableValue(AllVehicles.serializer())

    }

    override fun serialize(encoder: Encoder, value: AllVehicles) {

    }

}


