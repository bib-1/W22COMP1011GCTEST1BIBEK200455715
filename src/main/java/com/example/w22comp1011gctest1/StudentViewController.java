package com.example.w22comp1011gctest1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeSet;

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

    public StudentViewController() throws SQLException {
    }

//    @FXML
//    private void applyFilter() throws SQLException {
//
//        if(ontarioCheckBox.isSelected()){
//            tableView.getItems().clear();
//            tableView.getItems().addAll(DBUtility.getStudentsFromON("province = 'ON' "));
//        }
//        if(honourRollCheckBox.isSelected()){
//            tableView.getItems().clear();
//            tableView.getItems().addAll(DBUtility.getStudentsFromON("avgGrade >= 80"));
//        }
//        if(honourRollCheckBox.isSelected() && ontarioCheckBox.isSelected()){
//            tableView.getItems().clear();
//            tableView.getItems().addAll(DBUtility.getStudentsFromON("avgGrade >= 80 AND province = 'ON'"));
//        }
//
//        if(areaCodeComboBox.getSelectionModel().isSelected(1)) {
//            String code = areaCodeComboBox.getSelectionModel().getSelectedItem();
//
//            if (areaCodeComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("ALL")) {
//                tableView.getItems().clear();
//                tableView.getItems().addAll(DBUtility.getStudentsFromON("telephone is not NULL"));
//
//            } else
//            {
//                tableView.getItems().clear();
//                tableView.getItems().addAll(DBUtility.getStudentsFromON("telephone LIKE '" + code + "%'"));
//
//                if (ontarioCheckBox.isSelected()) {
//                    tableView.getItems().clear();
//                    tableView.getItems().addAll(DBUtility.getStudentsFromON("province = 'ON' AND telephone LIKE '" + code + "%'"));
//                }
//                if (honourRollCheckBox.isSelected()) {
//                    tableView.getItems().clear();
//                    tableView.getItems().addAll(DBUtility.getStudentsFromON("avgGrade >= 80 AND telephone LIKE '" + code + "%'"));
//                }
//                if (honourRollCheckBox.isSelected() && ontarioCheckBox.isSelected()) {
//                    tableView.getItems().clear();
//                    tableView.getItems().addAll(DBUtility.getStudentsFromON("avgGrade >= 80 AND province = 'ON' AND telephone LIKE '"+ code + "%'"));
//                }
//            }
//        }
//        numOfStudentsLabel.setText("Number of Students: " + String.valueOf(tableView.getItems().size()));
//
//
//    }

    @FXML
    private void applyFilter(){
        ArrayList<Student> filteredStudent = new ArrayList<>();
        filteredStudent.addAll(students);
        tableView.getItems().clear();
        for(Student student: students) {
            if (ontarioCheckBox.isSelected()) {
                if (!student.getProvince().equals("ON"))
                    filteredStudent.remove(student);
            }
            if (honourRollCheckBox.isSelected()) {
                  if (student.getAvgGrade() < 80)
                    filteredStudent.remove(student);
            }

                String areaCode = areaCodeComboBox.getSelectionModel().getSelectedItem();
                if(areaCode == null){
                    areaCode = "ALL";
                }
                if (!areaCode.equals("ALL")) {
                    if (!student.getTelephone().substring(0, 3).equals(areaCode))
                        filteredStudent.remove(student);
                }

        }

            tableView.getItems().addAll(filteredStudent);
            numOfStudentsLabel.setText("Number of Students: " + tableView.getItems().size());
        }


    ArrayList<Student> students= DBUtility.getStudentsFromDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        areaCodeComboBox.getItems().addAll("ALL");
        areaCodeComboBox.getItems().addAll(getAreaCode());
        getAreaCode();
        studentNumCol.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<>("avgGrade"));
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));


        tableView.getItems().addAll(students);

        numOfStudentsLabel.setText("Number of Students: " + String.valueOf(tableView.getItems().size()));

    }
    //method to get the area code
    private TreeSet<String> getAreaCode(){

        TreeSet areaCodes = new TreeSet();

        for(Student student: students){
            areaCodes.add(student.getTelephone().substring(0,3));
        }
        return areaCodes;
    }

}
