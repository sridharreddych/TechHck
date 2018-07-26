package com.hcl.loaneligibility.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.loaneligibility.exception.MonthlySavingsException;
import com.hcl.loaneligibility.model.Offer;

@Service
public interface OfferService {

	List<Offer> getLoanOfferByMonthlySaving(Double montlymonthlysaving) throws MonthlySavingsException;
}
