/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.client.fx.core.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.annotation.PostConstruct;

import org.openwms.client.fx.core.FXMLDialog;
import org.openwms.client.fx.core.controller.AbstractSceneController;

/**
 * A DossierBrowser.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * 
 */
public class DossierBrowser extends AbstractSceneController {

    private FXMLDialog dialog;
    @FXML
    Text sourceTxt;
    @FXML
    TextArea targetTxt;
    private boolean initialized;
    @FXML
    Rectangle rect;

    @PostConstruct
    public void onPostConstruct() {
        System.out.println("postconstruct");
    }

    /**
     * @see org.openwms.client.fx.core.controller.DialogController#setDialog(org.openwms.client.fx.core.FXMLDialog)
     */
    @Override
    public void setDialog(FXMLDialog dialog) {
        this.dialog = dialog;
    }

    @FXML
    public void login() {
        if (!initialized) {
            System.out.println("Test");
            addHandler();
            initialized = true;
        }
    }

    private void addHandler() {
        targetTxt.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != targetTxt && event.getDragboard().hasString()) {
                    // targetTxt.setFill(Color.GREEN);
                }

                event.consume();
            }
        });

        targetTxt.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                // targetTxt.setFill(Color.RED);

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    targetTxt.setText(db.getString());
                    success = true;
                }

                for (DataFormat df : db.getContentTypes()) {
                    System.out.println(df.toString());
                    Object obj = db.getContent(df);
                    System.out.println(obj);
                }
                if (db.hasFiles()) {
                    List<File> files = db.getFiles();
                    File file = files.get(0);

                    BufferedReader br = null;

                    try {

                        String sCurrentLine;
                        StringBuilder sb = new StringBuilder();
                        br = new BufferedReader(new FileReader(file));
                        while ((sCurrentLine = br.readLine()) != null) {
                            sb.append(sCurrentLine);
                        }
                        targetTxt.setText(sb.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (br != null) br.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        event.setDropCompleted(success);
                    }

                }
                /*
                 * let the source know whether the string was successfully
                 * transferred and used
                 */

            }
        });

        targetTxt.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    targetTxt.setText(db.getString());
                    success = true;
                }
                /*
                 * let the source know whether the string was successfully
                 * transferred and used
                 */
                event.setDropCompleted(success);

                event.consume();
            }
        });
        rect.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = rect.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString("Hello!");
                db.setContent(content);
                event.consume();
            }
        });
        sourceTxt.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");
                sourceTxt.setFill(Color.YELLOW);
                /*
                 * accept it only if it is not dragged from the same node and if
                 * it has a string data
                 */
                if (event.getGestureSource() != sourceTxt && event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });
    }
}
