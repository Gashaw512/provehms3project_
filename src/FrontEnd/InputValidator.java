package FrontEnd;

import Controller.Login_controller;
import javafx.geometry.Point2D;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.Objects;

public class InputValidator {
    static Menu_scene menu_scene = new Menu_scene();
    private static String action_type1 = "process";
    private static String action_type2 = "end";
    private static Tooltip empty1 = Dr_form_pane.tooltip("This is a required field ");
    private static Tooltip notMach1 = Dr_form_pane.tooltip("The password you entered don't mach.Try again");
    private static Tooltip empty2 = Dr_form_pane.tooltip("This is a required field ");
    private static Tooltip notMach2 = Dr_form_pane.tooltip("The password you entered don't mach.Try again");
    private static Tooltip tooShort = Dr_form_pane.tooltip("Your password must contain at least 6 character.Please " +
            "try again");
    private static Tooltip reEmpty = Dr_form_pane.tooltip("You have to confirm your new password. Please rewrite again.");

    private static Tooltip tooShort1 = Dr_form_pane.tooltip("Your user name must contain at least 3 character. Please try again!");
    private static Tooltip toChoose = Dr_form_pane.tooltip("You have to choose the account type.Please select the one.");
    private static Tooltip reEmpty1 = Dr_form_pane.tooltip("You have to rewrite your password here. Please confirm it" +
            ".");
    private static Tooltip dbNotification = Dr_form_pane.tooltip("This User Name already exist. It should be unique. " +
            "Please try again!");

    private static Tooltip[] labels = {empty1, notMach1, empty2, tooShort, notMach2, reEmpty};
    private static Tooltip[] tooltips1 = {empty1, tooShort1, empty2, tooShort, notMach2, reEmpty1, toChoose, dbNotification};
    private static DrScene drScene = new DrScene();

    public static boolean validateLetterType(String name, String type) {
        if (type.equals(action_type1)) {
            return name.matches("[A-Za-z][a-zA-z]*") || name.isEmpty();
        }
        return name.matches("[A-Za-z][a-zA-z]*");
    }

    public static boolean validatePhone(String phone, String type) {
        //////////phone number can contain space(\t)
        StringBuilder h = new StringBuilder(phone);
        if (phone.startsWith("0")) {
            if (phone.length() < 10) {
                return phone.matches("[0-9]*");

            } else if (phone.length() == 10) {
                return phone.matches("[0-9]*");
                //011 234 5678 or 094 162 3091
            } else return false;

        } else if (phone.startsWith("+")) {
            if (phone.length() < 13) {
                return phone.matches("");
            } else if (phone.length() == 13) {
                return phone.matches("");
                //+251 11 234 5678 or +251 94 162 3091////is the form for pattern
            } else
                return false;

        } else return phone.isEmpty();
    }

    public static boolean validateNumberType(String room, String type) {
        return room.matches("[0-9]*") || room.isEmpty();
    }

    public static boolean validateMixedType(String mixed, String type) {
        if (type.equals("end")) {

        }
        return true;
    }

    ////////////////////////////////////////
    public static void keyTypedLetterHandler(Tooltip lbl, TextField letter, String type) {
        // Dr_form_pane dr=new Dr_form_pane();
        if (!validateLetterType(letter.getText().trim(), type)) {
            if (!lbl.isShowing()) {
                Dr_form_pane.showTooltip(letter, lbl);
            }

        } else
            Dr_form_pane.hideTooltip(lbl);
    }

    public static void keyTypedPhoneHandler(Tooltip lbl, TextField letter, String type) {
        if (!validatePhone(letter.getText().trim(), type)) {
            if (!lbl.isShowing()) {
                Dr_form_pane.showTooltip(letter, lbl);
            }

        } else Dr_form_pane.hideTooltip(lbl);
    }

    public static void keyTypedNumberHandler(Tooltip lbl, TextField letter, String type) {
        if (!validateNumberType(letter.getText().trim(), type)) {
            if (!lbl.isShowing()) {
                Dr_form_pane.showTooltip(letter, lbl);
            }
        } else {
            Dr_form_pane.hideTooltip(lbl);
        }
    }

    public static void keyTypedNumberHandlerM(Tooltip lbl, TextField letter, String type) {
        if (!validateNumberType(letter.getText().trim(), type)) {
            if (!lbl.isShowing()) {
                Bill_view.showTooltip(letter, lbl);
            }
        } else {
            Dr_form_pane.hideTooltip(lbl);
        }
    }

    public static void keyTypedMixedTypeHandler(Tooltip lbl, TextField letter, String type) {
        if (!validateMixedType(letter.getText().trim(), type)) {
            if (!lbl.isShowing()) {
                Dr_form_pane.showTooltip(letter, lbl);
            }
        } else
            Dr_form_pane.hideTooltip(lbl);
    }

