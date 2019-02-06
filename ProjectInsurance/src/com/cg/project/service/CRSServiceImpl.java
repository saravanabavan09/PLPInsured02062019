package com.cg.project.service;

import java.util.List;
import java.util.regex.Pattern;

import com.cg.project.dao.CRSDao;
import com.cg.project.dao.CRSDaoImpl;
import com.cg.project.exception.CRSException;
import com.cg.project.presentation.ClaimCreationEntity;
import com.cg.project.presentation.ClaimDetailsEntity;
import com.cg.project.presentation.PolicyEntity;

public class CRSServiceImpl implements CRSService {
	CRSDao dao = new CRSDaoImpl();

	@Override
	public boolean CheckClaimReason(String claimReason) {
		String claimreasonRegEx = "[A-Z]{1}[a-zA-Z]{2,30}$";
		return Pattern.matches(claimreasonRegEx, String.valueOf(claimReason));
	}

	@Override
	public boolean CheckAccidentLocationStreet(String accidentLocationStreet) {
		String accidentlocationstreetRegEx = "[A-Z]{1}[a-zA-Z0-9\\s]{2,30}$";
		return Pattern.matches(accidentlocationstreetRegEx, String.valueOf(accidentLocationStreet));
	}

	@Override
	public boolean CheckAccidentCity(String accidentCity) {
		String accidentcityRegEx = "[A-Z]{1}[a-zA-Z\\s]{2,15}$";
		return Pattern.matches(accidentcityRegEx, String.valueOf(accidentCity));
	}

	@Override
	public boolean CheckAccidentState(String accidentState) {
		String accidentstateRegEx = "[A-Z]{1}[a-zA-Z\\s]{2,15}$";
		return Pattern.matches(accidentstateRegEx, String.valueOf(accidentState));
	}

	@Override
	public boolean CheckAccidentZip(long accidentZip) {
		String accidentzipRegEx = "[0-9]{5}$";
		return Pattern.matches(accidentzipRegEx, String.valueOf(accidentZip));
	}

	@Override
	public boolean CheckPolicyNumber(long policyNumber) {
		String policynumberRegEx = "[0-9]{10}$";
		return Pattern.matches(policynumberRegEx, String.valueOf(policyNumber));

	}

	@Override
	public int addCustomerDetails(ClaimCreationEntity claimCreation) throws CRSException {
		// TODO Auto-generated method stub
		return dao.addCustomerDetails(claimCreation);
	}

	@Override
	public List<ClaimDetailsEntity> getQuestions(long policyNumber) throws CRSException {
		// TODO Auto-generated method stub
		return dao.getQuestions(policyNumber);
	}

	@Override
	public int insertQuestions(long policyNumber, String claimQuesId, String claimAns) throws CRSException {
		// TODO Auto-generated method stub
		return dao.insertQuestions(policyNumber, claimQuesId, claimAns);
	}

	@Override
	public List<PolicyEntity> getPolicies() throws CRSException {
		// TODO Auto-generated method stub
		return dao.getPolicies();
	}

}
