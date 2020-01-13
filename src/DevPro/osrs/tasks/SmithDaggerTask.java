package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import osrs.Helper;
import osrs.Task;
import osrs.assets.ITEM;
import osrs.assets.OBJECTS;

import java.util.concurrent.Callable;

public class SmithDaggerTask extends Task {
    public SmithDaggerTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
//        return ctx.players.local().animation() == -1;
        return ctx.players.local().animation() == -1 && ctx.inventory.select().id(ITEM.BRONZE_BAR).count() >= 1;
    }

    @Override
    public void execute() {
        Item hammer = ctx.inventory.select().id(ITEM.HAMMER).poll();
        Item bronzeBar = ctx.inventory.select().id(ITEM.BRONZE_BAR).poll();

        if (hammer.valid() && bronzeBar.valid()) {
            GameObject anvil = ctx.objects.select().id(OBJECTS.ANVIL).nearest().poll();
            if (anvil.valid()) {
                anvil.interact("Smith");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        System.out.println("Waiting");
                        return ctx.players.local().animation() != -1;
                    }
                }, Helper.smartSecondsGen(), 4);

                Component selectDagger = ctx.widgets.component(312, 9, 0);
                if (selectDagger.visible()) {
                    selectDagger.click();
                } else {
                    System.out.println("Smithing component is not visible");
                }
            }
        } else {
            System.out.println("I don't have a hammer/bronze_bar anymore");
        }

    }
}
