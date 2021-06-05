package discord.apple.foundation;

import discord.apple.foundation.discord.DiscordBot;

import javax.security.auth.login.LoginException;

public class FoundationMain {
    public static void main(String[] args) throws LoginException {
        new DiscordBot();
        System.out.println("CreditBot started");
    }
}
