package osrs;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Menu;

import java.util.Arrays;

public class Settings {
    ClientContext ctx;

    public Settings(ClientContext ctx) {
        this.ctx = ctx;
    }

    public boolean clickOn(OPTION setting) {
        Component c;
        if (setting.equals(OPTION.SMALL_SCREEN)) {
            c = ctx.widgets.component(261, 33).component(4);
            // Means it is on
            if (c.textureId() == 1154) {
                return true;
            }
        } else if (setting.equals(OPTION.SCREEN_SETTINGS)) {
            c = ctx.widgets.component(261, 1).component(1);
        } else if (setting.equals(OPTION.HD_TRANSPARENT_SCREEN)) {
            c = ctx.widgets.component(261, 34).component(4);
            if (c.click(false) && ctx.menu.commands().length == 1)
                return true;
        } else if (setting.equals(OPTION.LOW_BRIGHT)) {
            c = ctx.widgets.component(261, 18);
        } else if (setting.equals(OPTION.SOUND_SETTINGS)) {
            c = ctx.widgets.component(261, 1).component(3);
        } else if (setting.equals(OPTION.NO_MUSIC)) {
            c = ctx.widgets.component(261, 45);
            if (c.textureId() == 687) {
                return true;
            }
        } else if (setting.equals(OPTION.NO_EFFECT_SOUND)) {
            c = ctx.widgets.component(261, 51);
            if (c.textureId() == 687) {
                return true;
            }
        } else if (setting.equals(OPTION.NO_AREA_SOUND)) {
            c = ctx.widgets.component(261, 57);
            if (c.textureId() == 687) {
                return true;
            }
        } else if (setting.equals(OPTION.HIGH_BRIGHT)) {
            c = ctx.widgets.component(261, 21);
            if (c.textureId() == 686) {
                return true;
            }
        } else {
            return false;
        }

        if (c != null) {
            c.click();
            Helper.sleepToClick();
        }
        return true;
    }

    public boolean isActive(OPTION setting) {
        Component c;
        if (setting.equals(OPTION.SMALL_SCREEN)) {
            c = ctx.widgets.component(261, 33).component(4);
            // Means it is on
            return c.textureId() == 1154;
        } else if (setting.equals(OPTION.SCREEN_SETTINGS)) {
            c = ctx.widgets.component(261, 1).component(1);
        } else if (setting.equals(OPTION.HD_TRANSPARENT_SCREEN)) {
            c = ctx.widgets.component(261, 34).component(4);
            return c.click(false) && ctx.menu.commands().length == 1;
        } else if (setting.equals(OPTION.LOW_BRIGHT)) {
            c = ctx.widgets.component(261, 18);
        } else if (setting.equals(OPTION.SOUND_SETTINGS)) {
            c = ctx.widgets.component(261, 1).component(3);
        } else if (setting.equals(OPTION.NO_MUSIC)) {
            c = ctx.widgets.component(261, 45);
            return c.textureId() == 687;
        } else if (setting.equals(OPTION.NO_EFFECT_SOUND)) {
            c = ctx.widgets.component(261, 51);
            return c.textureId() == 687;
        } else if (setting.equals(OPTION.NO_AREA_SOUND)) {
            c = ctx.widgets.component(261, 57);
            return c.textureId() == 687;
        } else if (setting.equals(OPTION.HIGH_BRIGHT)) {
            c = ctx.widgets.component(261, 21);
            return c.textureId() == 686;
        } else {
            return false;
        }
        return false;
    }

    public static enum OPTION {
        SCREEN_SETTINGS,

        LOW_BRIGHT,
        HIGH_BRIGHT,
        SMALL_SCREEN,
        HD_TRANSPARENT_SCREEN,

        SOUND_SETTINGS,

        NO_MUSIC,
        NO_EFFECT_SOUND,
        NO_AREA_SOUND

    }
}
