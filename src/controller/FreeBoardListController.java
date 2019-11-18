package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serivce.face.FreeBoardService;
import serivce.impl.FreeBoardServiceImpl;

@WebServlet
public class FreeBoardListController extends HttpServlet {
	private FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//헤헤
	}

}
