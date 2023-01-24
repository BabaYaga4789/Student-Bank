package com.example.student.bank.controller;

import com.example.student.bank.model.authentication.IAuthentication;
import com.example.student.bank.model.authentication.IUser;
import com.example.student.bank.model.authentication.User;
import com.example.student.bank.model.UserFactory;
import com.example.student.bank.model.validator.IValidation;
import com.example.student.bank.model.transaction.ISend;
import com.example.student.bank.model.transaction.Send;
import com.example.student.bank.database.persistance.IUserPersistance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthenticationController {

    private IAuthentication authentication;
    private IUserPersistance userPersistance;
    private IValidation nameValidation;
    private IValidation passwordValidation;
    private IUser user;
    private ISend send;

    public AuthenticationController() {
        authentication = UserFactory.Instance().makeAuthentication();
        userPersistance = UserFactory.Instance().makeUserPersistance();
        nameValidation = UserFactory.Instance().makeNameValidation();
        passwordValidation = UserFactory.Instance().makePasswordValidation();
        user = UserFactory.Instance().makeUser();
        send = UserFactory.Instance().makePayment();
    }

    @GetMapping("/")
    public String firstPage(Model model) {
        model.getAttribute("Login");
        model.addAttribute("loginPage", new User());
        return "UserLoginPage";
    }

    @GetMapping("/register_form")
    public String register(Model model) {
        model.getAttribute("Register");
        model.addAttribute("UserRegistration", new User());
        return "UserRegistrationPage";
    }

    @PostMapping("/saveUser")
    public String registerUser(@ModelAttribute User user, Model model) {
        model.addAttribute("UserRegistration", user);
        if (!nameValidation.validate(user.getFirstName()) || !nameValidation.validate(user.getLastName())) {
            model.addAttribute("Name", "Please enter letters only in Name");
            return "UserRegistrationPage";
        } else if (passwordValidation.validate(user.getPassword()) == false) {
            model.addAttribute("password", "Enter password of length more than 8");
            return "UserRegistrationPage";
        } else if (authentication.register(userPersistance, user)) {
            return "redirect:/login_form";
        } else {
            model.addAttribute("registerUnsuccessful");
            return "UserRegistrationPage";
        }
    }

    @GetMapping("/login_form")
    public String login(Model model) {
        model.getAttribute("Login");
        model.addAttribute("loginPage", new User());
        return "UserLoginPage";
    }

    @PostMapping("/checkUser")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession httpSession) {
        model.addAttribute("loginPage", user);
        if (authentication.login(userPersistance, user, user.getUserEmail(), user.getPassword())) {
            if (user.getUserAccountDetails(userPersistance, user)) {
                model.addAttribute("userAccountNumber", user.getAccountNumber());
                model.addAttribute("userAccountType", user.getAccountType());
                model.addAttribute("userAccountBalance", user.getAccountBalance());
                httpSession.setAttribute("userMail", user.getUserEmail());
            }
            return "UserHomePage";
        } else {
            model.addAttribute("loginError", "Incorrect Username or Password");
            return "UserLoginPage";
        }
    }

    @GetMapping("/transaction_form")
    public String transaction(Model model, Send send, HttpSession httpSession) {
        model.getAttribute("Transaction");
        send.accountDetails(userPersistance, send, (String) httpSession.getAttribute("userMail"));
        model.addAttribute("userAccountNumber", send.getSenderAccountNumber());
        model.addAttribute("userAccountBalance", send.getSenderAccountBalance());
        model.addAttribute("Transaction", new Send());
        return "TransactionPage";
    }

    @GetMapping("/home_page")
    public String homePage(Model model, User user, HttpSession httpSession) {
        model.addAttribute("UserHomePage");
        user.setUserEmail((String) httpSession.getAttribute("userMail"));
        user.getUserAccountDetails(userPersistance, user);
        model.addAttribute("userAccountNumber", user.getAccountNumber());
        model.addAttribute("userAccountType", user.getAccountType());
        model.addAttribute("userAccountBalance", user.getAccountBalance());
        return "UserHomePage";
    }

    @PostMapping("/transactionPage")
    public String transactionPage(@ModelAttribute Send send, Model model, HttpSession httpSession) {
        model.addAttribute("Transaction", send);
        send.sendMoney(userPersistance, (String) httpSession.getAttribute("userMail"), send);
        return "redirect:/home_page";
    }

    @GetMapping("/transaction_record")
    public String transactionRecord(Model model, Send send, HttpSession httpSession) {
        List<Send> sendList = new ArrayList<>();
        model.getAttribute("transactionPage");
        sendList.addAll(send.transferDetails(userPersistance, send, (String) httpSession.getAttribute("userMail")));
        model.addAttribute("userList", sendList);
        return "TransactionRecord";
    }
}
