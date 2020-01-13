package osrs.tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import osrs.Helper;
import osrs.Task;
import osrs.assets.AREA;
import osrs.assets.ITEM;
import osrs.assets.OBJECTS;

import java.util.concurrent.Callable;

public class SmeltBarTask extends Task {
    public SmeltBarTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation() == -1 && ctx.inventory.select().id(ITEM.BRONZE_BAR).count() < 1;
    }

    @Override
    public void execute() {
        Item tinOre = ctx.inventory.select().id(ITEM.TIN_ORE).poll();
        Item copperOre = ctx.inventory.select().id(ITEM.COPPER_ORE).poll();

        if (tinOre.valid() && copperOre.valid()) {
            WalkTask go = new WalkTask(ctx, AREA.furnaceTutorialArea);
            if (go.activate())
                go.execute();

            GameObject furnace = ctx.objects.select().id(OBJECTS.FURNACE).poll();
            furnace.interact("Use");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println("Waiting to make bar.");
                    return ctx.players.local().animation() != -1;
                }
            }, Helper.smartSecondsGen(), 10);
        } else {
            mineOre(tinOre, OBJECTS.TIN_ORE, AREA.tinOreTutorialArea);
            mineOre(copperOre, OBJECTS.COOPER_ORE, AREA.copperOreTutorialArea);
        }
    }

    private void mineOre(Item ore, int oreObjectId, Area area) {
        if (!ore.valid()) {
            WalkTask wt = new WalkTask(ctx, area);
            wt.execute();

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println("Waiting walking towards " + ore.name() + " ore area");
                    return ctx.players.local().animation() != -1;
                }
            }, Helper.smartSecondsGen(), 10);

            MiningTask mt = new MiningTask(ctx, oreObjectId);
            mt.execute();

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println("Waiting mining some " + ore.name());
                    return ctx.players.local().animation() != -1;
                }
            }, Helper.smartSecondsGen(), 10);

        }
    }
}
