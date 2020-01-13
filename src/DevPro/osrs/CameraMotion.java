package osrs;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Camera;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class CameraMotion extends Task {
    public CameraMotion(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
//        System.out.println(ctx.camera.yaw());
//        System.out.println(ctx.camera.yaw() <= 260 && ctx.camera.yaw() >= 275);
        return ctx.camera.yaw() <= 260 || ctx.camera.yaw() >= 280;
    }

    @Override
    public void execute() {
//        System.out.println("Centering Camera");
        this.ctx.camera.angle('e');

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.camera.angle((int) 270);
            }
        }, 300, 10);
    }

    public void center() {

    }
}
