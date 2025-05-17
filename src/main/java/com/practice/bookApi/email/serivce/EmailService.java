package com.practice.bookApi.email.serivce;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public boolean sendVerificationCode(String toEmail, String code) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setTo(toEmail);
			helper.setSubject("책읽는 고양이: 책고 인증 번호입니다.");
			helper.setText("인증 코드는 다음과 같습니다: <strong>" + code + "</strong>", true);
			helper.setFrom("johyeonhwak@gmail.com");
			
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
