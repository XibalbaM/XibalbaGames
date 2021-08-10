package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.games.ui.panel.Panel;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GlobalSettingsPanel extends AMenuPanel {

    private final Panel oldPanel;

    public GlobalSettingsPanel(Panel lastPanel) {

        this.oldPanel = lastPanel;
    }

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        Text text = new Text("COMING SOON !");
        text.setFont(new Font(85));
        text.setTextOrigin(VPos.CENTER);
        text.setX(175);
        text.setY(250);
        text.setFill(Color.DARKGREEN);

        TextMenuButton back = new TextMenuButton("BACK", Font.font(40), 150, 35);
        back.setTranslateX(425);
        back.setTranslateY(375);
        back.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(this.oldPanel, false));

        root.getChildren().addAll(text, back);
    }

    @Override
    public String getName() {

        return "Global Settings Panel";
    }
}
