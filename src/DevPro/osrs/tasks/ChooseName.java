package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import osrs.Helper;
import osrs.Task;
import osrs.TutorialLocation;
import osrs.assets.IslandLocation;

import java.util.concurrent.Callable;

public class ChooseName extends Task {
    TutorialLocation tl;
    Component successMessage;
    String displayName;

    public ChooseName(ClientContext ctx, String displayName) {
        super(ctx);
        tl = new TutorialLocation(ctx);
        this.displayName = displayName;
        successMessage = ctx.widgets.component(558, 12);
    }

    @Override
    public boolean activate() {
//        System.out.print("Visible: ");
//        System.out.println(successMessage.visible() && !successMessage.text().contains("Great! This display name is"));
//        System.out.println(successMessage.text());
        return successMessage.visible() && !successMessage.text().contains("Great! This display name is");
    }

    @Override
    public void execute() {
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if (tl.is(IslandLocation.CHOOSE_DISPLAY_NAME) ||
                        tl.is("Please pick a unique display name")) {

                    Component nameInputBox = ctx.widgets.component(558, 7);
                    if (nameInputBox.visible()) {
                        nameInputBox.click();
                    }

                    Helper.sleepToClick();

                    System.out.println("Picking a new display name");
                    System.out.println(pendingInput());
                    if (pendingInput()) {

                        System.out.println("Changing name");
//                        if (ctx.input.send("BewBow")) {
                        if (ctx.input.send(displayName)) {
                            ctx.input.sendln("");

                            Helper.sleepToClick();
                            Helper.sleepToClick();

                            Component lookUpComp = ctx.widgets.component(558, 17, 9);
                            if (lookUpComp.visible()) {
                                lookUpComp.click();
                            }
                        }
                    }

                    Helper.sleepToClick();
                    Helper.sleepToClick();

                    if (successMessage.text().contains("Great! This display name is")) {
                        Component lookUpComp = ctx.widgets.component(558, 18, 9);
                        if (lookUpComp.visible()) {
                            lookUpComp.click();
                        }
                    }
                }
                return successMessage.visible() && !successMessage.text().contains("Great! This display name is");
            }
        }, Helper.smartSecondsGen(), 1);
    }

    private Component inputBox() {
        return ((ClientContext) this.ctx).widgets.component(162, 45);
    }

    public boolean pendingInput() {
        return this.inputBox().visible();
    }


}
