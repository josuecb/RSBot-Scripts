package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Item;
import osrs.Helper;
import osrs.TabChanger;
import osrs.Task;
import osrs.assets.ITEM;

public class EquipmentTask extends Task {
    int[] itemsToEquipIds;
    boolean equippedItems = true;

    public EquipmentTask(ClientContext ctx, int[] itemsToEquipIds) {
        super(ctx);
        this.itemsToEquipIds = itemsToEquipIds;
    }

    @Override
    public boolean activate() {
        return !this.itemsEquipped();
    }

    @Override
    public void execute() {
        this.displayStatsArmourAndEquip();
    }


    private boolean itemsEquipped() {
        // if any item is in inventory then it is not equipped
        for (int itemId : this.itemsToEquipIds) {
            Item weapon = ctx.inventory.select().id(itemId).poll();
            if (weapon.valid())
                return false;
        }

        return true;
    }

    private void equip(Item weapon) {
        if (weapon.valid()) {
            weapon.interact("Equip");
            Condition.sleep(Helper.smartSecondsGen());
        }
    }

    private void displayStatsArmourAndEquip() {
        if (this.wornEquipStats().visible()) {
            for (int itemId : this.itemsToEquipIds) {
                Item weapon = ctx.inventory.select().id(itemId).poll();

                this.equip(weapon);
            }

            if (this.closeWornStats().visible()) {
                Condition.sleep(Helper.smartSecondsGen());
                this.closeWornStats().click();
            }
        } else {
            if (this.wornEquipIcon().visible()) {
                this.wornEquipIcon().click();
            } else {
                TabChanger tabChanger = new TabChanger(ctx);
                tabChanger.changeTo(Game.Tab.EQUIPMENT);
            }
        }
    }

    private Component wornEquipStats() {
        return ctx.widgets.component(84, 2);
    }

    private Component wornEquipIcon() {
        return ctx.widgets.component(387, 1);
    }

    private Component closeWornStats() {
        return ctx.widgets.component(84, 4);
    }
}
