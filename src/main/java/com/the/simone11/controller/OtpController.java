package com.the.simone11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.the.simone11.model.request.GeneraInviaRequest;
import com.the.simone11.model.response.GeneraInviaResponse;
import com.the.simone11.service.GeneraInviaService;

@RestController
@RequestMapping("nodb/otp")
public class OtpController {

	@Autowired
	private GeneraInviaService giService;
	
	
	
	@RequestMapping("send")
	public GeneraInviaResponse generaEinvia(@RequestBody GeneraInviaRequest request){
		return giService.generaeInvia(request);
	}
}
