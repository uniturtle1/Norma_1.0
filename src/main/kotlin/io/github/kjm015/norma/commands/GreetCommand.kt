package io.github.kjm015.norma.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import io.github.kjm015.norma.util.MessageGenerator
import org.jetbrains.kotlin.gradle.utils.`is`
import org.springframework.stereotype.Component
import java.lang.reflect.Member

@Component
class GreetCommand(private val generator: MessageGenerator) : Command() {
    init {
        this.name = "Greet"
        this.aliases = arrayOf("hello", "hi")
        this.help = "Norma will say hello"
        this.guildOnly = false
    }

    public override fun execute(event: CommandEvent){
        val target = event.author
        val twitchsub = event.member.roles
        var i = 0
        var subscriberCheck = false
        while (i != twitchsub.size){
            if (twitchsub[i].toString() == "R:Twitch Subscriber(842153350551175188)"){
                subscriberCheck = true
            }
            ++i
        }

        if(subscriberCheck){
            event.reply("Hey there ${target.asTag}, thanks for being a loyal and sexy subscriber.")
        }
        else{
            event.reply("Hello there ${target.asTag}, welcome to Mike's epic stream discord.")
        }
    }
}