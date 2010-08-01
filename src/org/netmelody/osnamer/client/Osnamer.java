package org.netmelody.osnamer.client;

import org.netmelody.osnamer.shared.FieldVerifier;
import org.netmelody.osnamer.shared.ProjectHostName;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Osnamer implements EntryPoint {

    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    private final FieldVerifier fieldVerifier = new FieldVerifier();
    private final ProjectNameLookupServiceAsync nameLookupService = GWT.create(ProjectNameLookupService.class);
    
    public void onModuleLoad() {
        final Button sendButton = new Button("Search");
        final TextBox nameField = new TextBox();
        final Label errorLabel = new Label();
        final Grid resultGrid = new Grid(ProjectHostName.values().length, 2);

        nameField.setText("junit");
        sendButton.addStyleName("sendButton");
        resultGrid.setVisible(false);
        
        int row = 0;
        for(ProjectHostName hostName : ProjectHostName.values()) {
            resultGrid.setHTML(row++, 0, hostName.toString());
        }
        
        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("sendButtonContainer").add(sendButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);
        RootPanel.get("resultContainer").add(resultGrid);


        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();

        // Create the popup dialog box
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Remote Procedure Call");
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button("Close");
        // We can set the id of a widget by accessing its Element
        closeButton.getElement().setId("closeButton");
        final Label textToServerLabel = new Label();
        final HTML serverResponseLabel = new HTML();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
        dialogVPanel.add(serverResponseLabel);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                sendButton.setEnabled(true);
                sendButton.setFocus(true);
            }
        });

        // Create a handler for the sendButton and nameField
        class MyHandler implements ClickHandler, KeyUpHandler {
            /**
             * Fired when the user clicks on the sendButton.
             */
            public void onClick(ClickEvent event) {
                sendNameToServer();
            }

            /**
             * Fired when the user types in the nameField.
             */
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendNameToServer();
                }
            }

            /**
             * Send the name from the nameField to the server and wait for a response.
             */
            private void sendNameToServer() {
                // First, we validate the input.
                errorLabel.setText("");
                String textToServer = nameField.getText();
                if (!fieldVerifier.isValidName(textToServer)) {
                    errorLabel.setText(fieldVerifier.getFailureMessage());
                    return;
                }

                // Then, we send the input to the server.
                textToServerLabel.setText(textToServer);
                serverResponseLabel.setText("");
                
                for (int i = 0; i < ProjectHostName.values().length; i++) {
                    resultGrid.setHTML(i, 1, "<img src=\"s.gif\"></img>");
                }
                resultGrid.setVisible(true);
                
                int row = 0;
                for(ProjectHostName hostName : ProjectHostName.values()) {
                    final int rowIndex = row;
                    nameLookupService.isInUse(hostName, textToServer, new AsyncCallback<Boolean>() {
                        public void onFailure(Throwable caught) {
                            // Show the RPC error message to the user
                            dialogBox.setText("Remote Procedure Call - Failure");
                            serverResponseLabel.addStyleName("serverResponseLabelError");
                            serverResponseLabel.setHTML(SERVER_ERROR);
                            dialogBox.center();
                            closeButton.setFocus(true);
                        }

                        public void onSuccess(Boolean result) {
                            resultGrid.setHTML(rowIndex, 1, Boolean.FALSE.equals(result)
                                    ? "<span style=\"color:green;\">FREE</span>"
                                    : "<span style=\"color:red;\">IN USE!</span>");
                        }
                    });
                    row++;
                }
            }
        }

        // Add a handler to send the name to the server
        MyHandler handler = new MyHandler();
        sendButton.addClickHandler(handler);
        nameField.addKeyUpHandler(handler);
    }
}
