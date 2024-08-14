package me.cyh2.thirdanniversarycelebrationevent.plays.giftseeker;


import me.cyh2.thirdanniversarycelebrationevent.Variable;
import org.bukkit.Location;

import static me.cyh2.thirdanniversarycelebrationevent.plays.giftseeker.gift.spawn;

public class ServerGift extends Variable {
    public static void spawnGifts () {
        giftLocation.getKeys(false).forEach( str -> {
            Object object =  giftLocation.get(str + ".Location");
            if (object instanceof Location) {
                Location location = (Location) object;
                GiftUUID.put(location, str);
                spawn(location);
            }
        });
    }
}
