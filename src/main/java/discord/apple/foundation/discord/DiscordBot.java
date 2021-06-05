package discord.apple.foundation.discord;

import discord.apple.foundation.FoundationMain;
import discord.apple.foundation.discord.command.Command;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DiscordBot extends ListenerAdapter {
    private static String discordToken = null;
    private final JDA client;
    public static final String PREFIX = "f!";

    static {
        List<String> list = Arrays.asList(FoundationMain.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("/"));
        String BOT_TOKEN_FILE_PATH = String.join("/", list.subList(0, list.size() - 1)) + "/config/discordToken.txt";

        File file = new File(BOT_TOKEN_FILE_PATH);
        if (!file.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } catch (IOException ignored) {
            }
            System.err.println("Please fill in the token for the discord bot in '" + BOT_TOKEN_FILE_PATH + "'");
            System.exit(1);
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                discordToken = reader.readLine();
                reader.close();
            } catch (IOException e) {
                System.err.println("Please fill in the token for the discord bot in '" + BOT_TOKEN_FILE_PATH + "'");
                System.exit(1);
            }
        }
    }


    public DiscordBot() throws LoginException {
        Collection<GatewayIntent> intents = new ArrayList<>(Arrays.asList(GatewayIntent.values()));
        intents.remove(GatewayIntent.GUILD_PRESENCES);
        JDABuilder builder = JDABuilder.create(intents);
        builder.addEventListeners(this);
        builder.setToken(discordToken);
        client = builder.build();
        client.getPresence().setPresence(Activity.playing(Command.HELP.getUsageMessage()), false);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (event.getChannelType() != ChannelType.TEXT) {
            return;
        }
        // the author is not a bot
        // deal with the different commands
        Command.dealWithEvent(event);

//        SqlDiscordCache.cache(event);
    }
}
