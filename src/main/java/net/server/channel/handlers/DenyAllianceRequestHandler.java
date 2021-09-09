/*
    This file is part of the HeavenMS MapleStory Server
    Copyleft (L) 2016 - 2019 RonanLana

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation version 3 as published by
    the Free Software Foundation. You may not use, modify or distribute
    this program under any other version of the GNU Affero General Public
    License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.server.channel.handlers;

import client.MapleCharacter;
import client.MapleClient;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.guild.MapleAlliance;

/**
 * @author Ronan
 */
public final class DenyAllianceRequestHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, MapleClient c) {
        p.readByte();
        String inviterName = p.readString();
        String guildName = p.readString();
        
        MapleCharacter chr = c.getWorldServer().getPlayerStorage().getCharacterByName(inviterName);
        if (chr != null) {
            MapleAlliance alliance = chr.getAlliance();
            if (alliance != null) {
                MapleAlliance.answerInvitation(c.getPlayer().getId(), guildName, alliance.getId(), false);
            }
        }
    }
}