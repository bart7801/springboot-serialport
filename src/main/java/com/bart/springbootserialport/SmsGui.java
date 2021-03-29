package com.bart.springbootserialport;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class SmsGui extends VerticalLayout {

    private final TextField textFieldNumber;
    private final TextField textFieldMessage;
    private final Button buttonSendSms;
    private final ModemConnection modemConnection;

    @Autowired
    public SmsGui(ModemConnection modemConnection) {
        this.modemConnection = modemConnection;
        textFieldNumber = new TextField();
        textFieldNumber.setPlaceholder("tel. number");
        textFieldMessage = new TextField();
        textFieldMessage.setPlaceholder("message");
        buttonSendSms = new Button("send <SMS> !!!");
        add(textFieldNumber, textFieldMessage, buttonSendSms);

        buttonSendSms.addClickListener(ClickEvent -> {
            modemConnection.sendSms(textFieldNumber.getValue(), textFieldMessage.getValue());
        });
    }
}