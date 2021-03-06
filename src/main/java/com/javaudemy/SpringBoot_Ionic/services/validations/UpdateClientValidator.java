package com.javaudemy.SpringBoot_Ionic.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.javaudemy.SpringBoot_Ionic.domain.Client;
import com.javaudemy.SpringBoot_Ionic.domain.dto.ClientUpdateDTO;
import com.javaudemy.SpringBoot_Ionic.repositories.ClientRepository;
import com.javaudemy.SpringBoot_Ionic.resources.exceptions.FieldMessage;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientUpdateDTO> {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ClientRepository clientRepository;
	
	
	@Override
	public void initialize(UpdateClient ann) {
	}

	@Override
	public boolean isValid(ClientUpdateDTO objDTO, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Client aux = clientRepository.findByEmail(objDTO.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}