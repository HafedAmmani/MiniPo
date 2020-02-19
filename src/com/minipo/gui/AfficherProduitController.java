package com.minipo.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AfficherProduitController implements Initializable {

	@FXML
    private AnchorPane anchhorpane_parent;

    @FXML
    private Pane pane_top;

    @FXML
    private Label label_title;

    @FXML
    private AnchorPane anchorpane_center;

    @FXML
    private AnchorPane anchorpane_left;

    @FXML
    private JFXTextField txt_designation;

    @FXML
    private JFXTextField txt_qtestock;

    @FXML
    private JFXTextField txt_prix;

    @FXML
    private JFXComboBox<?> combo_categorie;

    @FXML
    private JFXComboBox<?> combo_fournisseur;

    @FXML
    private JFXButton btn_fournisseur;

    @FXML
    private JFXButton btn_sauvegarder;

    @FXML
    private AnchorPane anchorpane_right;

    @FXML
    private TableView<?> tblview;

    @FXML
    private TableColumn<?, ?> column_id;

    @FXML
    private TableColumn<?, ?> column_designation;

    @FXML
    private TableColumn<?, ?> column_qtestock;

    @FXML
    private TableColumn<?, ?> column_prix;

    @FXML
    private TableColumn<?, ?> column_categorie;

    @FXML
    private TableColumn<?, ?> column_fournisseur;

    @FXML
    private JFXButton btn_print_preview;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
