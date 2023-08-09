package dev.milo.form.updaters;

import dev.waterdog.waterdogpe.network.protocol.updaters.ProtocolCodecUpdater;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketDefinition;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;

public class ModalFormUpdater implements ProtocolCodecUpdater {

    @Override
    public BedrockCodec.Builder updateCodec(BedrockCodec.Builder builder, BedrockCodec baseCodec) {
        // Register modal form request packet
        BedrockPacketDefinition<ModalFormRequestPacket> modalRequestDefinition = baseCodec.getPacketDefinition(ModalFormRequestPacket.class);
        builder.registerPacket(ModalFormRequestPacket::new, modalRequestDefinition.getSerializer(), modalRequestDefinition.getId());

        // Register modal form response packet
        BedrockPacketDefinition<ModalFormResponsePacket> modalResponseDefinition = baseCodec.getPacketDefinition(ModalFormResponsePacket.class);
        builder.registerPacket(ModalFormResponsePacket::new, modalResponseDefinition.getSerializer(), modalResponseDefinition.getId());

        return builder;
    }
}
