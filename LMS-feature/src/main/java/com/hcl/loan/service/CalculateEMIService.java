package com.hcl.loan.service;

import org.springframework.stereotype.Service;

import com.hcl.loan.model.EMI;
/**
 * 
 * @author shashank.sr
 *
 */
@Service
public interface CalculateEMIService {
	/**
	 * 
	 * @param emi
	 * @return
	 */
public EMI calculateEMI(EMI emi);
}
