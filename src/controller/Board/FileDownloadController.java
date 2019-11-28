package controller.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serivce.face.FileService;
import serivce.impl.FileServiceImpl;

@WebServlet("/file/download")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FileService fileService = FileServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		fileService.fileDownload(req, resp);
	}
}
