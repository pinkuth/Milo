# Milo
Milo is a [Cumulus](https://github.com/GeyserMC/Cumulus) implementation for the proxy server [WaterdogPE](https://waterdog.dev/).
This plugin will allow you to use the Cumulus API to create forms and send them to proxied players, no alterations needed!

## Quick Links

- [Cumulus Documentation](https://github.com/GeyserMC/Cumulus/wiki)

### Example
Sending a form to a player is simple!
```java
// Getting plugin instance from plugin manager
Milo milo = getProxy().getPluginManager().getPluginByName("Milo");
milo.sendForm(player, form);

// Getting plugin instance statically
Milo.getInstance().sendForm(player, form);
```

## Maven
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
        <version>${milo.version}</version>
    </dependency>
</dependencies>
```

## Issues & Contributing
There are no formats to follow just please make sure commits a readable and easy to understand.
If you find any issues please create an issue providing all information such as logs, code etc.

## Help
If you magically need any help with this then you can send a friend request to `pinkuth` on discord.