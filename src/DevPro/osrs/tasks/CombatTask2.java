package osrs.tasks;

import osrs.SmartCamera;
import osrs.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.rt4.*;
import osrs.Helper;
import osrs.TabChanger;
import osrs.assets.ITEM;

import java.util.Random;
import java.util.concurrent.Callable;

public class CombatTask2 extends Task {
    Npc npcToAttack;
    int NpcId;
    int[] NpcIds;
    Type combatType;

    public CombatTask2(ClientContext ctx, int NpcId, Type type) {
        super(ctx);
        this.NpcId = NpcId;
        npcToAttack = this.findNPCS();
        this.combatType = type;
    }

    public CombatTask2(ClientContext ctx, int[] NpcIds, Type type) {
        super(ctx);
        this.NpcId = -1;
        this.NpcIds = NpcIds;
        npcToAttack = this.findNPCS();
        this.combatType = type;
    }

    @Override
    public boolean activate() {
        System.out.println("Should attack: ? " + shouldAttack());

        return shouldAttack();
    }

    @Override
    public void execute() {
//        System.out.println(this.findNPCS().toString());
//        ctx.controller.stop();
        if (shouldAttack()) {
            attack();
        }

    }

    public Npc findNPCS() {
        if (this.NpcId < 1) {
            return this.ctx.npcs.select().id(this.NpcIds).select(new Filter<Npc>() {
                @Override
                public boolean accept(Npc npc) {
                    return !npc.healthBarVisible();
                }
            }).nearest().poll();
        } else {
            return this.ctx.npcs.select().id(this.NpcId).select(new Filter<Npc>() {
                @Override
                public boolean accept(Npc npc) {
                    return !npc.healthBarVisible();
                }
            }).nearest().poll();
        }
    }

    private void health() {
        Item shrimp = ctx.inventory.select().id(ITEM.SHRIMP).poll();
        if (this.hasFood(shrimp) && this.lowHealth()) {
            shrimp.interact("Eat");
        } else {
            System.out.println("Didn't eat anything.");
        }
    }

    private boolean hasFood(Item food) {
        return food.valid();
    }

    private boolean lowHealth() {
        return ctx.combat.healthPercent() < 60;
    }

    public void attack() {
        npcToAttack = this.findNPCS();
        this.switchGear();

        if (this.combatType == CombatTask2.Type.MAGIC) {
            TabChanger t = new TabChanger(ctx);
            t.changeTo(Game.Tab.MAGIC);

            if (ctx.magic.ready(Magic.Spell.WIND_STRIKE) && !ctx.magic.cast(Magic.Spell.WIND_STRIKE)) {
                ctx.magic.cast(Magic.Spell.WIND_STRIKE);
                Helper.smartSecondsGen();
                System.out.println("Casting");
                npcToAttack.interact("Cast");
            }
        } else {
            npcToAttack.interact(true, "Attack");
        }


        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                System.out.println("Waiting to release");
                health();
                makeRandomStuff();

                return npcToAttack.healthPercent() < 5 || Helper.checkIfStoppedThenShutDown(ctx);
            }
        }, Helper.smartSecondsGen() * 4, 6);
    }

    private void makeRandomStuff() {
        if ((new Random().nextInt(100) + 20) % 2 == 0) {
            SmartCamera sc = new SmartCamera(ctx, true);
            sc.rotate360Camera(1);
        } else {
            npcToAttack.interact(true, "Attack");
        }
    }

    public boolean shouldAttack() {
//        System.out.println("In combat: " + ctx.players.local().healthBarVisible());
//        System.out.println("In Motion: " + ctx.players.local().inMotion());
//        Condition.sleep(2000);
        return !ctx.players.local().inMotion() && !ctx.players.local().healthBarVisible() || !(npcToAttack != null && ctx.players.local().interacting().equals(npcToAttack));
    }

    public static enum Type {
        MELEE,
        RANGE,
        MAGIC
    }

    private void switchToMelee() {
        int mainHandID = ctx.equipment.itemAt(Equipment.Slot.MAIN_HAND).id();
        int shieldHandID = ctx.equipment.itemAt(Equipment.Slot.OFF_HAND).id();

        if (mainHandID != ITEM.BRONZE_SWORD)
            wieldITem(ITEM.BRONZE_SWORD);

        Condition.sleep(Helper.smartSecondsGen());

        if (shieldHandID != ITEM.WOODEN_SHIELD)
            wieldITem(ITEM.WOODEN_SHIELD);
        Condition.sleep(Helper.smartSecondsGen());
    }

    private void switchToRange() {
        int mainHandID = ctx.equipment.itemAt(Equipment.Slot.MAIN_HAND).id();
        int arrowWeaponID = ctx.equipment.itemAt(Equipment.Slot.QUIVER).id();

        if (mainHandID != ITEM.SHORT_BOW)
            wieldITem(ITEM.SHORT_BOW);

        Condition.sleep(Helper.smartSecondsGen());

        if (arrowWeaponID != ITEM.BRONZE_SWORD)
            wieldITem(ITEM.BRONZE_ARROW);
        Condition.sleep(Helper.smartSecondsGen());
    }

    private void switchToMagic() {

    }

    private void wieldITem(int wieldItemId) {
        Item bronzeWord = ctx.inventory.select().id(wieldItemId).poll();
        if (bronzeWord.valid()) {
            bronzeWord.interact("Wield");
        } else {
            System.out.println("Item is not on inventory");
        }
    }

    private void switchGear() {
        TabChanger tabChanger = new TabChanger(ctx);
        tabChanger.changeTo(Game.Tab.INVENTORY);

        switch (this.combatType) {
            case MELEE:
                this.switchToMelee();
                break;
            case RANGE:
                this.switchToRange();
                break;
            case MAGIC:
                this.switchToMagic();
                break;
        }
    }
}
