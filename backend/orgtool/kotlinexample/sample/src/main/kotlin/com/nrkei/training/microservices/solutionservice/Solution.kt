/*
 * Copyright (c) 2022 by Fred George
 * @author Fred George  fredgeorge@acm.org
 * Licensed under the MIT License; see LICENSE file in root.
 */

package com.nrkei.training.microservices.solutionservice

import com.nrkei.training.microservices.filter.rules
import com.nrkei.training.microservices.packet.Packet
import com.nrkei.training.microservices.rapid.RapidsConnection
import com.nrkei.training.microservices.rapid.rabbitmq.RabbitMqRapids
import com.nrkei.training.microservices.rental.Need
import com.nrkei.training.microservices.river.Status
import com.nrkei.training.microservices.river.River.SystemListener

// Understands the messages on an event bus
class Solution : SystemListener {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            require(args.size == 2) { "Missing IP and Port arguments! The IP address of the Rapids (as a string), and the Port number of the Rapids (also as a string)." }
            RabbitMqRapids(ipAddress = args[0], port = args[1]).register(Solution())
        }
    }

    // No rules for a Monitor. Options for filtering shown for documentation purpose
    override val rules = rules {
        require key "community" value "offer_engine_family"
        require key "need" value "car_rental_offer"
        forbid key "solution"
//        require key "a_key"                     // Reject packet unless it has key of a_key (not null, not empty String, not empty Array)
//        require key "a_key" value "a_value"     // Reject packet unless it has key:value pair a_key:a_value
//        forbid key "b_key"                      // Reject packet if it has key of b_key (unless b-key is null, empty String, or empty Array)
    }

    override fun packet(connection: RapidsConnection, packet: Packet, infoWarnings: Status) {
//        Sample possible Packet methods and purposes:
//
//        packet["key"]             // returns any? for any defined key
//        packet.get("key")         // synonym for packet["key"]
//        packet.isLacking("key")   // returns true if the key is missing, a null value, an empty string, or an empty array
//        packet.dateTime("key")    // returns a DateTime object (converting JSON string representation)
//        packet["key"] = value     // sets the key/value pair into the Packet
//        packet.set("key", value)  // synonym for packet["key"] = value
//
        var community = packet["community"]
        var need = packet["need"]
        if (community != null && need != null) {
            if(community.equals("offer_engine_family") && need.equals("car_rental_offer")){
                publish(connection,packet);
            }
        }
        println(String.format(" [*] %s", infoWarnings))
    }

    override fun rejectedPacket(connection: RapidsConnection, packet: Packet, problems: Status) {
        println(String.format(" [x] %s", problems))
    }

    override fun invalidFormat(connection: RapidsConnection, invalidString: String, problems: Status) {
        println(String.format(" [x] %s", problems))
    }

    override fun loopDetected(connection: RapidsConnection, packet: Packet, problems: Status) {
        println(String.format(" [x] %s", problems))
    }

    private fun publish(rapidsConnection: RapidsConnection, packet: Packet) {
        packet["solution"] = "car_rental_offer"
        packet["solution_profit"] = "20000"
        packet["solution_chance"] = "0.8" //0-1
        try {
            packet.also { needPacket ->
                while (true) {
                    println(String.format(" [<] %s", needPacket))
                    rapidsConnection.publish(needPacket)
                    Thread.sleep(7000)
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("Could not publish message:", e)
        }
    }
}