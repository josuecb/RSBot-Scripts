package osrs;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.Random;
import java.util.concurrent.Callable;

public class SmartCamera {
    ClientContext ctx;
    boolean _360 = false;
    int mainAngle = 0;

    public SmartCamera(ClientContext ctx, boolean _360) {
        this.ctx = ctx;
        this._360 = _360;
    }

    public void rotate360Camera(int intervals) {
//        System.out.println("Rotating: " + this.ctx.camera.yaw());
        this.ctx.camera.angle('e');
//        System.out.println("Current X   : " + ctx.camera.x());
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

//                System.out.println("Rotating: " + ctx.camera.yaw());
                mainAngle += new Random().nextInt(360 / intervals < 3 ? 2 : intervals) + 10;
                ctx.camera.angle(mainAngle);
                if (mainAngle % 2 == 0)
                    moveDownCamera();
                else
                    moveUpCamera();

                return mainAngle > 350 || Helper.checkIfStoppedThenShutDown(ctx);
            }
        }, Helper.smartSecondsGen() / (intervals < 3 ? 2 : intervals), intervals);
    }

    public void moveDownCamera() {
//        System.out.println("Moving all the way down");
        this.ctx.camera.pitch(false);
    }

    public void moveUpCamera() {
//        System.out.println("Moving all the way UP");
        this.ctx.camera.pitch(true);
    }
}
