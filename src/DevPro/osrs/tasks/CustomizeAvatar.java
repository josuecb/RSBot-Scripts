package osrs.tasks;

import org.powerbot.script.rt4.ClientContext;
import osrs.Avatar;
import osrs.Helper;
import osrs.Task;

import java.util.*;

public class CustomizeAvatar extends Task {
    Avatar avatar;
    ClientContext ctx;
    Integer[] twoOpts = {0, 1};
    Integer[] fourOpts = {0, 1, 2, 3};
    Integer[] eightOpts = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    boolean finished = false;

    public CustomizeAvatar(ClientContext ctx) {
        super(ctx);
        this.ctx = ctx;
        avatar = new Avatar(ctx);
    }

    @Override
    public boolean activate() {
        return !finished;
    }

    @Override
    public void execute() {
        List<Integer> options = Arrays.asList(eightOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    randomizeSex();
                    break;
                case 1:
                    randomizeJaw();
                    break;
                case 2:
                    randomizeTorso();
                    break;
                case 3:
                    randomizeArms();
                    break;
                case 4:
                    randomizeHands();
                    break;
                case 5:
                    randomizeLegs();
                    break;
                case 6:
                    randomizeFeet();
                    break;
                case 7:
                    randomizeSkin();
                    break;
                case 8:
                    randomizeHead();
                    break;
                default:
                    System.out.println("Not randomizing any mh");
                    break;
            }
        }

        finished = true;
        avatar.submitCharacter();
//        ctx.controller.stop();
    }

    public void randomizeHead() {
        List<Integer> options = Arrays.asList(fourOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    for (int count = 0; count < (new Random().nextInt(avatar.hairStylesCount)); count++) {
                        avatar.pickBackHair();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 1:
                    for (int count = 0; count < (new Random().nextInt(avatar.hairStylesCount)); count++) {
                        avatar.pickNextHair();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 2:
                    for (int count = 0; count < (new Random().nextInt(avatar.hairColorCount)); count++) {
                        avatar.pickBackHairColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 3:
                    for (int count = 0; count < (new Random().nextInt(avatar.hairColorCount)); count++) {
                        avatar.pickNextHairColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        }
    }

    public void randomizeJaw() {
        List<Integer> options = Arrays.asList(twoOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    for (int count = 0; count < (new Random().nextInt(avatar.jawStylesCount)); count++) {
                        avatar.pickBackJaw();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 1:
                    for (int count = 0; count < (new Random().nextInt(avatar.jawStylesCount)); count++) {
                        avatar.pickNextJaw();
                        Helper.smartSecondsGen();
                    }
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        }
    }

    public void randomizeTorso() {
        List<Integer> options = Arrays.asList(fourOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    for (int count = 0; count < (new Random().nextInt(avatar.torsoStylesCount)); count++) {
                        avatar.pickBackTorso();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 1:
                    for (int count = 0; count < (new Random().nextInt(avatar.torsoStylesCount)); count++) {
                        avatar.pickNextTorso();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 2:
                    for (int count = 0; count < (new Random().nextInt(avatar.torsoColorCount)); count++) {
                        avatar.pickBackTorsoColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 3:
                    for (int count = 0; count < (new Random().nextInt(avatar.torsoColorCount)); count++) {
                        avatar.pickNextTorsoColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        }
    }

    public void randomizeArms() {
        List<Integer> options = Arrays.asList(twoOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    for (int count = 0; count < (new Random().nextInt(avatar.armsStylesCount)); count++) {
                        avatar.pickBackArms();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 1:
                    for (int count = 0; count < (new Random().nextInt(avatar.armsStylesCount)); count++) {
                        avatar.pickNextArms();
                        Helper.smartSecondsGen();
                    }
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        }
    }

    public void randomizeHands() {
        List<Integer> options = Arrays.asList(twoOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    for (int count = 0; count < (new Random().nextInt(avatar.handsStylesCount)); count++) {
                        avatar.pickBackHands();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 1:
                    for (int count = 0; count < (new Random().nextInt(avatar.handsStylesCount)); count++) {
                        avatar.pickNextHands();
                        Helper.smartSecondsGen();
                    }
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        }
    }

    public void randomizeLegs() {
        List<Integer> options = Arrays.asList(fourOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    for (int count = 0; count < (new Random().nextInt(avatar.legsStylesCount)); count++) {
                        avatar.pickBackLegs();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 1:
                    for (int count = 0; count < (new Random().nextInt(avatar.legsStylesCount)); count++) {
                        avatar.pickNextLegs();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 2:
                    for (int count = 0; count < (new Random().nextInt(avatar.legsColorCount)); count++) {
                        avatar.pickBackLegsColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 3:
                    for (int count = 0; count < (new Random().nextInt(avatar.legsColorCount)); count++) {
                        avatar.pickNextLegsColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        }
    }

    public void randomizeFeet() {
        List<Integer> options = Arrays.asList(fourOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    for (int count = 0; count < (new Random().nextInt(avatar.feetStylesCount)); count++) {
                        avatar.pickBackFeet();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 1:
                    for (int count = 0; count < (new Random().nextInt(avatar.feetStylesCount)); count++) {
                        avatar.pickNextFeet();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 2:
                    for (int count = 0; count < (new Random().nextInt(avatar.feetColorCount)); count++) {
                        avatar.pickBackFeetColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 3:
                    for (int count = 0; count < (new Random().nextInt(avatar.feetColorCount)); count++) {
                        avatar.pickNextFeetColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        }
    }

    public void randomizeSkin() {
        List<Integer> options = Arrays.asList(twoOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        while (!optionTasks.isEmpty()) {
            switch (optionTasks.pop()) {
                case 0:
                    for (int count = 0; count < (new Random().nextInt(avatar.skinColorCount)); count++) {
                        avatar.pickBackSkinColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                case 1:
                    for (int count = 0; count < (new Random().nextInt(avatar.skinColorCount)); count++) {
                        avatar.pickNextSkinColor();
                        Helper.smartSecondsGen();
                    }
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        }
    }

    public void randomizeSex() {
        List<Integer> options = Arrays.asList(twoOpts);
        Collections.shuffle(options);

        System.out.println(options);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(options);

        switch (optionTasks.pop()) {
            case 0:
                avatar.pickFemale();
                Helper.smartSecondsGen();
                break;
            case 1:
                avatar.pickMale();
                Helper.smartSecondsGen();
                break;
            default:
                System.out.println("No option selected");
                break;
        }
    }
}
