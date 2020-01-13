package osrs;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Helper {
    public static final int fishMaxCount = 5;
    public static int treeMaxCount = 3;

    public static void sleepToClick() {
        Condition.sleep(Helper.smartSecondsGen());
    }

    public static int smartSecondsGen() {
        return new Random().nextInt(50) + (new Random().nextInt(1000) + 200);
    }

    public static List<Integer> optionIndexGen(int size) {
        int[] optionCount = IntStream.range(0, size).toArray();
        return Arrays.asList(Arrays.stream(optionCount).boxed().toArray(Integer[]::new));
    }

    public static int actualFishCount(int reduceCount) {
        return Helper.fishMaxCount - reduceCount;
    }

    public static int actualTreeCount(int reduceCount) {
        return Helper.treeMaxCount - reduceCount;
    }

    public static boolean checkIfStoppedThenShutDown(ClientContext ctx) {
        if (ctx.controller.isStopping()) {
            ctx.controller.stop();
            return true;
        } else return false;
    }
}