    public static boolean validateEmail(String email) {
        return email.matches("[A-Za-z0-9.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{3,4}");
    }

    public static boolean isInvalid(Tooltip[] lbl) {
        for (int i = 0; i < lbl.length; i++)
            if (lbl[i].isShowing())
                return false;
        return true;
    }

    public static boolean isEmpty(TextField[] txt, Pane pane) {
        for (int i = 0; i < txt.length; i++)
            if (txt[i].getText().isEmpty())
                return false;

        return true;
    }

    ///////////for Password///////////////
    public static void validateAddUser_field(TextField[] textFields, RadioButton[] rButton) throws SQLException, ClassNotFoundException {
        if (textFields[0].getText().trim().isEmpty()) {
            if (tooltips1[1].isShowing())
                hideTooltip(tooltips1[1]);
            if (!tooltips1[0].isShowing())
                showTooltip(textFields[0], tooltips1[0]);

            validateNextFieldAddUserForms(textFields);
            rButtonSelectionChecker(rButton);
        } else if (!textFields[0].getText().trim().isEmpty()) {
            if (textFields[0].getText().trim().length() < 3) {
                if (tooltips1[0].isShowing())
                    hideTooltip(tooltips1[0]);
                if (!tooltips1[1].isShowing())
                    showTooltip(textFields[0], tooltips1[1]);
            }
            validateNextFieldAddUserForms(textFields);
            rButtonSelectionChecker(rButton);
        }

        for (int i = 0; i < tooltips1.length; i++)
            if (tooltips1[i].isShowing())
                return;
        if (!Login_controller.UserNameExistanceCheker(textFields[0].getText().trim())) {
            showTooltip(textFields[0], tooltips1[7]);//{empty1, tooShort1, empty2, tooShort, notMach2, reEmpty1, toChoose};
            return;
        }
        System.out.println("You have successfully add a new user. ");
        Menu_scene.addNewUserToSystem(textFields, rButton);
    }

    public static void validateNextFieldAddUserForms(TextField[] textFields) {
        if (!Objects.equals(textFields[1].getText().trim(), textFields[2].getText().trim())) {
            if (!textFields[1].getText().trim().isEmpty()) {
                if (textFields[2].getText().trim().isEmpty()) {
                    if (tooltips1[3].isShowing())
                        hideTooltip(tooltips1[3]);
                    if (!tooltips1[5].isShowing())///// {empty1, tooShort1, empty2, tooShort, notMach2, reEmpty1, toChoose};
                        showTooltip(textFields[2], tooltips1[5]);
                } else {
                    if (tooltips1[5].isShowing())
                        hideTooltip(tooltips1[5]);
                    if (!(tooltips1[4].isShowing()))
                        showTooltip(textFields[2], tooltips1[4]);
                }
            } else if (textFields[1].getText().trim().isEmpty()) {

                if ((tooltips1[3].isShowing()))
                    hideTooltip(tooltips1[3]);
                if (!(tooltips1[2].isShowing()))
                    showTooltip(textFields[1], tooltips1[2]);
            }
        } else if (textFields[1].getText().trim().isEmpty()) {
            if ((tooltips1[3].isShowing()))
                hideTooltip(tooltips1[3]);
            if (!(tooltips1[2].isShowing()))
                showTooltip(textFields[1], tooltips1[2]);
        } else if (Objects.equals(textFields[1].getText().trim(), textFields[2].getText().trim())) {
            if (textFields[1].getText().trim().length() < 6) {
                if ((tooltips1[2].isShowing()))
                    hideTooltip(tooltips1[2]);
                if (!(tooltips1[3].isShowing()))
                    showTooltip(textFields[1], tooltips1[3]);
            }
        }
    }

    public static void rButtonSelectionChecker(RadioButton[] radioButtons) {
        if (!radioButtons[0].isSelected())/////yields true when different NOR
        {
            if (!radioButtons[1].isSelected()) {
                if (!tooltips1[6].isShowing()) {
                    showTooltip1(radioButtons[0], tooltips1[6]);
                }
            } else hideTooltip(tooltips1[6]);
        } else hideTooltip(tooltips1[6]);
    }

