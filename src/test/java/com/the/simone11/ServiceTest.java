package com.the.simone11;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doNothing;


import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.the.simone11.model.request.GeneraInviaRequest;
import com.the.simone11.model.response.GeneraInviaResponse;
import com.the.simone11.service.GeneraInviaService;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTest {

	@Mock
	JavaMailSender javaMail;
	@InjectMocks
	GeneraInviaService gis;
	
	@Test
	public void invioOK() {
		GeneraInviaRequest request = new GeneraInviaRequest();
		request.setEmail("mail");
		request.setUsername("usrm");
//		doNothing(javaMail.send(Mockito.any(SimpleMailMessage.class)));
//		doNothing().doThrow(new RuntimeException()).when(javaMail).send(Mockito.any(SimpleMailMessage.class))));
		doNothing().doThrow(new RuntimeException()).when(javaMail).send( Mockito.any(SimpleMailMessage.class));
		
		GeneraInviaResponse resp = gis.generaeInvia(request);
		
		assertThat(resp.getMsgResp()).isSameAs("OTS inviato con successo");
		assertThat(resp.getEsito()).isSameAs("00");
	}
}
