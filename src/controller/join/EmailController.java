package controller.join;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import serivce.face.UserService;
import serivce.impl.UserServiceImpl;
import util.MailAuth;

@WebServlet("/email")
public class EmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/user/mail.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인증 번호 생성기
        StringBuffer temp =new StringBuffer();
        Random rnd = new Random();
        for(int i=0;i<10;i++)
        {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
            case 0:
                // a-z
                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                break;
            case 1:
                // A-Z
                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 2:
                // 0-9
                temp.append((rnd.nextInt(10)));
                break;
            }
        }
        String AuthenticationKey = temp.toString();
        System.out.println("인증번호 : " + AuthenticationKey);
        req.setAttribute("key", AuthenticationKey);

		// FROM
		final String FROM = "mindo4393@gmail.com"; // 보내는 사람 이메일
		final String FROMNAME = "PickMI"; // 보내는 사람 이름

		// TO
		final String TO = req.getParameter("email"); // 받는사람 이메일

		// 메일 제목
		final String SUBJECT = "이메일 인증번호 입니다."; // <<------------------------------수정하세요

		// 메일 본문
		final String BODY = String.join("<h1>PickMI 이메일 인증</h1>", "인증번호 : "+temp+"<br>"+"<p>인증번호를 사이트에 입력해 주세요</p>"); // <<------------------------------수정하세요


		// 인증 객체
		Authenticator auth = new MailAuth("mindo4393@gmail.com", "ehehgns4393"); // <<------------------------------수정하세요

		// 연결 설정
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		// 메일 세션 객체 생성
		Session session = Session.getDefaultInstance(props, auth);

		// 메시지 생성
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(FROM, FROMNAME));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			msg.setSubject(SUBJECT);
			msg.setContent(BODY, "text/html;charset=utf-8");

			System.out.println("Sending...");

			// 메시지 보내기
			Transport.send(msg);

			System.out.println("Email sent!");

		} catch (NoSuchProviderException e) {
			e.printStackTrace();

		} catch (MessagingException e) {
			e.printStackTrace();

			System.out.println("The email was not sent.");
			System.out.println("Error message: " + e.getMessage());
			
		}
		
//		resp.sendRedirect("/email");
		
		req.getRequestDispatcher("/authentic").forward(req, resp);

	}
}
