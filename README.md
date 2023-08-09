# Milo
Milo is a [Cumulus](https://github.com/GeyserMC/Cumulus) implementation for the proxy server [WaterdogPE](https://waterdog.dev/).
This plugin will allow you to use the Cumulus API to create forms and send them to proxied players, no alterations needed!

## Quick Links

- [Cumulus Documentation](https://github.com/GeyserMC/Cumulus/wiki)

## Usage
Sending a form to a player is simple!
```xml
<repositories>
    <repository>
        <id>skulkstudio-main</id>
        <url>https://repo.skulkstudio.com/main</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>dev.milo</groupId>
        <artifactId>form</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```
### Simple
```java
Milo.getInstance().sendForm(player, form);
```

## Detailed
*For those who don't know how to add it without a detailed example*

```java
package dev.milo.form.examples;

import dev.milo.form.Milo;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.plugin.Plugin;
import org.geysermc.cumulus.form.SimpleForm;

/**
 * Example plugin class
 */
public class ExamplePlugin extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getCommandMap().registerCommand(new ExampleCommand());
    }

    /**
     * Example command class
     *
     * This would normally be in another file and not nested in the
     * main plugin class.
     */
    public static class ExampleCommand extends Command {

        public ExampleCommand() {
            // /form
            super("form");
        }

        @Override
        public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
            // Create form
            SimpleForm.Builder simpleForm = SimpleForm.builder();
            // add elements to form...

            // send form to player
            Milo.getInstance().sendForm((ProxiedPlayer) commandSender, simpleForm);
            return true;
        }
    }
}
```

## Issues & Contributing
There are no formats to follow just please make sure commits a readable and easy to understand.
If you find any issues please create an issue providing all information such as logs, code etc.

## Help
If you magically need any help with this then you can send a friend request to `pinkuth` on discord.