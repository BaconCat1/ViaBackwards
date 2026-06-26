/*
 * This file is part of ViaBackwards - https://github.com/ViaVersion/ViaBackwards
 * Copyright (C) 2016-2026 ViaVersion and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.viaversion.viabackwards.protocol.v26_2to26_1.rewriter;

import com.viaversion.nbt.tag.CompoundTag;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.minecraft.blockentity.BlockEntity;
import com.viaversion.viaversion.api.protocol.Protocol;
import com.viaversion.viaversion.api.protocol.packet.ClientboundPacketType;
import com.viaversion.viaversion.api.rewriter.block.BlockRewriter1_21_5;
import com.viaversion.viaversion.rewriter.BlockRewriter;

public final class BlockRewriter26_2<C extends ClientboundPacketType> extends BlockRewriter1_21_5<C> {

    public BlockRewriter26_2(final Protocol<C, ?, ?, ?> protocol, final BlockRewriter.ChunkTypeSupplier chunkTypeSupplier) {
        super(protocol, chunkTypeSupplier);
    }

    @Override
    public void handleBlockEntity(final UserConnection connection, final BlockEntity blockEntity) {
        if (protocol.getMappingData().getBlockEntityMappings().mappedId("bed") == blockEntity.typeId() && blockEntity.tag() == null) {
            // Older clients expect bed block entity data even if empty to render properly
            blockEntity.setTag(new CompoundTag());
        }
        super.handleBlockEntity(connection, blockEntity);
    }
}
