package com.nrkei.training.microservices.solutionservice

import com.nrkei.training.microservices.filter.rules
import com.nrkei.training.microservices.packet.Packet
import com.nrkei.training.microservices.rapid.RapidsConnection
import com.nrkei.training.microservices.rapid.rabbitmq.RabbitMqRapids
import com.nrkei.training.microservices.river.River
import com.nrkei.training.microservices.river.Status

class BrokenService: River.SystemListener {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            require(args.size == 2) { "Missing IP and Port arguments! The IP address of the Rapids (as a string), and the Port number of the Rapids (also as a string)." }
            RabbitMqRapids(ipAddress = args[0], port = args[1]).register(BrokenService())
        }
    }

    // No rules for a Monitor. Options for filtering shown for documentation purpose
    override val rules = rules {
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

    override fun isStillAlive(connection: RapidsConnection): Boolean {
        return false
    }
}