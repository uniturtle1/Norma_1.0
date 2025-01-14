package io.github.kjm015.norma

import com.jagrosh.jdautilities.command.CommandClientBuilder
import io.github.kjm015.norma.commands.*
import io.github.kjm015.norma.config.DiscordSettings
import io.github.kjm015.norma.listeners.ExodusListener
import io.github.kjm015.norma.listeners.InfluxListener
import io.github.kjm015.norma.listeners.MessageListener
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

/**
 * Alright, this is the important file.
 *
 * This is where the application gets ran, and where all of the commands are imported.
 * This is where all of the beans are created and where the settings for each instance of the
 * bot are put into place.
 *
 * If your code adds a command, DON'T FORGET TO ADD IT HERE BEFORE YOU PUSH IT!
 *
 * @author kjm015
 * @since 7/26/2018
 */
@SpringBootApplication
class KyleNewerApplication(
        private val settings: DiscordSettings,
        private val fetchCommand: FetchCommand,
        private val rageCommand: RageCommand,
        private val judgeCommand: JudgeCommand,
        private val simpCommand: SimpCommand,
        private val greetCommand: GreetCommand
) {

    @Bean
    fun jda(): JDA {
        // Import values from settings
        val token = settings.oauth
        val owner = settings.owner
        val prefix = settings.prefix

        // We could put this all on one line, but we won't. Don't do it.
        val builder = CommandClientBuilder()

        // Set the bot's prefix (what triggers commands)
        builder.setPrefix(prefix)

        // Add the commands to the running pool (Add yours here!)
        builder.addCommands(
                rageCommand,  //stays but reworked
                fetchCommand,
                judgeCommand,
                //quoteCommand  //stays, no rework may add onto
                simpCommand,
                greetCommand
        )

        // Set the owner of the bot (set in properties)
        builder.setOwnerId(owner)

        // Build the command listener
        val client = builder.build()

        // Build the bot with the given settings and listeners
        return JDABuilder.createDefault(token).addEventListeners(client, MessageListener(), ExodusListener(), InfluxListener()).build()
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(KyleNewerApplication::class.java, *args)
}
