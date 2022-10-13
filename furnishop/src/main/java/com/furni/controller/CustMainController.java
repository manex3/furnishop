package com.furni.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furni.dto.CustDTO;
import com.furni.service.CustService;

@Controller
public class CustMainController {

	@Autowired
	CustService cust_service;

	@RequestMapping("/")
	public String main() {
		return "main";
	}

	// login
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("center", "login");
		return "main";
	}

	// register
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("center", "register");
		return "main";
	}

	// loginimpl
	@RequestMapping("/loginimpl")
	public String loginimpl(String custid, String custpwd, Model model, HttpSession session) {
		CustDTO cust = null;
		try {
			cust = cust_service.get(custid);
			if (cust == null) {
				model.addAttribute("center", "loginfail");
			} else {
				if (custpwd.equals(cust.getCustpwd())) {
					session.setAttribute("logincust", cust);
					model.addAttribute("center", "main");
					// id,pwd가 다 아닐 때
				} else {
					model.addAttribute("center", "loginfail");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}

	// registerimpl

	@RequestMapping("/registerimpl")
	public String registerimpl(Model model, CustDTO cust, HttpSession session) {
		// custservice를 이용해서 database로 보내주기 위한 부분!
		try {
			// 회원가입이 정상일 때!!
			cust_service.register(cust);
			// 회원가입을 했을 때 자동으로 로그인이 된 것!
			session.setAttribute("logincust", cust);
			// 정상일때는
			model.addAttribute("center", "registerok");
			// 객체를 넣을 수도 있음!
			model.addAttribute("rid", cust);
		} catch (Exception e) {
			// 중복된 id를 넣을 때는 !!registerfail로 가게 한다.
			model.addAttribute("center", "registerfail");
			// registerfail page에 특정 값을 내보낼 때 model사용함! 이것이 tymleaf가 하는 일!
			model.addAttribute("fid", cust.getCustid());
		}

		return "main";
	}

	//custdetail
	@RequestMapping("/custdetail")
	public String custdetail(Model model, String id) {
		CustDTO cust = null;
		try {
			cust=cust_service.get(id);
			model.addAttribute("custdetail", cust);
			//centerpage는 이친구임(cust)의 정보를 가지고 있는!!
			model.addAttribute("center", "custdetail");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main";
	}
	
	//custupdate
	@RequestMapping("/custupdate")
	//id를 입력으로 받아와랴 해당하는 회원의 detail정보를 불러올 수 있음
	public String custupdate(Model model, String id) {
		CustDTO cust = null;
		try {
			cust=cust_service.get(id);
			//select해온 값을 담아서
			model.addAttribute("custupdate", cust);
			//centerpage는 이친구임(cust)의 정보를 가지고 있는!!
			//수정하는 화면으로 이동한다.
			model.addAttribute("center", "custupdate");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main";
	}
	
	//custupdateimpl
	@RequestMapping("/custupdateimpl")
	//id를 입력으로 받아와랴 해당하는 회원의 detail정보를 불러올 수 있음
	public String custupdateimpl(Model model, CustDTO updatecust) {
		//수정 한 이우에 custdetail 페이지로 이동
	    try {
			cust_service.modify(updatecust);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//controller안에서 다른 controller호출할 때
		return "redirect:custdetail?id="+updatecust.getCustid();
	}

}