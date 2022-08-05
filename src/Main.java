import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.JDialog;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends JDialog implements NativeKeyListener {
    LinkedList<String> result = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Logger.getLogger(GlobalScreen.class.getPackageName()).setLevel(Level.OFF);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new Main());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        char ch = nativeKeyEvent.getKeyChar();
        if((int)ch >= 32 && (int)ch <= 127) {
            result.addLast(String.valueOf(ch));
        }
        else if ((int)ch == 13){
            result.addLast("[ENTER]");
        }
        else if ((int)ch == 8) {
            result.pollLast();
        }
        System.out.print("\r" + String.join("", result));
    }
    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {}

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {}
}
