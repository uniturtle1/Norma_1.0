package io.github.kjm015.newkyle.service

import io.github.kjm015.newkyle.config.RedditSettings
import masecla.reddit4j.client.Reddit4J
import masecla.reddit4j.client.UserAgentBuilder
import masecla.reddit4j.objects.subreddit.RedditSubreddit
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class RedditService(redditSettings: RedditSettings) {

    private val redditClient: Reddit4J

    init {
        val client = Reddit4J.rateLimited()
            .setClientId(redditSettings.appId).setClientSecret(redditSettings.secret)
            .setUserAgent(UserAgentBuilder().appname("New Kyle").author("kjm015").version("1.0"))
        client.userlessConnect()
        redditClient = client
    }

    fun getSubreddit(subredditName: String): RedditSubreddit {
        return redditClient.getSubreddit(subredditName)
    }
}

fun RedditSubreddit.customDescription(): String {
    return """
        ${this.displayNamePrefixed} - ${this.publicDescription}
        ```
${this.subscribers} subscribers, ${this.activeUserCount} active
Created on ${Date.from(Instant.ofEpochSecond(this.createdUTC))}
        ```
https://reddit.com${this.url}
    """.trim()
}
