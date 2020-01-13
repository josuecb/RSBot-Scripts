package osrs;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

import java.awt.*;

public class Avatar {
    public int hairStylesCount = 24;
    public int hairColorCount = 24;

    public int jawStylesCount = 14;

    public int torsoStylesCount = 14;
    public int torsoColorCount = 29;

    public int armsStylesCount = 12;
    public int handsStylesCount = 2;

    public int legsStylesCount = 11;
    public int legsColorCount = 29;

    public int feetStylesCount = 2;
    public int feetColorCount = 12;

    public int skinColorCount = 8;

    public ClientContext ctx;

    public Avatar(ClientContext ctx) {
        this.ctx = ctx;
    }

    public boolean pickBackHair() {
        Component h = ctx.widgets.component(269, 106);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackHairColor() {
        Component h = ctx.widgets.component(269, 105);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextHairColor() {
        Component h = ctx.widgets.component(269, 121);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextHair() {
        Component h = ctx.widgets.component(269, 113);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackJaw() {
        Component h = ctx.widgets.component(269, 107);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextJaw() {
        Component h = ctx.widgets.component(269, 114);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackTorsoColor() {
        Component h = ctx.widgets.component(269, 123);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextTorsoColor() {
        Component h = ctx.widgets.component(269, 127);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackTorso() {
        Component h = ctx.widgets.component(269, 108);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextTorso() {
        Component h = ctx.widgets.component(269, 115);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackArms() {
        Component h = ctx.widgets.component(269, 109);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextArms() {
        Component h = ctx.widgets.component(269, 116);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackHands() {
        Component h = ctx.widgets.component(269, 110);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextHands() {
        Component h = ctx.widgets.component(269, 117);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackLegsColor() {
        Component h = ctx.widgets.component(269, 122);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextLegsColor() {
        Component h = ctx.widgets.component(269, 129);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackLegs() {
        Component h = ctx.widgets.component(269, 111);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextLegs() {
        Component h = ctx.widgets.component(269, 118);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackFeetColor() {
        Component h = ctx.widgets.component(269, 124);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextFeetColor() {
        Component h = ctx.widgets.component(269, 130);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackFeet() {
        Component h = ctx.widgets.component(269, 112);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextFeet() {
        Component h = ctx.widgets.component(269, 119);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickBackSkinColor() {
        Component h = ctx.widgets.component(269, 125);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickNextSkinColor() {
        Component h = ctx.widgets.component(269, 131);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickMale() {
        Component h = ctx.widgets.component(269, 136);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean pickFemale() {
        Component h = ctx.widgets.component(269, 137);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }

    public boolean submitCharacter() {
        Component h = ctx.widgets.component(269, 100);
        if (h.visible()) {
            return h.click();
        }

        return false;
    }
}
