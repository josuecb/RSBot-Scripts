package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import osrs.Helper;
import osrs.Task;

import java.util.concurrent.Callable;

public class Banking extends Task {
    public Banking(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() > 0 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            if (ctx.bank.depositInventory()) {
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.inventory.count() == 0 && ctx.bank.opened();
                    }
                }, Helper.smartSecondsGen(), 5);

                ctx.bank.close();
            }
        } else {
            if (ctx.bank.inViewport() && ctx.bank.open()) {
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.bank.opened();
                    }
                }, Helper.smartSecondsGen(), 5);
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }
    }
}
