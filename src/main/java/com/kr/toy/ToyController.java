package com.kr.toy;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ToyDAO;
import vo.ToyVO;

@Controller
public class ToyController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ServletContext application;
	
	public static final String PATH = "/WEB-INF/views/toy/";
	
	ToyDAO toy_dao;
	public void setToy_dao(ToyDAO toy_dao) {
		this.toy_dao = toy_dao;
}
	
	//장바구니 목록 보기
	@RequestMapping(value = { "/toy_list.do")
	public String cartList(Model model) {
		
		List<ToyVO> list = toy_dao.selectList();
		model.addAttribute("list", list);
		return PATH + "toy_list.jsp";
		
	}
	}
	
	//장바구니 상품 담기
	@RequestMapping("/toy_addproduct.do")
	public String addtocart(ToyVO vo) {
		System.out.println("vo:" + vo);
		
		toy_dao.addCart(vo);
		
		return "redirect:toy_list.do";
		
	}
	
	
	//장바구니 상품 삭제
	@RequestMapping(value = "/toy_delete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProduct(int id) {
		int res = toy_dao.deleteProduct(id);
		
		String result = "삭제 실패";
		if (res != 0) {
			result = "삭제 성공";
		}
		
		String str = String.format("[{'result':'%s'}]", result);
		return "redirect:toy_list.do";
	}
	//주문하기
	@RequestMapping("/toy_order.do")
	public String orderProduct(ToyVO vo) {
		System.out.println("vo:" + vo);
		toy_dao.orderProduct(vo);
		
		return "redirect:toy_list.do";
	
	}
	
	//주문 목록 보기
	@RequestMapping("/toy_orderlist.do")
	public String orderList(Model model) {
		List<ToyVO> orderlist = toy_dao.selectList();
		model.addAttribute("orderlist", orderlist);
		return PATH + "toy_orderlist.jsp";
		
	}
	
}