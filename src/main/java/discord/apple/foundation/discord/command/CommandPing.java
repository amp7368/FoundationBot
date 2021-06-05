package discord.apple.foundation.discord.command;

import discord.apple.foundation.discord.DiscordBot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Locale;

public class CommandPing implements CommandMessageEater {

    @Override
    public void eat(MessageReceivedEvent event) {
        event.getChannel().sendMessage("pong").queue();
    }

    @Override
    public boolean isCommand(String command) {
        return command.toLowerCase(Locale.ROOT).startsWith(DiscordBot.PREFIX + "ping");
    }

    @Override
    public String getUsageMessage() {
        return DiscordBot.PREFIX + "ping";
    }

}
