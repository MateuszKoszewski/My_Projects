package currencyCalculator.fx.ExchangePanelUtils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;



public class TextFieldVBox extends VBox {
    private Label label = new Label();
    private TextField textField = new TextField();


    public TextFieldVBox(String labels) {
        this.label.setText(labels);
        this.getChildren().addAll(label,textField);
    }



    public TextField getTextField() {
        return textField;
    }

}

