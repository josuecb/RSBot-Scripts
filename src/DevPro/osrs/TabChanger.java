package osrs;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Game;

public class TabChanger {
    public static String AccountManagement = "account management";
    private ClientContext ctx;

    public TabChanger(ClientContext ctx) {
        this.ctx = ctx;
    }


    /**
     * @param tab: desired tab
     * @return true if changed and false if it didn't change anything
     */
    public boolean changeTo(Game.Tab tab) {
        if (!ctx.game.tab().equals(tab)) {
            ctx.game.tab(tab);
//            System.out.print("Switching to: ");
//            System.out.println(ctx.game.tab());
            return true;
        }
        return false;
    }

    public boolean changeTo(String tab) {
        if (tab.equals(TabChanger.AccountManagement) && accountManagementTab().textureId() != -1) {
            accountManagementTab().click();
            return true;
        }
        return false;
    }

    private Component accountManagementTab() {
        return ctx.widgets.component(548, 33);
    }


}
