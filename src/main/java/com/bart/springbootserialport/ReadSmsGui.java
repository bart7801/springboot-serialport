package com.bart.springbootserialport;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class ReadSmsGui extends VerticalLayout {

    private final TextField textFieldMemoryPlace;
    private final Button buttonReadSms;
    private final ModemConnection modemConnection;
    private Label label;

    @Autowired
    public ReadSmsGui(ModemConnection modemConnection) {
        this.modemConnection = modemConnection;
        textFieldMemoryPlace = new TextField("put 0 or 1 to see details");
        label = new Label();
        buttonReadSms = new Button("read <SMS>");
        add(textFieldMemoryPlace, buttonReadSms, label);

        buttonReadSms.addClickListener(ClickEvent -> {
            String sms = modemConnection.readSms(textFieldMemoryPlace.getValue());
            label.setText(sms);
        });
    }
}