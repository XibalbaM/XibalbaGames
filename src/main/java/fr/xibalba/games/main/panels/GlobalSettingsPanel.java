package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.games.ui.panel.Panel;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static fr.xibalba.games.main.GameCore.getHeight;
import static fr.xibalba.games.main.GameCore.getWidth;

public class GlobalSettingsPanel extends AMenuPanel {

    private final Panel oldPanel;
    private Text text;
    private TextMenuButton back;

    public GlobalSettingsPanel(Panel lastPanel) {

        this.oldPanel = lastPanel;
    }

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        text = new Text("ARRIVE BIENTOT !");
        text.setTextOrigin(VPos.CENTER);
        text.setFill(Color.DARKGREEN);

        back = new TextMenuButton("RETOUR", Font.font(40), 150, 35);
        back.setTranslateX(425);
        back.setTranslateY(375);
        back.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(this.oldPanel, false));

        root.getChildren().addAll(text, back);
    }

    @Override
    public void doSize() {

        super.doSize();

        text.setFont(new Font(getWidth() * 0.085));
        text.setX(getWidth() * 0.175);
        text.setY(getHeight() * 0.41);

        back.setSize(getWidth() * 0.15, getHeight() * 0.058, Font.font(getHeight() * 0.06));
        back.setTranslateX(getWidth() * 0.425);
        back.setTranslateY(getHeight() * 0.625);
    }

    @Override
    public String getName() {

        return "Global Settings Panel";
    }
}
