package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import osrs.Helper;
import osrs.Task;
import osrs.Walker;
import osrs.assets.ITEM;
import osrs.assets.OBJECTS;

import java.util.concurrent.Callable;

public class CookingTutorial extends Task {
    public CookingTutorial(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
        GameObject fire = ctx.objects.select().id(OBJECTS.FIRE).nearest().poll();
        Item raw_shrimp = ctx.inventory.select().id(ITEM.RAW_SHRIMP).poll();

        if (fire.valid() && ctx.inventory.select().id(ITEM.RAW_SHRIMP).count() > 0) {
            if (!fire.tile().matrix(ctx).inViewport()) {
                Walker w = new Walker(ctx);

                //cooking
                System.out.println("cook something");
                Tile[] fireTile = {fire.tile()};
                Tile[] tiles = {ctx.players.local().tile(), w.getNextTile(fireTile)};

                w.walkPath(tiles);
            }

            ctx.camera.turnTo(fire);
            //cooking
            if (ctx.players.local().animation() == -1) {
                System.out.println("cook something");
                raw_shrimp.interact("Use");
                fire.interact(true, "Use");

                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        System.out.println("Waiting or cooking already");
                        return ctx.players.local().animation() != -1;
                    }
                }, Helper.smartSecondsGen(), 5);
            }
        } else if (ctx.inventory.select().id(ITEM.RAW_SHRIMP).count() > 0) {
            if (ctx.inventory.select().id(ITEM.TREE_LOG).count() == 0) {
                System.out.println("Woodcutting");
                WoodcutTutorial w = new WoodcutTutorial(ctx);
                if (w.activate()) {
                    w.execute();
                }
            } else {
                System.out.println("Firemaking");
                FiremakingTutorial fm = new FiremakingTutorial(ctx);
                if (fm.activate()) {
                    fm.execute();
                }
            }
        } else {
            System.out.println("Fishing");
            FishingTutorial f = new FishingTutorial(ctx);
            if (f.activate()) {
                f.execute();
            }
        }
    }
}
