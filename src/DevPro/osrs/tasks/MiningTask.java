package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import osrs.Helper;
import osrs.Task;
import osrs.assets.ITEM;
import osrs.assets.OBJECTS;

import java.util.concurrent.Callable;

public class MiningTask extends Task {
    int oreId;
    int oreAmount;


    public MiningTask(ClientContext ctx, int oreId) {
        super(ctx);
        this.oreId = oreId;
        this.oreAmount = 1;
    }

    public MiningTask(ClientContext ctx, int oreId, int amount) {
        super(ctx);
        this.oreId = oreId;
        this.oreAmount = amount;
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation() == -1 &&
                ctx.inventory.select().count() < 28;
//                && ctx.inventory.select().id(this.oreId).count() < this.oreAmount;
    }

    @Override
    public void execute() {
        Item bronzePickItem = ctx.inventory.select().id(ITEM.BRONZE_PICK).poll();
        GameObject ore = ctx.objects.select().id(this.oreId).nearest().poll();

        if (bronzePickItem.valid() && ore.valid()) {
//            ore.interact(true, "Prospect");
//            System.out.println("prospecting tin ore");
//            Condition.sleep(2000);
            ore.interact(true, "Mine");
        }

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        }, Helper.smartSecondsGen(), 1);
    }
}
