package com.nrkei.training.microservices.solutionselection

import com.nrkei.training.microservices.filter.rules
import com.nrkei.training.microservices.packet.Packet
import com.nrkei.training.microservices.rapid.RapidsConnection
import com.nrkei.training.microservices.rapid.rabbitmq.RabbitMqRapids
import com.nrkei.training.microservices.river.River.SystemListener
import com.nrkei.training.microservices.river.Status
import javax.print.attribute.IntegerSyntax


class SolutionSelector: SystemListener {

    private val offers = mutableMapOf<String,String>();
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            require(args.size == 2) { "Missing IP and Port arguments! The IP address of the Rapids (as a string), and the Port number of the Rapids (also as a string)." }
            RabbitMqRapids(ipAddress = args[0], port = args[1]).register(SolutionSelector())
        }
    }

    override fun invalidFormat(connection: RapidsConnection, invalidString: String, problems: Status) {
        TODO("Not yet implemented")
    }

    override fun loopDetected(connection: RapidsConnection, packet: Packet, problems: Status) {
        TODO("Not yet implemented")
    }

    override val rules = rules {
//        require key "community" value "offer_engine_family"
//        require key "need" value "car_rental_offer"
//        require key "solution" value "car_rental_offer"
//        require key "a_key"                     // Reject packet unless it has key of a_key (not null, not empty String, not empty Array)
//        require key "a_key" value "a_value"     // Reject packet unless it has key:value pair a_key:a_value
//        forbid key "b_key"                      // Reject packet if it has key of b_key (unless b-key is null, empty String, or empty Array)
    }

    override fun packet(connection: RapidsConnection, packet: Packet, infoWarnings: Status) {
        TODO("Not yet implemented")
        setBestOffer(packet)
    }

    private fun setBestOffer(packet: Packet){
        // solution_chance solution_profit

        var total = Integer.parseInt(packet["solution_chance"].toString()) * Integer.parseInt(packet["solution_profit"].toString())
        if(!offers.contains("car_rental_offer")){
            offers["car_rental_offer"] = total.toString()
            println(String.format(" [x] %s",  total.toString()))
        }

        if(Integer.parseInt(offers["car_rental_offer"])<total){
            offers["car_rental_offer"] = total.toString();
            println(String.format(" [x] %s",  total.toString()))
        }
    }

//    private fun publish(rapidsConnection: RapidsConnection, packet: Packet) {
//        packet["value"] = "car_offer"
//        packet["value"] = "30000"
//        packet["offer_id"] = "car_dealer_2"
//        try {
//            packet.also { needPacket ->
//                while (true) {
//                    println(String.format(" [<] %s", needPacket))
//                    rapidsConnection.publish(needPacket)
//                    Thread.sleep(5000)
//                }
//            }
//        } catch (e: Exception) {
//            throw RuntimeException("Could not publish message:", e)
//        }
//    }
}