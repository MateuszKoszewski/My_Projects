package currencyCalculator.fx;


import currencyCalculator.fx.ExchangePanelUtils.ButtonVBox;
import currencyCalculator.fx.ExchangePanelUtils.LabelVBox;
import currencyCalculator.fx.ExchangePanelUtils.TextFieldVBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangePanel extends HBox {
    private TextFieldVBox currencyFrom;
    private TextFieldVBox currencyTo;
    private TextFieldVBox amount;

    private ButtonVBox button;
    private LabelVBox result;

    public ExchangePanel(){
        this.currencyFrom = new TextFieldVBox("currencyFrom");
        this.currencyTo = new TextFieldVBox("currencyTo");
        this.amount = new TextFieldVBox("amount");
        this.button = new ButtonVBox("calculate     ");
        this.result = new LabelVBox("result");

        this.getChildren().addAll(currencyFrom, currencyTo, amount, button, result);
this.amount.setOnKeyTyped(key -> {
    char keyAsChar = key.getCharacter().toCharArray()[0];
    boolean isDigitOrNot = Character.isDigit(keyAsChar) || keyAsChar == '.';
   if (!isDigitOrNot){
       String valueBeforeKeyPressed = this.amount.getTextField().getText().substring(0, this.amount.getTextField().getText().length()-1);
       this.amount.getTextField().setText(valueBeforeKeyPressed);
   }
});

        this.button.getButton().setOnMouseClicked(click -> {
            String from = currencyFrom.getTextField().getText();
            String to = currencyTo.getTextField().getText();
            String value = amount.getTextField().getText();
            BigDecimal exchange = HelloWorld.getService().exchange(from, to, LocalDate.now(), BigDecimal.valueOf(Double.parseDouble(value)));
            result.setResult(exchange.toString());
        });

    }

}
