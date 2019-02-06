package com.cg.project.service;

import java.util.List;

import com.cg.project.exception.CRSException;
import com.cg.project.presentation.ClaimCreationEntity;
import com.cg.project.presentation.ClaimDetailsEntity;
import com.cg.project.presentation.PolicyEntity;

public interface CRSService {

	int addCustomerDetails(ClaimCreationEntity claimCreation) throws CRSException;

	boolean CheckClaimReason(String claimReason);

	boolean CheckAccidentLocationStreet(String accidentLocationStreet);

	boolean CheckAccidentCity(String accidentCity);

	boolean CheckAccidentState(String accidentState);

	boolean CheckAccidentZip(long accidentZip);

	boolean CheckPolicyNumber(long policyNumber);

	List<ClaimDetailsEntity> getQuestions(long policyNumber) throws CRSException;

	int insertQuestions(long policyNumber, String claimQuesId, String claimAns) throws CRSException;

	List<PolicyEntity> getPolicies()throws CRSException;;

}
