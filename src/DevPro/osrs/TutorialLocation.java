package osrs;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import osrs.assets.AREA;
import osrs.assets.IslandLocation;

public class TutorialLocation {
    ClientContext ctx;

    //<col=0000ff>Choosing a Display Name</col><br>Before you get started, you'll need a unique display name. Please follow the instructions on the open interface to <col=0000ff>look up names</col> you might like, or to <col=0000ff>choose a suggested one</col>.
    public TutorialLocation(ClientContext ctx) {
        this.ctx = ctx;
    }

    public boolean is(String toCompare) {
        Component input = this.wholeBox();
        Component input2 = this.inputBox();
        Component input3 = this.onTopBox();

//        System.out.println("=======================");

        if (input.text().contains(toCompare)) {
            return input.visible();
        } else if (input2.text().contains(toCompare)) {
            return input2.visible();
        } else if (input3.text().contains(toCompare)) {
            return input3.visible();
        } else {
            return false;
        }
    }

    public String getCurrentLocation() {
        String textLoc = wholeBox().text();
        if (textLoc != null && textLoc.contains("0000ff>"))
            return textLoc.substring(textLoc.indexOf("0000ff>") + 7, textLoc.indexOf("</col>"));

        return null;
    }


    private Component inputBox() {
        return ((ClientContext) this.ctx).widgets.component(162, 44);
    }

    private Component wholeBox() {
        return ((ClientContext) this.ctx).widgets.component(263, 1, 0);
    }

    private Component onTopBox() {
        return ((ClientContext) this.ctx).widgets.component(193, 2);
    }

    public void smartChecker() {
        if (!this.wholeBox().visible()) {
            if (this.inputBox().visible()) {
                if (this.inputBox().text().contains(IslandLocation.NOTHING_INTERESTING_HAPPENS) ||
                        this.inputBox().text().contains(IslandLocation.ALREADY_UNDER_ATTACK) ||
                        this.inputBox().text().contains(IslandLocation.CANT_ATTACK_OTHERS_PLAYERS_IF_NOT_WILDERNESS) ||
                        this.inputBox().text().contains(IslandLocation.CANT_REACH_THAT) ||
                        this.inputBox().text().contains(IslandLocation.UNLOCKED_MUSIC_TRACK) ||
                        this.inputBox().text().contains(IslandLocation.THEY_HEAL_SOME)) {
                    this.ctx.widgets.component(162, 45).click();
                } else {
                    Walker walker = new Walker(ctx);
                    Tile[] t = {ctx.players.local().tile()};
                    Tile[] nt = {ctx.players.local().tile(), walker.getNextTile(t)};
                    walker.walkPath(nt);
                }
            }

            clickIfOnTopBox();
            clickIfOnTopBox2();
            clickIfCongratsBox();
            continueChatBox2();
            continueChatBox();
        }
    }

    public boolean finishScript() {
        return AREA.lumbridgeArea.containsOrIntersects(ctx.players.local());
    }

    public boolean clickIfOnTopBox() {
        if (this.onTopBox().visible()) {
            this.ctx.widgets.component(193, 0, 2).click();
            return true;
        }

        return false;
    }

    public boolean clickIfOnTopBox2() {
        if (this.ctx.widgets.component(229, 2).visible()) {
            this.ctx.widgets.component(229, 2).click();
            return true;
        }

        return false;
    }

    public boolean clickIfCongratsBox() {
        if (this.ctx.widgets.component(233, 3).visible()) {
            this.ctx.widgets.component(233, 3).click();
            return true;
        }

        return false;
    }

    public boolean continueChatBox2() {
        if (this.ctx.widgets.component(217, 3).visible()) {
            this.ctx.widgets.component(217, 3).click();
            return true;
        }

        return false;
    }

    public boolean continueChatBox() {
        if (this.ctx.widgets.component(231, 3).visible()) {
            this.ctx.widgets.component(231, 3).click();
            return true;
        }

        return false;
    }
}
