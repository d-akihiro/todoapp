package com.example.todoapp.interfaces.home.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller;

/**
 * ホーム画面コントローラ
 * Created by d_akihiro on 2017/02/25.
 */
@Controller
public class HomeController {
	/**
	 * ホーム画面
	 * @return
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	String index(){
		return "index";
	}
}
