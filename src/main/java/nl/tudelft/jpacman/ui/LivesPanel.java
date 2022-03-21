package nl.tudelft.jpacman.ui;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.level.Player;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective lives underneath.
 *
 * @author Jeroen Roosen
 *
 */
public class LivesPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their scores are on.
     */
    private final Map<Player, JLabel> livesLabels;

    /**
     * The default way in which the score is shown.
     */
    public static final LivesFormatter DEFAULT_LIVES_FORMATTER =
        (Player player) -> String.format("Lives: %3d", player.getLives());

    /**
     * The way to format the score information.
     */
    private LivesFormatter livesFormatter = DEFAULT_LIVES_FORMATTER;

    /**
     * Creates a new score panel with a column for each player.
     *
     * @param players
     *            The players to display the scores of.
     */
    public LivesPanel(List<Player> players) {
        super();
        if( players == null){
            throw new IllegalArgumentException("players is NULL");
        }

        setLayout(new GridLayout(2, players.size()));

        for (int i = 1; i <= players.size(); i++) {
            add(new JLabel("Player " + i, JLabel.CENTER));
        }
        livesLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel livesLabel = new JLabel("0", JLabel.CENTER);
            livesLabels.put(player, livesLabel);
            add(livesLabel);
        }
    }

    /**
     * Refreshes the scores of the players.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : livesLabels.entrySet()) {
            Player player = entry.getKey();
            String lives = "";
            lives += livesFormatter.format(player);
            entry.getValue().setText(lives);
        }
    }



    /**
     * Provide means to format the score for a given player.
     */
    public interface LivesFormatter {

        /**
         * Format the score of a given player.
         * @param player The player and its score
         * @return Formatted score.
         */
        String format(Player player);
    }

    /**
     * Let the lives panel use a dedicated life formatter.
     * @param livesFormatter lives formatter to be used.
     */
    public void setLivesFormatter(LivesFormatter livesFormatter) {

        if( livesFormatter == null){
            throw new IllegalArgumentException("livesFormatter is NULL");
        }
        this.livesFormatter = livesFormatter;
    }
}
