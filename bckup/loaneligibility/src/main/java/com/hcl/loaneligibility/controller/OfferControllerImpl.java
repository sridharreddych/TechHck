package com.hcl.loaneligibility.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.loaneligibility.model.Offer;
import com.hcl.loaneligibility.service.OfferService;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
public class OfferControllerImpl implements OfferController {

	/*** Property to hold auto-wired PromocampaignService instance. */
	@Autowired
	private OfferService offerService;

	/*** Property to hold Logger instance and write to UI Service logs. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OfferControllerImpl.class);

	@Override
	public ResponseEntity<List<Offer>> getLoanOfferByMonthlySaving(@NotNull Double monthlysaving) throws Exception {

		List<Offer> offerLst = new ArrayList<>();

		try {
			offerLst = offerService.getLoanOfferByMonthlySaving(monthlysaving);
		} catch (Exception e) {
			LOGGER.error("OfferControllerImpl.getLoanOfferByMonthlySaving(monthlysaving) = " + monthlysaving + ")");
			throw new Exception();
		}

		return new ResponseEntity<>(offerLst, HttpStatus.OK);
	}
}
