package com.example.w22comp1011gctest1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> studentNumCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> telephoneCol;

    @FXML
    private TableColumn<Student, String> addressCol;

    @FXML
    private TableColumn<Student, String> provinceCol;

    @FXML
    private TableColumn<Student, Integer> avgGradeCol;

    @FXML
    private TableColumn<Student, String> majorCol;

    @FXML
    private CheckBox ontarioCheckBox;

    @FXML
    private Label numOfStudentsLabel;

    @FXML
    private CheckBox honourRollCheckBox;

    @FXML
    private ComboBox<String> areaCodeComboBox;

    @FXML
    private void applyFilter() throws SQLException {
        if(ontarioCheckBox.isSelected()){
            tableView.getItems().clear();
            tableView.getItems().addAll(DBUtility.getStudentsFromON("province = 'ON' "));
            numOfStudentsLabel.setText("Number of Students: " + String.valueOf(tableView.getItems().size()));
        }
        if(honourRollCheckBox.isSelected()){
            tableView.getItems().clear();
            tableView.getItems().addAll(DBUtility.getStudentsFromON("avgGrade >= 80"));
            numOfStudentsLabel.setText("Number of Students: " + String.valueOf(tableView.getItems().size()));
        }
        if(honourRollCheckBox.isSelected() && ontarioCheckBox.isSelected()){
            tableView.getItems().clear();
            tableView.getItems().addAll(DBUtility.getStudentsFromON("avgGrade >= 80 AND province = 'ON'"));
            numOfStudentsLabel.setText("Number of Students: " + String.valueOf(tableView.getItems().size()));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        areaCodeComboBox.getItems().addAll("416", "905", "519", "647", "705");

        studentNumCol.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<>("avgGrade"));
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));

        try {
            tableView.getItems().addAll(DBUtility.getStudentsFromDB());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        numOfStudentsLabel.setText("Number of Students: " + String.valueOf(tableView.getItems().size()));

    }

}
