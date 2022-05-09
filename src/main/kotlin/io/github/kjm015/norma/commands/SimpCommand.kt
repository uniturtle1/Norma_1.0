package io.github.kjm015.norma.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import io.github.kjm015.norma.util.MessageGenerator
import org.springframework.stereotype.Component

@Component
class SimpCommand(private val generator: MessageGenerator) : Command() {

    init {
        this.name = "simp"
        this.aliases = arrayOf("simp.", "love me.")
        this.help = "Norma will simp kind of"
        this.guildOnly = false
    }

    public override fun execute(event: CommandEvent){
        val target = event.author
        if (target.asTag == "mike uniturtle#5609"){
            event.reply(generator.generateJudgementMike(target))
        }else{
            event.reply(generator.generateJudgement(target))
        }
    }


}