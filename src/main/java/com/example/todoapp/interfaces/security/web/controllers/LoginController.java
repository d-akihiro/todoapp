package com.example.todoapp.interfaces.security.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ログイン画面コントローラ
 * Created by d_akihiro on 2017/02/25.
 */
@Controller
public class LoginController {
	/**
	 * ログイン画面
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
}
