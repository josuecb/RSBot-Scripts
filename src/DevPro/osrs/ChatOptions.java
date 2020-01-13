package osrs;


import com.sun.deploy.util.ArrayUtil;
import org.powerbot.script.rt4.Chat;
import org.powerbot.script.rt4.ChatOption;
import org.powerbot.script.rt4.ClientContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class ChatOptions extends Chat {
    public ChatOptions(ClientContext ctx) {
        super(ctx);
    }

    public List<ChatOption> getOptions() {
        return this.get();
    }

    public String getRandomOption() {
        List<Integer> opts = Helper.optionIndexGen(this.get().size());
        Collections.shuffle(opts);

//        System.out.println(opts);
        Stack<Integer> optionTasks = new Stack<Integer>();
        optionTasks.addAll(opts);

        if (optionTasks.size() > 0) {
            System.out.println(this.get().get(optionTasks.pop()).text());
            return this.get().get(optionTasks.pop()).text();
        } else {
            return null;
        }
    }
}
