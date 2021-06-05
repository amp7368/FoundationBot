package discord.apple.foundation.discord.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandMessageEater {
    void eat(MessageReceivedEvent event);

    boolean isCommand(String command);

    String getUsageMessage();
}
