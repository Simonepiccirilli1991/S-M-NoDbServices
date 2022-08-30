package com.the.simone11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.security.SecureRandom;

import com.the.simone11.model.request.GeneraInviaRequest;
import com.the.simone11.model.response.GeneraInviaResponse;

@Service
public class GeneraInviaService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public GeneraInviaResponse generaeInvia(GeneraInviaRequest request) {

		// creo codice otp
		String otp = randomString(7);
		GeneraInviaResponse response = new GeneraInviaResponse();

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(request.getEmail());

		msg.setSubject("Codice Sicurezza the-Simone");
		msg.setText("Hello "+request.getUsername()+" this is your OTP: "+otp);
		try {
			javaMailSender.send(msg);
		}
		catch (Exception e) {
			response.setEsito("01");
			response.setMsgResp("Errore durante invio ots via mail");
			return response;
		}
		response.setEsito("00");
		response.setMsgResp("OTS inviato con successo");
		response.setOoTp(otp);

		return response;
        
	}
	
	
	
	// usati per generare otp
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	private String randomString(int len){
		StringBuilder sb = new StringBuilder(len);
		for(int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
