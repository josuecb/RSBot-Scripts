package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import osrs.Helper;
import osrs.Settings;
import osrs.TabChanger;
import osrs.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CustomSettingTask extends Task {
    List<Boolean> setSetting;

    public CustomSettingTask(ClientContext ctx) {
        super(ctx);
        setSetting = new ArrayList<>();
    }

    @Override
    public boolean activate() {
        Settings s = new Settings(ctx);

        System.out.println("is small screen active?: " + s.isActive(Settings.OPTION.SMALL_SCREEN));
        System.out.println(("Can be activated? : " +
                (!(s.isActive(Settings.OPTION.SMALL_SCREEN) && ctx.game.tab().equals(Game.Tab.LOGOUT)))));

        System.out.println("Current Tab: " + ctx.game.tab());
        return !(s.isActive(Settings.OPTION.SMALL_SCREEN) && ctx.game.tab().equals(Game.Tab.LOGOUT));
    }

    @Override
    public void execute() {
        System.out.print("Current Tab: ");
        System.out.println(ctx.game.tab());
        TabChanger tabChanger = new TabChanger(ctx);
        tabChanger.changeTo(Game.Tab.OPTIONS);

        final Settings s = new Settings(ctx);

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Helper.checkIfStoppedThenShutDown(ctx);
                // SCREEN SETTINGS
                System.out.println("Adding:");
                setSetting.add(s.clickOn(Settings.OPTION.SCREEN_SETTINGS));
                setSetting.add(s.clickOn(Settings.OPTION.SMALL_SCREEN));
                setSetting.add(s.clickOn(Settings.OPTION.HIGH_BRIGHT));

                // NO SOUND EFFECT
                setSetting.add(s.clickOn(Settings.OPTION.SOUND_SETTINGS));
                setSetting.add(s.clickOn(Settings.OPTION.NO_MUSIC));
                setSetting.add(s.clickOn(Settings.OPTION.NO_EFFECT_SOUND));
                setSetting.add(s.clickOn(Settings.OPTION.NO_AREA_SOUND));

//                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@: " + setSetting.size());

                for (Boolean set : setSetting) {
                    if (!set) {
                        System.out.println("Repeating");
                        return false;
                    }
                }

                return true;
            }
        }, 250, 2);
    }
}
