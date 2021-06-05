package discord.apple.foundation.discord.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public enum Command {
    INACTIVITY(new CommandPing()),
    HELP(new CommandHelp());

    private final CommandMessageEater command;

    Command(CommandMessageEater command) {
        this.command = command;
    }


    public boolean isCommand(String command) {
        return this.command.isCommand(command);
    }

    public static void dealWithEvent(MessageReceivedEvent event) {
        String messageContent = event.getMessage().getContentStripped().toLowerCase();
        for (Command command : values()) {
            if (command.isCommand(messageContent)) {
                command.run(event);
                break;
            }
        }
    }

    public void run(MessageReceivedEvent event) {
        command.eat(event);
    }

    public String getUsageMessage() {
        return command.getUsageMessage();
    }
}
