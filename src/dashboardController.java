import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private Button coaches_addBtn;

    @FXML
    private TextArea coaches_address;

    @FXML
    private TextField coaches_coachId;

    @FXML
    private TableColumn<coachData, String> coaches_col_address;

    @FXML
    private TableColumn<coachData, String> coaches_col_coachId;

    @FXML
    private TableColumn<coachData, String> coaches_col_gender;

    @FXML
    private TableColumn<coachData, String> coaches_col_name;

    @FXML
    private TableColumn<Class, Integer> coaches_col_phoneNumber;

    @FXML
    private TableColumn<coachData, String> coaches_col_status;

    @FXML
    private Button coaches_deleteBtn;

    @FXML
    private AnchorPane coaches_form;

    @FXML
    private ComboBox<?> coaches_gender;

    @FXML
    private TextField coaches_name;

    @FXML
    private TextField coaches_phoneNumber;

    @FXML
    private Button coaches_resetBtn;

    @FXML
    private ComboBox<?> coaches_status;

    @FXML
    private TableView<coachData> coaches_tableView;

    @FXML
    private Button coaches_updateBtn;

    @FXML
    private AnchorPane dadashboad_form;

    @FXML
    private Label dashboad_NC;

    @FXML
    private Label dashboad_NM;

    @FXML
    private Label dashboad_TI;

    @FXML
    private AreaChart<?, ?> dashboad_incomeChart;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private DatePicker member_EndDate;

    @FXML
    private Button member_addBtn;

    @FXML
    private TextArea member_address;

    @FXML
    private Button member_clearBtn;

    @FXML
    private TableColumn<memberData, String> member_col_address;

    @FXML
    private TableColumn<memberData, String> member_col_customerId;

    @FXML
    private TableColumn<memberData, String> member_col_endDate;

    @FXML
    private TableColumn<memberData, String> member_col_gender;

    @FXML
    private TableColumn<memberData, String> member_col_name;

    @FXML
    private TableColumn<memberData, String> member_col_phoneNumber;

    @FXML
    private TableColumn<memberData, String> member_col_shedule;

    @FXML
    private TableColumn<memberData, String> member_col_startDate;

    @FXML
    private TableColumn<memberData, String> member_col_status;

    @FXML
    private TextField member_customerId;

    @FXML
    private Button member_deleteBtn;

    @FXML
    private AnchorPane member_form;

    @FXML
    private ComboBox<?> member_gender;

    @FXML
    private TextField member_name;

    @FXML
    private TextField member_phoneNumber;

    @FXML
    private ComboBox<?> member_shedule;

    @FXML
    private DatePicker member_startDate;

    @FXML
    private ComboBox<?> member_status;

    @FXML
    private TableView<memberData> member_tableView;

    @FXML
    private Button member_updateBtn;

    @FXML
    private Button minimize;

    @FXML
    private AnchorPane payment_Form;

    @FXML
    private TextField payment_amount;

    @FXML
    private Label payment_change;

    @FXML
    private TableColumn<memberData, String> payment_col_customerId;

    @FXML
    private TableColumn<memberData, String> payment_col_endDate;

    @FXML
    private TableColumn<memberData, String> payment_col_name;

    @FXML
    private TableColumn<memberData, String> payment_col_startDate;

    @FXML
    private TableColumn<memberData, String> payment_col_status;

    @FXML
    private ComboBox<?> payment_customerId;

    @FXML
    private ComboBox<?> payment_name;

    @FXML
    private Button payment_payBtn;

    @FXML
    private TableView<memberData> payment_tableView;

    @FXML
    private Label payment_total;

    @FXML
    private Button coaches_btn;

    @FXML
    private Button member_btn;

    @FXML
    private Button payment_btn;

    @FXML
    private Button dashboard_btn;

    private Connection connect;
    private PreparedStatement prepared;
    private ResultSet result;
    private Statement statement;

    public void emptyFields(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blanks fields");
        alert.showAndWait();
    }

    public void dashboardNM(){

        String sql = "select COUNT(id) from member where status = 'Paid' ";

        connect = database.connectDb();

        int nm = 0 ;

        try{

            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();

            if (result.next()){
                nm = result.getInt("count(id)");
            }
            dashboad_NM.setText(String.valueOf(nm));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dashboardTC(){

        String sql = "select COUNT(id) from coach where status = 'Active' ";

        connect = database.connectDb();

        int tc = 0;
        try{
            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();

            if (result.next()){
                tc = result.getInt("COUNT(id)");
            }
            dashboad_NC.setText(String.valueOf(tc));

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void dashboardTI(){

        String sql = "select SUM(price) from member where status = 'Paid' ";

        connect = database.connectDb();

        double ti = 0 ;
        try{
            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();

            if(result.next()){
                ti = result.getInt("SUM(price)");
            }

            dashboad_TI.setText(String.valueOf("$ "+ ti));


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dashboardChart(){

        dashboad_incomeChart.getData().clear();

        String sql = "SELECT startDate, SUM(price) FROM member WHERE status = 'Paid' GROUP BY startDate ORDER BY TIMESTAMP(startDate) ASC LIMIT 10 " ;

        connect = database.connectDb();

        XYChart.Series chart = new XYChart.Series();

        try {
            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();

            while (result.next()){

                chart.getData().add(new XYChart.Data(result.getString(1),result.getDouble(2)));

            }

            dashboad_incomeChart.getData().add(chart);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void coachesAddButton(){

        String sql = "insert into coach (coachId, name, address, gender, phoneNumber, status) " + "values(?,?,?,?,?,?)";

        connect = database.connectDb();
        try {

            Alert alert ;

            if (coaches_coachId.getText().isEmpty() || coaches_name.getText().isEmpty() || coaches_address.getText().isEmpty() || coaches_gender.getSelectionModel().getSelectedItem() == null || coaches_phoneNumber.getText().isEmpty() || coaches_status.getSelectionModel().getSelectedItem() == null ){
                emptyFields();
            }else{

                String checkData = "select coachId from coach where coachId = '"+coaches_coachId+"' ";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Coach ID " + coaches_coachId + "was allready taken!" );
                    alert.showAndWait();

                }else{
                    prepared = connect.prepareStatement(sql);

                    prepared.setString(1,coaches_coachId.getText());
                    prepared.setString(2,coaches_name.getText());
                    prepared.setString(3,coaches_address.getText());
                    prepared.setString(4,(String)coaches_gender.getSelectionModel().getSelectedItem());
                    prepared.setString(5,coaches_phoneNumber.getText());
                    prepared.setString(6,(String)coaches_status.getSelectionModel().getSelectedItem());

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!" );
                    alert.showAndWait();

                    prepared.executeUpdate();
                    setCochesShowtData();
                    coachesClearBtn();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void coachUpdateBtn(){

        String sql = "update coach set name = '"
                +coaches_name.getText() +"', address ='"
                +coaches_address.getText() +"', gender = '"
                +coaches_gender.getSelectionModel().getSelectedItem()+"', phoneNumber = '"
                +coaches_phoneNumber.getText()+"', status = '"
                +coaches_status.getSelectionModel().getSelectedItem()+"' where coachId = '"+coaches_coachId.getText()+"' ";


        connect = database.connectDb();

        try{
            Alert alert;

            if (coaches_coachId.getText().isEmpty()
                    || coaches_name.getText().isEmpty()
                    || coaches_address.getText().isEmpty()
                    || coaches_gender.getSelectionModel().getSelectedItem() == null
                    || coaches_phoneNumber.getText().isEmpty()
                    || coaches_status.getSelectionModel().getSelectedItem() == null ){
                emptyFields();
            }else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to UPDATE coach ID : "+ coaches_coachId.getText()+ " ? " );
                alert.showAndWait();
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){

                    prepared = connect.prepareStatement(sql);
                    prepared.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!" );
                    alert.showAndWait();

                    setCochesShowtData();
                    coachesClearBtn();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update ! " );
                    alert.showAndWait();

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void coachesDeleteBtn(){

        String sql = "delete from coach where coachid ='"+coaches_coachId.getText()+"' ";


        connect = database.connectDb();

        try{
            Alert alert;

            if (coaches_coachId.getText().isEmpty()
                    || coaches_name.getText().isEmpty()
                    || coaches_address.getText().isEmpty()
                    || coaches_gender.getSelectionModel().getSelectedItem() == null
                    || coaches_phoneNumber.getText().isEmpty()
                    || coaches_status.getSelectionModel().getSelectedItem() == null ){
                emptyFields();
            }else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to Delete coach ID : "+ coaches_coachId.getText()+ " ? " );
                alert.showAndWait();
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){

                    prepared = connect.prepareStatement(sql);
                    prepared.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Delete!" );
                    alert.showAndWait();

                    setCochesShowtData();
                    coachesClearBtn();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update ! " );
                    alert.showAndWait();

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public void coachesClearBtn(){
        coaches_coachId.setText("");
        coaches_name.setText("");
        coaches_address.setText("");
        coaches_gender.getSelectionModel().clearSelection();
        coaches_phoneNumber.setText("");
        coaches_status.getSelectionModel().clearSelection();
    }


    public ObservableList<coachData> coachListData(){

        ObservableList listData = FXCollections.observableArrayList();

        String sql = "select * from coach";

        connect = database.connectDb();

        try {

            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();

            coachData cd ;

            while(result.next()){
                cd = new coachData(result.getInt("id"),
                        result.getString("coachId"),
                        result.getString("name"),
                        result.getString("address"),
                        result.getString("gender"),
                        result.getInt("phoneNumber"),
                        result.getString("status"));

                listData.add(cd);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<coachData> coachesListData;

    public void setCochesShowtData(){

        coachesListData = coachListData();

        coaches_col_coachId.setCellValueFactory(new PropertyValueFactory<>("coachId"));
        coaches_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        coaches_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        coaches_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        coaches_col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        coaches_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

         coaches_tableView.setItems(coachesListData);

    }

    public void coachesSelect(){
        coachData cd = coaches_tableView.getSelectionModel().getSelectedItem();
        int num = coaches_tableView.getSelectionModel().getSelectedIndex();

        if((num -1 ) < - 1) return;

        coaches_coachId.setText(cd.getCoachId());
        coaches_name.setText(cd.getName());
        coaches_address.setText(cd.getAddress());
        coaches_phoneNumber.setText(String.valueOf(cd.getPhoneNumber()));

    }

    private String gender[] ={"Male","Female","Other"};

    public void coachGenderList(){

        List<String> genderList = new ArrayList<>();

        for (String data : gender){
            genderList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(genderList);
        coaches_gender.setItems(listData);
    }

    private String coachStatus[] = {"Active","Inactive"};

    public void coachStatusList(){

        List<String> statusList = new ArrayList<>();

        for (String data : coachStatus){
            statusList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(statusList);
        coaches_status.setItems(listData);
    }

    private int totalDay;
    private double price;

    public void memberAddBtn(){

        String sql = "insert into member (memberId,name,address,phoneNumber,gender,schedule,startDate,endDate,price,status)" + " values (?,?,?,?,?,?,?,?,?,?) ";

        connect = database.connectDb();

        try{
            Alert alert;

            if (member_customerId.getText().isEmpty()
                    || member_name.getText().isEmpty()
                    || member_address.getText().isEmpty()
                    || member_phoneNumber.getText().isEmpty()
                    || member_gender.getSelectionModel().getSelectedItem() == null
                    || member_shedule.getSelectionModel().getSelectedItem() == null
                    || member_startDate.getValue() == null
                    || member_EndDate.getValue() == null
                    || member_status.getSelectionModel().getSelectedItem() == null){
                emptyFields();

            }else {

                String chechData = "select memberId from member where memberId = '"+member_customerId.getText()+"' ";

                statement = connect.createStatement();
                result = statement.executeQuery(chechData);

                if (result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Member ID : '" + member_customerId + "' was allrady taken ! ");
                    alert.showAndWait();
                }else {

                    prepared = connect.prepareStatement(sql);
                    prepared.setString(1, member_customerId.getText());
                    prepared.setString(2, member_name.getText());
                    prepared.setString(3, member_address.getText());
                    prepared.setString(4, member_phoneNumber.getText());
                    prepared.setString(5, (String) member_gender.getSelectionModel().getSelectedItem());
                    prepared.setString(6, (String) member_shedule.getSelectionModel().getSelectedItem());
                    prepared.setString(7, String.valueOf(member_startDate.getValue()));
                    prepared.setString(8, String.valueOf(member_EndDate.getValue()));

                    totalDay = (int) ChronoUnit.DAYS.between(member_startDate.getValue(), member_EndDate.getValue());

                    price = (totalDay * 50);

                    prepared.setString(9, String.valueOf(price));
                    prepared.setString(10, (String) member_status.getSelectionModel().getSelectedItem());

                    prepared.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("succesfuly added! ");
                    alert.showAndWait();

                    memberShowData();
                    memberClearBtn();
                }
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public void memberUpdatBtn(){

        totalDay = (int) ChronoUnit.DAYS.between(member_startDate.getValue(),member_EndDate.getValue());
        price = (totalDay * 50);


        String sql = "update member set name = '"
                +member_name.getText()+ "', address = '"
                + member_address.getText() + "', phoneNumber = '"
                + member_phoneNumber.getText() + "', gender = '"
                + member_gender.getSelectionModel().getSelectedItem()+ "', schedule ='"
                + member_shedule.getSelectionModel().getSelectedItem() +"', startDate = '"
                + member_startDate.getValue() + "', endDate = '"
                + member_EndDate.getValue() + "',  price = '"
                + String.valueOf(price) + "',  status = '" + member_status.getSelectionModel().getSelectedItem() + "' where memberId = '"
                + member_customerId.getText()+ "' ";

        connect = database.connectDb();

        try{
            Alert alert;

            if (member_customerId.getText().isEmpty()
                    || member_name.getText().isEmpty()
                    || member_address.getText().isEmpty()
                    || member_phoneNumber.getText().isEmpty()
                    || member_gender.getSelectionModel().getSelectedItem() == null
                    || member_shedule.getSelectionModel().getSelectedItem() == null
                    || member_startDate.getValue() == null
                    || member_EndDate.getValue() == null
                    || member_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();

            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to UPDATE MEMBER ID: " +member_customerId.getText()+ " ? ");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    prepared = connect.prepareStatement( sql);
                    prepared.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("succesfuly Updated! ");
                    alert.showAndWait();

                    memberShowData();
                    memberClearBtn();
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update! ");
                    alert.showAndWait();
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void memberDeleteBtn(){

        String sql = "delete from member where memberId = '"+member_customerId.getText()+"' ";

        connect = database.connectDb();

        try {
            Alert alert;

            if (member_customerId.getText().isEmpty()
                    || member_name.getText().isEmpty()
                    || member_address.getText().isEmpty()
                    || member_phoneNumber.getText().isEmpty()
                    || member_gender.getSelectionModel().getSelectedItem() == null
                    || member_shedule.getSelectionModel().getSelectedItem() == null
                    || member_startDate.getValue() == null
                    || member_EndDate.getValue() == null
                    || member_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to Delete MEMBER ID: " + member_customerId.getText() + " ? ");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepared = connect.prepareStatement(sql);
                    prepared.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("succesfuly Delete! ");
                    alert.showAndWait();

                    memberShowData();
                    memberClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Delete! ");
                    alert.showAndWait();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void memberClearBtn(){
        member_customerId.setText("");
        member_name.setText("");
        member_address.setText("");
        member_phoneNumber.setText("");
        member_gender.getSelectionModel().clearSelection();
        member_shedule.getSelectionModel().clearSelection();
        member_startDate.setValue(null);
        member_EndDate.setValue(null);
        member_status.getSelectionModel().clearSelection();
    }

    public void memberGender(){
        List<String> genderList = new ArrayList<>();

        for(String data : gender){

            genderList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(genderList);
        member_gender.setItems(listData);
    }

    private String [] scheduleList = {"9AM - 11AM ", "11AM - 1 PM ","1PM - 3PM","3PM - 5PM","5PM - 7PM", };
    public void memberSchedule(){
        List<String> sl = new ArrayList<>();

        for(String data : scheduleList){

            sl.add(data);

            ObservableList listData = FXCollections.observableArrayList(sl);
            member_shedule.setItems(listData);
        }
    }

    private String [] memberStatus = {"Paid" , "Unpaid"};
    public void memberStatus(){

        List<String> ms = new ArrayList<>();

        for (String data : memberStatus){
            ms.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(memberStatus);
        member_status.setItems(listData);


    }

    public ObservableList<memberData> membersDataList(){

        ObservableList<memberData> listData = FXCollections.observableArrayList();

        String sql = "select * from member";

        connect =database.connectDb();

        try{
            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();


            memberData md;

            while(result.next()){

                md = new memberData(result.getInt("id")
                        ,result.getString("memberId")
                        ,result.getString("name")
                        ,result.getString("address")
                        ,result.getInt("phoneNumber")
                        ,result.getString("gender")
                        ,result.getString("schedule")
                        ,result.getDate("startDate")
                        ,result.getDate("endDate")
                        ,result.getDouble("price")
                        ,result.getString("status"));

                listData.add(md);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<memberData> membersListData;

    public void memberShowData(){
        membersListData = membersDataList();

        member_col_customerId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        member_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        member_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        member_col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        member_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        member_col_shedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        member_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        member_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        member_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        member_tableView.setItems(membersListData);
    }

    public void memberSelect(){

        memberData md = member_tableView.getSelectionModel().getSelectedItem();
        int num = member_tableView.getSelectionModel().getSelectedIndex();


        if((num -1 ) < - 1) return;

        member_customerId.setText(md.getMemberId());
        member_name.setText(md.getName());
        member_address.setText(md.getAddress());
        member_phoneNumber.setText(String.valueOf(md.getPhoneNumber()));
        member_startDate.setValue(LocalDate.parse(String.valueOf(md.getStartDate())));
        member_EndDate.setValue(LocalDate.parse(String.valueOf(md.getEndDate())));

    }

    public void paymentMemberID(){

        String sql = "select memberId from member where status = 'Unpaid' ";

        connect = database.connectDb();

        try {

            ObservableList listDate = FXCollections.observableArrayList();

            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();

            while (result.next()){
                listDate.add(result.getString("memberid"));

            }

            payment_customerId.setItems(listDate);

            paymentName();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void paymentName(){

        String sql = "select name from member where memberId = '" + payment_customerId.getSelectionModel().getSelectedItem() + "' ";

        connect = database.connectDb();

        try {
            ObservableList listData = FXCollections.observableArrayList();

            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();

            if (result.next()){
                listData.add(result.getString("name"));

            }

            payment_name.setItems(listData);

            paymentDisplayTotal();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private double totalP;
    public void displayTotal(){

         String sql = "select price from member where name = '"
                 + payment_name.getSelectionModel().getSelectedItem()+"' and memberId = '"
                 + payment_customerId.getSelectionModel().getSelectedItem()+"' ";

         connect = database.connectDb();

         try {

             prepared = connect.prepareStatement(sql);
             result = prepared.executeQuery();

             if (result.next()){
                 totalP = result.getDouble("price");
             }

             payment_total.setText("$" + String.valueOf(totalP));

         }catch (Exception e){
             e.printStackTrace();
         }

    }
    public void paymentDisplayTotal(){

        displayTotal();
        payment_total.setText("$" + String.valueOf(totalP));
    }



    private double amount;
    private double change;
    public void paymentAmount(){
            displayTotal();

            Alert alert;

            if (payment_amount.getText().isEmpty() || totalP == 0 ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error Message");
                alert.setContentText("Invalide :3");
                alert.showAndWait();

                payment_amount.setText("");
            }else{
                amount = Double.parseDouble(payment_amount.getText());

                if (amount >= totalP){

                    change = (amount - totalP);
                    payment_change.setText("$" + String.valueOf(change));

                }else {
                    payment_amount.setText("");
                    change = 0;
                    amount = 0;

                }
            }
    }
    public void paymentPayBtn(){

        String sql = "update member set status = 'Paid' where memberId = '"+payment_customerId.getSelectionModel().getSelectedItem()+"' ";

        connect = database.connectDb();

        try {
            Alert alert;
            if (totalP == 0 || change == 0 || payment_amount.getText().isEmpty()){
                alert =  new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something Wrong : 3");
                alert.showAndWait();



            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confimation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {


                    prepared = connect.prepareStatement(sql);
                    prepared.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();

                    paymentShowData();
                    memberClear();
                }else {

                    alert =  new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void memberClear(){

        payment_customerId.getSelectionModel().clearSelection();
        payment_name.getSelectionModel().clearSelection();
        payment_total.setText("$0.0");
        payment_amount.setText("");
        payment_change.setText("$0.0");

        amount = 0;
        change = 0;
        totalP = 0;

    }



    public ObservableList<memberData> paymentDataList(){


        ObservableList<memberData>  listData = FXCollections.observableArrayList();

        String sql = "select * from member";

        connect = database.connectDb();

        try {
            memberData md;
            prepared = connect.prepareStatement(sql);
            result = prepared.executeQuery();

            while (result.next()){
                md = new memberData(result.getInt("id")
                        , result.getString("memberId")
                        , result.getString("name")
                        , result.getString("address")
                        , result.getInt("phoneNumber")
                        , result.getString("gender")
                        , result.getString("schedule")
                        , result.getDate("startDate")
                        , result.getDate("endDate")
                        , result.getDouble("price")
                        , result.getString("status"));

                listData.add(md);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<memberData> paymentDataList;

    public void paymentShowData(){

        paymentDataList = paymentDataList();

        payment_col_customerId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        payment_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        payment_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        payment_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        payment_tableView.setItems(paymentDataList);
    }


    public void swithchForm(ActionEvent event){

        if(event.getSource() == dashboard_btn){

            dadashboad_form.setVisible(true);
            coaches_form.setVisible(false);
            member_form.setVisible(false);
            payment_Form.setVisible(false);

            dashboardTC();
            dashboardTI();
            dashboardNM();
            dashboardChart();

        }else if (event.getSource() == coaches_btn){

            dadashboad_form.setVisible(false);
            coaches_form.setVisible(true);
            member_form.setVisible(false);
            payment_Form.setVisible(false);

            coachGenderList();
            coachStatusList();
            setCochesShowtData();

        }else if (event.getSource() == member_btn){

            dadashboad_form.setVisible(false);
            coaches_form.setVisible(false);
            member_form.setVisible(true);
            payment_Form.setVisible(false);

            memberShowData();
            memberGender();
            memberSchedule();
            memberStatus();

        }else if (event.getSource() == payment_btn){

            dadashboad_form.setVisible(false);
            coaches_form.setVisible(false);
            member_form.setVisible(false);
            payment_Form.setVisible(true);

            paymentShowData();
            paymentMemberID();
            paymentName();

        }

    }

    private double x = 0;
    private double y = 0;


    public void logout(){

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setContentText(null);
            alert.setHeaderText("Are you sure want to logout ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){

                Parent parent = FXMLLoader.load(this.getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(parent);

                Stage stage = (Stage)main_form.getScene().getWindow();

                parent.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                parent.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(0.8);
                });

                parent.setOnMouseReleased((MouseEvent evebt) ->{
                   stage.setOpacity(1);
                });

                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Login Page");
                stage.show();

            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void minimize(){

        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close(){

        javafx.application.Platform.exit();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dashboardTC();
        dashboardTI();
        dashboardNM();
        dashboardChart();

        coachGenderList();
        coachStatusList();
        setCochesShowtData();

        memberShowData();
        memberGender();
        memberSchedule();
        memberStatus();

        paymentShowData();
        paymentMemberID();
        paymentName();

    }
}
