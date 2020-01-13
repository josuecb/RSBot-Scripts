package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;
import osrs.Helper;
import osrs.Task;

public class TalkToEntityTask extends Task {
    int NpcId = -1;
    int[] NpcIds = null;
    int timeToTalk = 0;

    public TalkToEntityTask(ClientContext ctx, int NpcId) {
        super(ctx);
        this.NpcId = NpcId;
    }

    public TalkToEntityTask(ClientContext ctx, int NpcId, int timeToTalk) {
        super(ctx);
        this.NpcId = NpcId;
        this.timeToTalk = timeToTalk;
    }

    public TalkToEntityTask(ClientContext ctx, int[] NpcIds, int timeToTalk) {
        super(ctx);
        this.NpcIds = NpcIds;
        this.timeToTalk = timeToTalk;
    }

    // If we have no times to talk and we are not chatting
    // TODO: We need to make a class to check where it is chatting or not.
    //  Sometimes chat API doesn't know it is chatting
    @Override
    public boolean activate() {
        return timeToTalk > 0;
    }

    @Override
    public void execute() {
        while (this.timeToTalk > 0) {
            action();
            this.timeToTalk--;
        }
    }

    protected void action() {
        Npc npc;
        if (this.NpcIds != null && this.NpcIds.length > 0)
            npc = ctx.npcs.select().id(this.NpcIds).poll();
        else
            npc = ctx.npcs.select().id(this.NpcId).poll();

//        System.out.println("Can continue");
        System.out.println("****Can continue: " + this.firstBox().visible());
        do {
            this.talk(npc);
        } while (ctx.chat.canContinue() || this.firstBox().visible());

    }

    protected boolean talk(Npc npc) {

        Options opts = new Options(ctx);


        if (npc.valid()) {
            if (!npc.inViewport()) {
                // TODO: Make a camera class to rotate the whole camera from upper to south side
                ctx.camera.turnTo(npc);
                System.out.println("Turning Camera to NPC");
                return this.talk(npc);
            } else {
                if (!ctx.chat.chatting()) {
                    npc.interact("Talk-to");

                    Condition.wait(() -> {
                        System.out.println("Waiting to interact talking");
                        return ctx.chat.chatting();
                    }, Helper.smartSecondsGen(), 4);
                } else {
                    if (!opts.empty()) {
                        String opt = opts.getRandomOption();
                        ctx.chat.continueChat(opt);
                    } else {
                        ctx.chat.clickContinue();
                        Condition.sleep(Helper.smartSecondsGen());
                    }
                    return true;
                }
            }
        } else {
            System.out.println("Npc: " + npc.name() + " is not valid");
        }

        Condition.wait(() -> {
            System.out.println("Waiting to talk");
            System.out.println("Can continue: " + ctx.chat.canContinue());
            if (ctx.chat.canContinue())
                return this.talk(npc);
            else return false;
        }, Helper.smartSecondsGen(), 4);
        return false;
    }

    private Component firstBox() {
        return ctx.widgets.component(219, 1);
    }
}
