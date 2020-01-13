package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import osrs.Helper;
import osrs.TabChanger;
import osrs.Task;
import osrs.Walker;
import osrs.assets.AREA;
import osrs.assets.OBJECTS;

import java.util.concurrent.Callable;

public class WoodcutTutorial extends Task {
    public WoodcutTutorial(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation() == -1
                && Helper.actualTreeCount(ctx.inventory.select().id(OBJECTS.TREE).count()) > 0
                && ctx.inventory.select().count() < 28;
    }

    @Override
    public void execute() {
        if (!AREA.woodcutTutorialArea.containsOrIntersects(ctx.players.local())) {

            Tile randomTile = AREA.woodcutTutorialArea.getRandomTile();
            Tile[] tiles = {ctx.players.local().tile(), randomTile};

            Walker w = new Walker(ctx);
            w.walkPath(tiles);
        } else {
            if (ctx.inventory.selectedItemIndex() != -1) {
                TabChanger tabChanger = new TabChanger(ctx);
                tabChanger.changeTo(Game.Tab.INVENTORY);
            }

            GameObject treeObj = ctx.objects.select().id(OBJECTS.TREE).nearest().poll();
            if (treeObj.valid() && ctx.players.local().animation() == -1) {
                ctx.camera.turnTo(treeObj);
                treeObj.interact(true, "Chop down");

                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.players.local().animation() != -1;
                    }
                }, Helper.smartSecondsGen(), 1);
            }


        }
//        ChatOption chatOption = ctx.chat.select().poll();
//        System.out.println(chatOption.text());
//        chatOption.ctx.chat.clickContinue();

//        GameObject treeObj = ctx.objects.select().id(TREE_IDS).nearest().poll();
//        treeObj.interact(true,"Chop down");
    }
}
