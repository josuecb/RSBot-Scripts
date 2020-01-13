package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Item;
import osrs.Helper;
import osrs.Task;
import osrs.Walker;
import osrs.assets.AREA;
import osrs.assets.ITEM;

import java.util.concurrent.Callable;

public class FiremakingTutorial extends Task {
    public FiremakingTutorial(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation() == -1
                && ctx.inventory.select().id(ITEM.TREE_LOG).count() > 0;
    }

    @Override
    public void execute() {
        Item tinderBox = ctx.inventory.select().id(ITEM.TINDER_BOX).poll();
        Item treeLog = ctx.inventory.select().id(ITEM.TREE_LOG).poll();
        System.out.println("Executing");
        if (tinderBox.valid() && treeLog.valid() && ctx.players.local().animation() == -1) {
            System.out.println("it is valid");
            tinderBox.interact("Use");
            treeLog.interact("Use");

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    if (!canLight()) {
                        System.out.println("Can't light something in then move");
                        WalkTask wt = new WalkTask(ctx, AREA.fishingTutorialArea);
                        wt.execute(); // Execute no matter what
                    }
                    return ctx.players.local().animation() != -1;
                }
            }, Helper.smartSecondsGen(), 1);
        }
    }

    private boolean canLight() {
        return !cantLightWarning().visible();
    }

    private Component cantLightWarning() {
        return ctx.widgets.component(229, 1);
    }
}
