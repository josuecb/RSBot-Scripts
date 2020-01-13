package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import osrs.ChatOptions;
import osrs.Helper;
import osrs.Task;
import osrs.assets.NPC;

import java.util.concurrent.Callable;

public class TalkToMagic extends Task {
    Npc magicInstructor;

    public TalkToMagic(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        magicInstructor = ctx.npcs.select().id(NPC.MAGIC_INSTRUCTOR).nearest().poll();
        System.out.println(magicInstructor.valid() && !ctx.chat.chatting());
        return magicInstructor.valid() && !ctx.chat.chatting();
    }

    @Override
    public void execute() {
        if (!this.magicInstructor.inViewport()) {
            ctx.camera.turnTo(magicInstructor);
        } else {
            if (!ctx.chat.chatting()) {
                magicInstructor.interact("Talk-to");
                this.smartWait();
            }

            if (!ctx.chat.chatting())
                return;

            while (ctx.chat.chatting() || new ChatOptions(ctx).getRandomOption() != null) {
                System.out.println("trying to talk");
                ChatOptions opts = new ChatOptions(ctx);

                System.out.println(opts.getRandomOption());
                if (opts.getOptions().size() > 0) {
                    if (opts.getOptions().size() == 2) {
                        ctx.chat.continueChat("Yes");
                    } else if (opts.getOptions().size() == 3) {
                        ctx.chat.continueChat("No");
//                        ctx.chat.continueChat("No, I'm not planning to do that.");
//                        System.out.println("trying to talk 3 OPTS");
                    }
                } else {
                    ctx.chat.continueChat();
                }

                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        System.out.println("Waiting to continue");
                        return !ctx.chat.canContinue();
                    }
                }, Helper.smartSecondsGen(), 5);
            }
        }
    }

    public void smartWait() {
        Helper.smartSecondsGen();
        Helper.smartSecondsGen();
        Helper.smartSecondsGen();
        Helper.smartSecondsGen();
    }
}