    public static void validateModifyPassword(TextField[] textFields) throws SQLException, ClassNotFoundException {
        if (!textFields[0].getText().trim().equals(menu_scene.getPassword())) {
            if (textFields[0].getText().trim().isEmpty()) {
                if (labels[1].isShowing())
                    hideTooltip(labels[1]);
                if (!labels[0].isShowing())
                    showTooltip(textFields[0], labels[0]);
                 textFields[0].requestFocus();
                validateNextFieldModifyPassword(textFields);
            } else if (!textFields[0].getText().trim().isEmpty()) {
                if (labels[0].isShowing())
                    hideTooltip(labels[0]);
                if (!labels[1].isShowing())
                    showTooltip(textFields[0], labels[1]);
                validateNextFieldModifyPassword(textFields);
            }

        } else
            validateNextFieldModifyPassword(textFields);
        for (int i=0;i<6;i++)
            if (labels[i].isShowing())
                return;
                System.out.println("Change successfully.");
                Menu_scene.modifyPassword(textFields);

    }

    public static void validateNextFieldModifyPassword(TextField[] textFields) {
        if (!Objects.equals(textFields[1].getText().trim(), textFields[2].getText().trim())) {
            if (!textFields[1].getText().trim().isEmpty()) {
                if (textFields[2].getText().trim().isEmpty()) {
                    if (labels[4].isShowing())
                        hideTooltip(labels[4]);////{empty1, notMach1, empty2, tooShort, notMach2, reEmpty};
                    if (!labels[5].isShowing())
                        showTooltip(textFields[2], labels[5]);
                    textFields[0].requestFocus();
                } else {
                    if (labels[5].isShowing())
                        hideTooltip(labels[5]);
                    if (!(labels[4].isShowing()))
                        showTooltip(textFields[2], labels[4]);
                }

            } else if (textFields[1].getText().trim().isEmpty()) {
                if (labels[3].isShowing())//{empty1, notMach1, empty2, tooShort, notMach2, reEmpty};
                    hideTooltip(tooltips1[3]);
                if (!labels[2].isShowing())
                    showTooltip(textFields[1], tooltips1[2]);
            }

        } else if (textFields[1].getText().trim().isEmpty()) {
            if ((labels[3].isShowing()))
                hideTooltip(labels[3]);
            if (!(labels[2].isShowing()))
                showTooltip(textFields[1], labels[2]);
        } else if (Objects.equals(textFields[1].getText().trim(), textFields[2].getText().trim())) {
            if (textFields[1].getText().trim().length() < 6) {
                if ((labels[2].isShowing()))////
                    hideTooltip(labels[2]);
                if (!(labels[3].isShowing()))
                    showTooltip(textFields[1], labels[3]);
            }

        }
    }

    public static void validateChangeUserName(TextField txt) throws SQLException, ClassNotFoundException {
        if (txt.getText().trim().isEmpty()) {
            if (!tooltips1[0].isShowing())
                showTooltip(txt, tooltips1[0]);
        } else if (txt.getText().trim().length() < 3) {
            if (tooltips1[0].isShowing())
                hideTooltip(tooltips1[0]);
            if (!tooltips1[1].isShowing())
                showTooltip(txt, tooltips1[1]);
        } else if (!Login_controller.UserNameExistanceCheker(txt.getText().trim())) {
            showTooltip(txt, tooltips1[7]);
        } else {
            Menu_scene.modifyUserName(txt);
        }
    }

    public static void keyHandler(int mode) {
        for (int i = mode; i < mode + 2; i++) {
            if (labels[i].isShowing())
                hideTooltip(labels[i]);
        }
    }

    public static void keyHandler1(int mode) {
        for (int i = mode; i < mode + 2; i++) {
            if (tooltips1[i].isShowing())
                hideTooltip(tooltips1[i]);
        }
        if (mode == 0 && dbNotification.isShowing())
            hideTooltip(dbNotification);
    }

    public static void hideTooltip(Tooltip tooltip) {
        tooltip.hide();
    }

    public static void showTooltip(TextField pF, Tooltip tooltip) {
        Point2D p = pF.localToScene(pF.getBoundsInLocal().getMaxX(), pF.getBoundsInLocal().getMaxY());
        tooltip.show(pF, p.getX() + menu_scene.account_stageProvider().getScene().getX() + menu_scene.account_stageProvider().getX() + 5, p.getY() + menu_scene.account_stageProvider().getScene().getY() + menu_scene.account_stageProvider().getY() - 15);
    }

    public static void showTooltip1(RadioButton radioButton, Tooltip tooltip) {
        Point2D p = radioButton.localToScene(radioButton.getBoundsInLocal().getMaxX(), radioButton.getBoundsInLocal().getMaxY());
        tooltip.show(radioButton,
                p.getX() + menu_scene.account_stageProvider().getScene().getX() + menu_scene.account_stageProvider().getX() + 5, p.getY() + menu_scene.account_stageProvider().getScene().getY() + menu_scene.account_stageProvider().getY() - 10);
    }
}