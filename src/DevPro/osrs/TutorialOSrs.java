package osrs;

import org.powerbot.script.Condition;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.*;
import osrs.assets.*;
import osrs.gui.SettingWindow;
import osrs.tasks.*;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "Tutrial", description = "OSRS Tutorial", properties = "client=4; author=Josue; topic=999;")
public class TutorialOSrs extends PollingScript<ClientContext> {
    SettingWindow sw;
    int count = 0;

    @Override
    public void start() {
        sw = new SettingWindow(getName());
    }

    public List<Task> detectLocation() {
        List<Task> taskList = new ArrayList<Task>();

        TutorialLocation location = new TutorialLocation(ctx);

        if (location.is(IslandLocation.CHOOSE_DISPLAY_NAME)) {
            taskList.add(new ChooseName(ctx, sw.getUsername()));
        } else if (location.is(IslandLocation.OPTION_MENU) || location.is(IslandLocation.OPTION_MENU_TALK)) {
            System.out.println("OPTION MENU");

            taskList.add(new CustomSettingTask(ctx));
            taskList.add(new TalkToEntityTask(ctx, NPC.GIELINOR_GUIDE, 1));

        } else if (location.is(IslandLocation.YOU_VE_GIVEN_AN_ITEM)) {
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.INVENTORY);

            System.out.println("Current Tab" + ctx.game.tab());
        } else if (location.is(IslandLocation.YOU_VE_GAINED_SOME_EXP)) {
            System.out.println("Switching to tab: " + ctx.game.tab());

            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.STATS);
        } else if (location.is(IslandLocation.SKILLS_AND_EXPERIENCE)) {
            taskList.add(new TalkToEntityTask(ctx, NPC.SURVIVAL_EXPERT, 1));
        } else if (location.is(IslandLocation.FISHING)) {
            System.out.println("Fishing");
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.INVENTORY);

            taskList.add(new FishingTutorial(ctx));
        } else if (location.is(IslandLocation.WOODCUTING)) {
            System.out.println("Woodcutting");
//            System.out.println("Ahhh");
            taskList.add(new WoodcutTutorial(ctx));
        } else if (location.is(IslandLocation.FIREMAKING)) {
            taskList.add(new FiremakingTutorial(ctx));
        } else if (location.is(IslandLocation.MOVING_TO_FIRST)) {
            taskList.add(new WalkTask(ctx, AREA.woodcutTutorialArea, 1));
        } else if (location.is(IslandLocation.MOVING_TO_SURVIVAL_EXPERT)) {
            taskList.add(new WalkTask(ctx, AREA.woodcutTutorialArea, 1));
            taskList.add(new TalkToEntityTask(ctx, NPC.SURVIVAL_EXPERT, 1));
        } else if (location.is(IslandLocation.SURVIVAL_EXPERT_GIVES_YOU_NET)) {
            ctx.widgets.component(193, 0, 2).click();
            System.out.println("Clicking on net: " + ctx.chat.canContinue());
        } else if (location.is(IslandLocation.MOVING_ON_AFTER_KITCHEN)) {
            taskList.add(new WalkTask(ctx, AREA.afterWardsOutsideKitchenArea));
        } else if (location.is(IslandLocation.FANCY_A_RUN)) {
            System.out.println("Fancy a run");
            Component runComp = ctx.widgets.component(160, 24);

            runComp.click();
            Condition.sleep(Helper.smartSecondsGen());

            if (runComp.textureId() == 1064) {
                runComp.click();
            }

        } else if (location.is(IslandLocation.MOVING_ON_AFTER_COOKING)) {
            System.out.println("Going to quest area@@@@@@@@@@@@@");
            taskList.add(new WalkTask(ctx, AREA.questTutorialArea));
        } else if (location.is(IslandLocation.MOVING_AFTER_COOKING_FISH) || location.is(IslandLocation.MOVING_AFTER_COOKING_FISH2)) {
            System.out.println("Going outside");
            taskList.add(new WalkTask(ctx, AREA.kitchenTutorialArea));
        } else if (location.is(IslandLocation.LEARN_QUEST)) {
            System.out.println("Talking to Quest Guide");
            taskList.add(new TalkToEntityTask(ctx, NPC.QUEST_GUIDE, 1));
        } else if (location.is(IslandLocation.QUEST_JOURNAL_TALK)) {
            System.out.println("QUEST JOURNAL");
            taskList.add(new TalkToEntityTask(ctx, NPC.QUEST_GUIDE, 1));
        } else if (location.is(IslandLocation.MOVING_ON_AFTER_QUEST)) {
            System.out.println("MOVING TO CAVE");
            taskList.add(new WalkTask(ctx, AREA.miningStartingPointArea, new String[]{"staircase"}));
        } else if (location.is(IslandLocation.MINING_AND_SMITHING_WALK)) {
            System.out.println("MOVING TO MINING");
            taskList.add(new WalkTask(ctx, AREA.miningTutorialArea));
            taskList.add(new TalkToEntityTask(ctx, NPC.MINING_INSTRUCTOR, 1));
        } else if (location.is(IslandLocation.MINING_TIN_ORE)) {
            System.out.println("Mining tin ore");
            taskList.add(new WalkTask(ctx, AREA.tinOreTutorialArea));
            taskList.add(new MiningTask(ctx, OBJECTS.TIN_ORE));

        } else if (location.is(IslandLocation.MINING_COPPER_ORE)) {
            System.out.println("Mining cooper ore");
            taskList.add(new WalkTask(ctx, AREA.copperOreTutorialArea));
            taskList.add(new MiningTask(ctx, OBJECTS.COOPER_ORE));

        } else if (location.is(IslandLocation.QUEST_JOURNAL)) {
            System.out.println("QUEST TAB");
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.QUESTS);
        } else if (location.is(IslandLocation.MOVING_ON_AFTER_COOKING)) {
            System.out.println("Going to kitchen area");
            taskList.add(new WalkTask(ctx, AREA.kitchenTutorialArea));
        } else if (location.is(IslandLocation.SMITHING_SMELT_BRONZE_BAR)) {
            System.out.println("smelting ore");
            taskList.add(new WalkTask(ctx, AREA.furnaceTutorialArea));
            taskList.add(new SmeltBarTask(ctx));
        } else if (location.is(IslandLocation.SMITHING_A_DAGGER) || location.is(IslandLocation.SMITHING_A_DAGGER_ACTION)) {
            System.out.println("smithing a dagger");
            taskList.add(new WalkTask(ctx, AREA.anvilTutorialArea));
            taskList.add(new SmithDaggerTask(ctx));
        } else if (location.is(IslandLocation.MOVING_AFTER_SMITHING)) {
            System.out.println("moving on after smithing");
            taskList.add(new WalkTask(ctx, AREA.firstHalfToCombatArea));
        } else if (location.is(IslandLocation.COMBAT_TALK)) {
            System.out.println("going to instructor");
            taskList.add(new WalkTask(ctx, AREA.combatInstructorArea));
            taskList.add(new TalkToEntityTask(ctx, NPC.COMBAT_INSTRUCTOR, 1));
        } else if (location.is(IslandLocation.EQUIPMENT_ITEM)) {
            System.out.println("EQUIPMENT ITEM");
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.EQUIPMENT);
        } else if (location.is(IslandLocation.WORN_INVENTORY)) {
            System.out.println("WORN INVENTORY");
            Component wornEquipmentComp = ctx.widgets.component(387, 1);

            if (wornEquipmentComp.visible()) {
                wornEquipmentComp.click();
            } else {
                TabChanger tabChanger = new TabChanger(ctx);
                tabChanger.changeTo(Game.Tab.EQUIPMENT);
            }
        } else if (location.is(IslandLocation.EQUIP_DAGGER)) {
            System.out.println("EQUIP DAGGER");
            taskList.add(new EquipmentTask(ctx, new int[]{ITEM.BRONZE_DAGGER}));
        } else if (location.is(IslandLocation.HOLDING_DAGGER)) {
            System.out.println("HOLDING DAGGER");
            taskList.add(new TalkToEntityTask(ctx, NPC.COMBAT_INSTRUCTOR, 1));
        } else if (location.is(IslandLocation.UNEQUIPPING_ITEM)) {
            System.out.println("Unnequiping items");
            taskList.add(new EquipmentTask(ctx, new int[]{ITEM.WOODEN_SHIELD, ITEM.BRONZE_SWORD}));
        } else if (location.is(IslandLocation.COMBAT_INTERFACE)) {
            System.out.println("combat interface items");
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.ATTACK);
        } else if (location.is(IslandLocation.COMBAT_GO_IN)) {
            System.out.println("combat go in");
            taskList.add(new WalkTask(ctx, AREA.combatRatArea));
        } else if (location.is(IslandLocation.ATTACKING_RATS)
                || location.is(IslandLocation.SIT_BACK_AND_WATCH)) {
            System.out.println("Attacking rats");
//            taskList.add(new CombatTask(ctx));
            taskList.add(new CombatTask2(ctx, NPC.GIANT_RAT, CombatTask2.Type.MELEE));
        } else if (location.is(IslandLocation.WELL_DONE_FIRST_KILL)) {
            taskList.add(new WalkTask(ctx, AREA.combatInstructorArea));
            taskList.add(new TalkToEntityTask(ctx, NPC.COMBAT_INSTRUCTOR, 1));

        } else if (location.is(IslandLocation.KITCHEN_COOKING)) {
            System.out.println("KITCHEN COOKING");
            taskList.add(new TalkToEntityTask(ctx, NPC.MASTER_CHEF, 1));
        } else if (location.is(IslandLocation.SMITHING_WEAPON_TALK)) {
            System.out.println("talk to make a weapon");
            taskList.add(new WalkTask(ctx, AREA.miningTutorialArea));
            taskList.add(new TalkToEntityTask(ctx, NPC.MINING_INSTRUCTOR, 1));
        } else if (location.is(IslandLocation.MAKING_DOUGH)) {
            System.out.println("MAKING DOUGH");
            Item potOfFlour = ctx.inventory.select().id(ITEM.POT_OF_FLOUR).poll();
            Item bucketOfWater = ctx.inventory.select().id(ITEM.BUCKET_OF_WATER).poll();

            System.out.println(potOfFlour.valid());
            System.out.println(bucketOfWater.valid());
            if (potOfFlour.valid() && bucketOfWater.valid()) {
                potOfFlour.interact("Use");
                Condition.sleep(Helper.smartSecondsGen());
                bucketOfWater.interact(true, "Use");
            }
            Condition.sleep(2000);
        } else if (location.is(IslandLocation.COOKING_DOUGH)) {
            System.out.println("COOKING DOUGH");

            Item bread = ctx.inventory.select().id(ITEM.BREAD).poll();
            Item breadDough = ctx.inventory.select().id(ITEM.BREAD_DOUGH).poll();
            GameObject range = ctx.objects.select().id(OBJECTS.RANGE).poll();

            System.out.println("bread: " + !bread.valid());
            System.out.println("breadDough: " + breadDough.valid());
            System.out.println("range: " + range.valid());
            if (breadDough.valid() && range.valid() && !bread.valid()) {
                Condition.sleep(Helper.smartSecondsGen());
                range.interact("Cook");
            }
            Condition.sleep(2000);
        } else if (location.is(IslandLocation.COOKING)) {
            taskList.add(new CookingTutorial(ctx));
        } else if (location.is(IslandLocation.RAT_RAGING)) {
            System.out.println("Rat ranging");
//            taskList.add(new CombatTask(ctx, CombatTask.Type.RANGE));
            taskList.add(new CombatTask2(ctx, NPC.GIANT_RAT, CombatTask2.Type.RANGE));
        } else if (location.is(IslandLocation.MOVING_AFTER_RANGING)) {
            System.out.println("Moving forward after ranging");
            taskList.add(new WalkTask(ctx, AREA.bankStairsArea));
        } else if (location.is(IslandLocation.BANKING) || location.is(IslandLocation.YOUR_BANKING)) {
            System.out.println("Moving to banking area");

            taskList.add(new WalkTask(ctx, AREA.bankArea));
            taskList.add(new Banking(ctx));
            taskList.add(new SelectingPoll(ctx));
        } else if (location.is(IslandLocation.POLL_BOOTH) || location.is(IslandLocation.A_FLAG_APPEARS)) {
            System.out.println("Checking Poll");
            taskList.add(new WalkTask(ctx, AREA.pollArea));
            taskList.add(new SelectingPoll(ctx));

        } else if (location.is(IslandLocation.POLL_MOVING_ON) || location.is(IslandLocation.ACCOUNT_MANAGEMENT)) {
            System.out.println("Account Guide area");
            Component pollCloseBtn = ctx.widgets.component(310, 2, 11);
            if (pollCloseBtn.visible()) {
                pollCloseBtn.click();
            }

            taskList.add(new WalkTask(ctx, AREA.accountGuideArea));
            taskList.add(new TalkToEntityTask(ctx, NPC.ACCOUNT_GUIDE, 1));

        } else if (location.is(IslandLocation.ACCOUNT_MANAGEMENT_TAB)) {
            System.out.println("Account Management Tab");
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(TabChanger.AccountManagement);

        } else if (location.is(IslandLocation.ACCOUNT_MANAGEMENT_SECOND_TALK)) {
            System.out.println("Talking again with account guide");

            taskList.add(new WalkTask(ctx, AREA.accountGuideArea));
            taskList.add(new TalkToEntityTask(ctx, NPC.ACCOUNT_GUIDE, 1));

        } else if (location.is(IslandLocation.ACCOUNT_MANAGEMENT_MOVING_ON)) {
            System.out.println("Walking outside the area");
            taskList.add(new WalkTask(ctx, AREA.outsideAccountGuideArea));

        } else if (location.is(IslandLocation.PRAYER_ALTAR)) {
            System.out.println("Walking to chapel and talk to brother brace");
            taskList.add(new WalkTask(ctx, AREA.prayerChapelArea));
            taskList.add(new TalkToEntityTask(ctx, NPC.BROTHER_BRACE, 1));

        } else if (location.is(IslandLocation.PRAYER_MENU_TAB)) {
            System.out.println("Checking prayer tab");
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.PRAYER);

        } else if (location.is(IslandLocation.PRAYER_DESCRIPTION)) {
            System.out.println("Talking again with bother brace");
            taskList.add(new TalkToEntityTask(ctx, NPC.BROTHER_BRACE, 1));

        } else if (location.is(IslandLocation.FRIENDS_AND_IGNORE_LIST)) {
            System.out.println("Changing tabs to see friend and ignore list");
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.FRIENDS_LIST);

        } else if (location.is(IslandLocation.FRIENDS_AND_IGNORE_LIST_TALK)) {
            System.out.println("Talking about friend and ignore list");
            taskList.add(new TalkToEntityTask(ctx, NPC.BROTHER_BRACE, 1));

        } else if (location.is(IslandLocation.YOUR_FINAL_INSTRUCTOR)) {
            System.out.println("Last instruction, pass through the door");
            taskList.add(new WalkTask(ctx, AREA.outsideChapelSouthArea, OBJECTS.CHAPEL_DOOR));

        } else if (location.is(IslandLocation.WALKING_TO_WIZARDS_HOUSE)) {
            System.out.println("Walking to wizard");
            taskList.add(new WalkTask(ctx, AREA.wizardArea));
            taskList.add(new TalkToEntityTask(ctx, NPC.MAGIC_INSTRUCTOR, 1));

        } else if (location.is(IslandLocation.MAGIC_TABS)) {
            System.out.println("Switching to magic tab");
            TabChanger tabChanger = new TabChanger(ctx);
            tabChanger.changeTo(Game.Tab.MAGIC);
        } else if (location.is(IslandLocation.MAGIC_SPELLS_TALK)) {
            System.out.println("Magic spells talk");
            taskList.add(new TalkToEntityTask(ctx, NPC.MAGIC_INSTRUCTOR, 1));
        } else if (location.is(IslandLocation.MAGIC_KILL_A_CHICKEN)) {
            System.out.println("Spell kill a chicken");
            taskList.add(new WalkTask(ctx, AREA.closeToChicken));
            taskList.add(new CombatTask2(ctx, NPC.CHICKEN, CombatTask2.Type.MAGIC));
        } else if (location.is(IslandLocation.TO_MAINLAND)) {
            System.out.println("Talking to magician");
            TabChanger t = new TabChanger(ctx);
            t.changeTo(Game.Tab.INVENTORY);

            taskList.add(new TalkToMagic(ctx));

        } else if (location.is(IslandLocation.SETTING_APPEARANCE)) {
            taskList.add(new CustomizeAvatar(ctx));
        } else if (location.is(IslandLocation.GETTING_STARTED)) {
            taskList.add(new TalkToEntityTask(ctx, NPC.GIELINOR_GUIDE, 1));
        } else {
            TutorialLocation tl = new TutorialLocation(ctx);
            tl.smartChecker();

            if (tl.finishScript()) {
                System.out.println("switching off script");
                sw.switchOff();
                ctx.controller.stop();
            }

            System.out.println("pitch " + ctx.camera.pitch(99));

            return taskList;
        }
        return taskList;
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void poll() {
        if (sw.start()) {

            for (Task t : detectLocation()) {
                if (t.activate()) {
//                    System.out.println("Task started: " + count++);
                    t.execute();
                    break;
                }
            }

        }
    }


    private Component inputBox() {
        return ((ClientContext) this.ctx).widgets.component(162, 45);
    }

    public boolean pendingInput() {
        return this.inputBox().visible();
    }


}
