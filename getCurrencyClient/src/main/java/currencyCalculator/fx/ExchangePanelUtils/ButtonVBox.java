package currencyCalculator.fx.ExchangePanelUtils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ButtonVBox extends VBox {
    private Button button = new Button();
    private Label label = new Label();

    public ButtonVBox (String labelName){
        this.label.setText(labelName);
        this.getChildren().addAll(label,button);
    }

    public Button getButton() {
        return button;
    }
}
