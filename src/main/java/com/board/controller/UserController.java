package com.board.controller;

import com.board.dto.UserForm;
import com.board.entity.File;
import com.board.entity.User;
import com.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model) {
        log.info("회원가입 페이지");
        model.addAttribute("userForm", new UserForm());
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm,
                         BindingResult bindingResult, @RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error("회원가입 에러");
            return "signup_form";
        }

        if (!userForm.getPassword1().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userForm.getUsername(),
                    userForm.getEmail(), userForm.getPassword1(), file);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
        redirectAttributes.addFlashAttribute("successMessage", "회원가입 성공!");
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login() {
        log.info("로그인 페이지");
        return "login_form";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        log.info("마이 프로필 페이지");
        User user = userService.getUser(principal.getName());
        File file = userService.getUserImg(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userImg", file);
        return "user/profile";
    }

    @GetMapping("/profile/edit")
    public String profileEdit(Model model, Principal principal) {
        log.info("프로필 수정 페이지");
        User user = userService.getUser(principal.getName());
        File userImg = userService.getUserImg(user.getId());
        model.addAttribute("userForm", new UserForm(user));
        model.addAttribute("userImg", userImg);
        return "signup_form";
    }

    @PostMapping("/profile/edit")
    public String profileEdit(@Valid UserForm userForm, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Principal principal,
                              HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            log.error("회원수정 에러");
            User user = userService.getUser(principal.getName());
            File userImg = userService.getUserImg(user.getId());
            model.addAttribute("userImg", userImg);
            return "signup_form";
        }

        if (!userForm.getPassword1().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        this.userService.modify(userForm.getPassword1(), principal.getName(), file);

        // 로그아웃 처리
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, authentication);

        redirectAttributes.addFlashAttribute("successMessage", "회원 수정 성공!, 다시 로그인 하세요!");
        return "redirect:/question/list";
    }
}
