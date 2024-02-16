package cx.rain.mc.bukkit.loreanvil.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;

public class EventFixItem implements Listener {

    @EventHandler
    public void onFixItem(InventoryClickEvent event) {
        if (event.isCancelled())
            return;
        if (!(event.getWhoClicked() instanceof Player player))
            return;
        if (!(event.getClickedInventory() instanceof AnvilInventory anvil))
            return;
        if (event.getCurrentItem() == null)
            return;

        ItemStack item = event.getCurrentItem();

        if (!(item.getItemMeta() instanceof Repairable repairable))
            return;

        int current = repairable.getRepairCost();

        if ((current == -1) || (current >= 40))
            current = 40;
        repairable.setRepairCost(current);
        item.setItemMeta(repairable);
        event.setCurrentItem(item);
    }
}
