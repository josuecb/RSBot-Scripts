package osrs.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import osrs.Helper;
import osrs.Task;
import osrs.Walker;
import org.powerbot.script.Area;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class WalkTask extends Task {
    Walker w;
    Area area;
    String[] avoidTypes;
    Tile specificTile;
    int rotateTo;

    public WalkTask(ClientContext ctx, Area area) {
        super(ctx);
        this.area = area;
        this.avoidTypes = new String[]{};
        this.rotateTo = -1;
    }

    public WalkTask(ClientContext ctx, Tile specificTile) {
        super(ctx);
        this.area = null;
        this.specificTile = null;
        this.specificTile = specificTile;
        this.avoidTypes = new String[]{};
        this.rotateTo = -1;

    }

    public WalkTask(ClientContext ctx, Area area, String[] avoidBoundaryTypes) {
        super(ctx);
        this.area = area;
        this.specificTile = null;
        this.avoidTypes = avoidBoundaryTypes;
        this.rotateTo = -1;
    }

    public WalkTask(ClientContext ctx, Area area, int rotateTo) {
        super(ctx);
        this.area = area;
        this.specificTile = null;
        this.avoidTypes = new String[]{};
        this.rotateTo = rotateTo;
    }

    public WalkTask(ClientContext ctx, Tile specificTile, int rotateTo) {
        super(ctx);
        this.area = null;
        this.specificTile = null;
        this.specificTile = specificTile;
        this.avoidTypes = new String[]{};
        this.rotateTo = rotateTo;

    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() &&
                (area != null && !area.containsOrIntersects(ctx.players.local())
                        || (area == null && ctx.players.local().tile().distanceTo(this.specificTile) > 1));
    }

    @Override
    public void execute() {
//        System.out.println("Start Walking");


        Tile randomTile = this.area == null ? this.specificTile : this.area.getRandomTile();
        Tile[] tiles = {ctx.players.local().tile(), randomTile};

//        System.out.println("Random Tile: " + randomTile.toString());
//        System.out.println(Arrays.toString(tiles));
        w = new Walker(ctx);

        for (String avoidType : this.avoidTypes)
            w.avoidChecking(avoidType);

        w.walkPath(tiles);

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
//                System.out.println("Waiting");
                if (rotateTo > 0)
                    ctx.camera.turnTo(ctx.objects.select().id(rotateTo).nearest().poll());
                return ctx.players.local().inMotion() || Helper.checkIfStoppedThenShutDown(ctx);
            }
        }, Helper.smartSecondsGen(), 5);
    }
}
