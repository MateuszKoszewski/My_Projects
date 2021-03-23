package currencyCalculator.fx.ExchangePanelUtils;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LabelVBox extends VBox {
    private Label result = new Label();
    private Label labelName = new Label();

public LabelVBox (String label){
    this.labelName.setText(label);
    this.getChildren().addAll(labelName, result);

}

    public void setResult(String text) {
        this.result.setText(text);
    }


}
