package com.practice.bookApi.email.serivce;

import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public String createCode() {
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 6; i++) {
			code += random.nextInt(10);
		}
		return code;
	}
	
	public boolean isValidEmail(String email) {
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    return email != null && email.matches(emailRegex);
	}
	
	public EmailSendResult sendVerificationCode(String toEmail, String code) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setTo(toEmail);
			helper.setSubject("책읽는 고양이: 책고 인증 번호입니다.");
			helper.setText("인증 코드는 다음과 같습니다: <strong>" + code + "</strong>", true);
			helper.setFrom("johyeonhwak@gmail.com");
			
			mailSender.send(message);
			return new EmailSendResult(true, null);
		} catch (MailException e) {
			return new EmailSendResult(false, "이메일 전송중 서버 오류가 생겼습니다.");
		} catch (AddressException  e) {
			return new EmailSendResult(false, "잘못된 이메일 형식입니다.");
		} catch (Exception  e) {
			return new EmailSendResult(false, "이메일 전송 실패: " + e.getMessage());
		}
	}
	
	public class EmailSendResult {
		
		private boolean success;
		private String errorMessage;
		
		public EmailSendResult(boolean success, String errorMessage) {
			this.success = success;
			this.errorMessage = errorMessage;
		}
		
		public boolean isSuccess() {
			return success;
		}
		
		public String getErrorMessage() {
			return errorMessage;
		}
	}
	
}
