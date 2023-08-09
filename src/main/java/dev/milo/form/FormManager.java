package dev.milo.form;

import dev.waterdog.waterdogpe.network.protocol.Signals;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import dev.milo.form.utils.FormUtils;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.geysermc.cumulus.form.Form;

import java.util.concurrent.atomic.AtomicInteger;

public class FormManager {

    private final Milo plugin;

    private final Int2ObjectMap<Form> storedForms = new Int2ObjectOpenHashMap<>();
    private final AtomicInteger nextFormId = new AtomicInteger(0);

    public FormManager(Milo plugin) {
        this.plugin = plugin;
    }

    /**
     * Sends a modal form to a player
     *
     * @param player the player to send the modal form to
     * @param form the form to send to the player
     */
    public void sendFormToPlayer(ProxiedPlayer player, Form form) {
        int formId = getNextFormId();
        String formData = FormUtils.createFormData(form);

        // Store the form that was sent
        storedForms.put(formId, form);

        // Create packet
        ModalFormRequestPacket packet = new ModalFormRequestPacket();
        packet.setFormId(formId);
        packet.setFormData(formData);

        // Sends packet to player
        player.sendPacket(packet);
    }

    public PacketSignal handleFormConsumer(ModalFormResponsePacket packet) {
        String formData = packet.getFormData();
        int formId = packet.getFormId();

        // Remove handled form
        Form removedForm = storedForms.remove(formId);

        // Handle response consumer
        try {
           return Signals.fromBoolean(FormUtils.callResponseConsumer(removedForm, formData));
        } catch (Exception exception) {
            this.plugin.getLogger().warn("Error while handling form response: {}", exception.getMessage());
            return PacketSignal.UNHANDLED;
        }
    }

    /**
     * All the forms that have been sent to players which haven't received
     * a response.
     *
     * @return
     */
    public Int2ObjectMap<Form> getStoredForms() {
        return storedForms;
    }

    /**
     * Gets the next form id
     *
     * @return id of the next form
     */
    protected Integer getNextFormId() {
        // signed bit is used to check if the form is from a proxy or a server
        return nextFormId.getAndUpdate(
                (number) -> number == Short.MAX_VALUE ? 0 : number + 1);
    }
}
