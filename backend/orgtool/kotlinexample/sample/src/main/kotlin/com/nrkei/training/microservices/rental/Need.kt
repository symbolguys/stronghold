/*
 * Copyright (c) 2022 by Fred George
 * @author Fred George  fredgeorge@acm.org
 * Licensed under the MIT License; see LICENSE file in root.
 */

package com.nrkei.training.microservices.rental

import com.nrkei.training.microservices.packet.Packet
import com.nrkei.training.microservices.rapid.RapidsConnection
import com.nrkei.training.microservices.rapid.rabbitmq.RabbitMqRapids

// Understands the requirement for advertising on a site
class Need {

    companion object {
        public const val COMMUNITY = "community"
        public const val OFFER_ENGINE_FAMILY = "offer_engine_family"
        public const val NEED = "need"
        public const val CAR_RENTAL_OFFER = "car_rental_offer"

        @JvmStatic
        fun main(args: Array<String>) {
            require(args.size == 2) { "Missing IP and Port arguments! The IP address of the Rapids (as a string), and the Port number of the Rapids (also as a string)." }
            RabbitMqRapids(ipAddress = args[0], port = args[1]).also { rapids ->
                publish(rapids)
            }
        }

        private fun publish(rapidsConnection: RapidsConnection) {
            try {
                Packet(
                    "need_id" to "11",
                    COMMUNITY to OFFER_ENGINE_FAMILY,
                    NEED to CAR_RENTAL_OFFER
                ).also { needPacket ->
                    while (true) {
                        println(String.format(" [<] %s", needPacket))
                        rapidsConnection.publish(needPacket)
                        Thread.sleep(20000)
                    }
                }
            } catch (e: Exception) {
                throw RuntimeException("Could not publish message:", e)
            }
        }
    }
}
