package dev.milo.form.handler;

import dev.milo.form.Milo;
import dev.waterdog.waterdogpe.network.PacketDirection;
import dev.waterdog.waterdogpe.network.protocol.handler.PluginPacketHandler;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;
import org.cloudburstmc.protocol.common.PacketSignal;

public class ModalFormResponseHandler implements PluginPacketHandler {
    private final Milo plugin;

    public ModalFormResponseHandler(Milo plugin) {
        this.plugin = plugin;
    }

    @Override
    public PacketSignal handlePacket(BedrockPacket packet, PacketDirection direction) {
        if(packet.getPacketType().equals(BedrockPacketType.MODAL_FORM_RESPONSE)) {
            return this.plugin.getFormManager().handleFormConsumer((ModalFormResponsePacket) packet);
        }
        return PacketSignal.UNHANDLED;
    }
}
