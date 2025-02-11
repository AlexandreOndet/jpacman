package nl.tudelft.jpacman.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * A key listener based on a set of keyCode-action pairs.
 *
 * @author Jeroen Roosen 
 */
class PacKeyListener implements KeyListener {

    /**
     * The mappings of keyCode to action.
     */
    private final Map<Integer, Action> mappings;

    /**
     * Create a new key listener based on a set of keyCode-action pairs.
     * @param keyMappings The mappings of keyCode to action.
     */
    PacKeyListener(Map<Integer, Action> keyMappings) {
        assert keyMappings != null;
        this.mappings = keyMappings;
    }
    /** François : refactored assert event to if statement */
    @Override
    public void keyPressed(KeyEvent event) {
        if( event == null){
            throw new IllegalArgumentException("event is NULL");
        }
        Action action = mappings.get(event.getKeyCode());
        if (action != null) {
            action.doAction();
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // do nothing
    }

    @Override
    public void keyReleased(KeyEvent event) {
        // do nothing
    }
}
