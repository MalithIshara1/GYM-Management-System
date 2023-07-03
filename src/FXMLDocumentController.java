import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FXMLDocumentController {

    @FXML
    private Button close;

    @FXML
    private FontAwesomeIcon close_icon;

    @FXML
    private Label edit_lable;

    @FXML
    private AnchorPane login_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button si_loginBtn;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private AnchorPane singup_form;

    @FXML
    private TextField su_email;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_singupBtn;

    @FXML
    private TextField su_username;

    @FXML
    private AnchorPane sub_form;

    @FXML
    private Button sub_loginBtn;

    @FXML
    private Button sub_singupBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void login(){
        String sql = "select * from admin where username = ? and password = ?";

        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1,si_username.getText());
            prepare.setString(2,si_password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if(si_username.getText().isEmpty() || si_password.getText().isEmpty()){
                alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blanks fields");
                alert.showAndWait();

            }else
                if (result.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    Parent parent = FXMLLoader.load(this.getClass().getResource("dashboard.fxml"));
                    Scene scene = new Scene(parent);

                    Stage stage = (Stage)main_form.getScene().getWindow();

                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setTitle("Dash Board");
                    stage.show();


                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password!");
                    alert.showAndWait();
                }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void singUp(){

        String sql = "insert into admin (email,username,password) values (?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (su_email.getText().isEmpty() || su_username.getText().isEmpty() || su_password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blanks fields");
                alert.showAndWait();
            }else {
                if (su_password.getText().length() < 8){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid password 3");
                    alert.showAndWait();
                }else {
                    prepare = connect.prepareStatement(sql);

                    prepare.setString(1,su_email.getText());
                    prepare.setString(2,su_username.getText());
                    prepare.setString(3,su_password.getText());

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful create a new Account!");
                    alert.showAndWait();

                    prepare.executeUpdate();

                    su_email.setText("");
                    su_username.setText("");
                    su_password.setText("");

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void singupSlider(){

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(sub_form);
        translateTransition.setToX(300);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.play();

        translateTransition.setOnFinished((ActionEvent event) ->{

            edit_lable.setText("Login Account");

            sub_singupBtn.setVisible(false);
            sub_loginBtn.setVisible(true);

            close_icon.setFill(Color.valueOf("#fff"));
        });
    }

    public void loginSlider(){

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(sub_form);
        translateTransition.setToX(0);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.play();

        translateTransition.setOnFinished((ActionEvent event) ->{

            edit_lable.setText("Create Account");

            sub_singupBtn.setVisible(true);
            sub_loginBtn.setVisible(false);

            close_icon.setFill(Color.valueOf("#000"));
        });

    }


    public void close(){
        javafx.application.Platform.exit();
    }


    public void initialize(){

    }

}
