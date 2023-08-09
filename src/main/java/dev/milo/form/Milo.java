package dev.milo.form;

import dev.milo.form.updaters.ModalFormUpdater;
import dev.waterdog.waterdogpe.event.defaults.PlayerLoginEvent;
import dev.waterdog.waterdogpe.network.protocol.ProtocolCodecs;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.milo.form.handler.ModalFormResponseHandler;
import org.geysermc.cumulus.form.Form;

import java.util.UUID;

/**
 * Main interface for sending forms to WaterdogPE players
 */
public class Milo extends Plugin {

    private static Milo instance;

    private FormManager formManager;

    @Override
    public void onEnable() {
        instance = this;

        // Add codec handler for modal form request and response
        if(!Boolean.parseBoolean(System.getProperty("disableFastCodec", "false"))) {
            ProtocolCodecs.addUpdater(new ModalFormUpdater());
            getLogger().debug("Added ModalFormUpdater to protocol codec updater");
        }

        this.formManager = new FormManager(this);

        // Event Handler
        getProxy().getEventManager().subscribe(PlayerLoginEvent.class, this::onLogin);
    }

    public static Milo getInstance() {
        return instance;
    }

    /**
     * Send a {@link Form} to a player.
     *
     * @param player the player to send the form to
     * @param form the for to send
     */
    public void sendForm(ProxiedPlayer player, Form form) {
        getFormManager().sendFormToPlayer(player, form);
    }

    /**
     * Send a {@link Form} to a player.
     *
     * @param player the {@link UUID} of the player to send the form to
     * @param form the for to send
     */
    public void sendForm(UUID player, Form form) {
        ProxiedPlayer proxiedPlayer = getProxy().getPlayer(player);

        // Check if the player is null (you never know)
        if (proxiedPlayer == null) {
            return;
        }

        // Send form if all checks pass
        sendForm(proxiedPlayer, form);
    }

    public FormManager getFormManager() {
        return formManager;
    }

    /**
     * Add modal form response handler to player
     *
     * @param event the event being handled
     */
    private void onLogin(PlayerLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        // Add modal response handler
        player.getPluginPacketHandlers().add(new ModalFormResponseHandler(this));
    }
}