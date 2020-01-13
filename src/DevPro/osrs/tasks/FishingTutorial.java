package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Npc;
import osrs.*;
import osrs.assets.AREA;
import osrs.assets.ITEM;
import osrs.assets.NPC;

import java.util.concurrent.Callable;

public class FishingTutorial extends Task {

    public FishingTutorial(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
//        System.out.println(AREA.fishingTutorialArea.containsOrIntersects(ctx.players.local()));
//        System.out.println("Active function fishing count: " + fishCount);
//        System.out.println("Activate: " + (ctx.players.local().animation() == -1 && Helper.fishCount > 0));
//        System.out.println("Activate: " + ((ctx.players.local().animation() == -1 && Helper.fishCount > 0) && ctx.inventory.count() < 28));

//        System.out.println("Net Exist: " + ctx.inventory.contains(ctx.inventory.select().id(303).poll()));
//        System.out.println("Net count: " + ctx.inventory.select().id(ITEM.FISHING_NET).count());
//        System.out.println("Shrimps count: " + (Helper.actualFishCount(ctx.inventory.select().id(ITEM.RAW_SHRIMP).count()) > 0));
//        System.out.println("Shrimps count: " + Helper.actualFishCount(ctx.inventory.select().id(ITEM.RAW_SHRIMP).count()));
//        System.out.println("Shrimps count: " + ctx.inventory.select().id(ITEM.RAW_SHRIMP).count());

//        ctx.controller.stop();
        return (ctx.players.local().animation() == -1
                && Helper.actualFishCount(ctx.inventory.select().id(ITEM.RAW_SHRIMP).count()) > 0)
                && ctx.inventory.count() < 28;
    }

    @Override
    public void execute() {

        if (!AREA.fishingTutorialArea.containsOrIntersects(ctx.players.local())) {

            Tile randomTile = AREA.fishingTutorialArea.getRandomTile();
            Tile[] tiles = {ctx.players.local().tile(), randomTile};

//            System.out.println("Random Tile: " + randomTile.toString());
//            System.out.print("I am not in the area: ");
            Walker w = new Walker(ctx);
            w.walkPath(tiles);
        } else {
            //fish
            BasicQuery<Npc> fishingSpots = ctx.npcs.select().id(NPC.FISHING_SPOT).nearest();
            Npc fishingSpot = fishingSpots.peek();
            if (fishingSpot.valid() && ctx.players.local().animation() == -1) {
//                System.out.println("Valid fishing now");
//                System.out.println(ctx.players.local().animation());

                ctx.camera.turnTo(fishingSpot);
                fishingSpot.interact("Net");

//                Helper.fishCount--;
//                System.out.print("Fishing count: ");
//                System.out.println(Helper.fishCount);
//                System.out.println(ctx.players.local().animation());
//                System.out.println("animated: " + (ctx.players.local().animation() != -1));

                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        System.out.println("Waiting: " + (ctx.players.local().animation() != -1));
                        return ctx.players.local().animation() != -1;
                    }
                }, Helper.smartSecondsGen(), 1);
            }

        }

//        System.out.println(ctx.players.local().tile());
//        ctx.controller.stop();
    }
}
