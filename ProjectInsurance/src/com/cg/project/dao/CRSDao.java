package com.cg.project.dao;

import java.util.List;

import com.cg.project.exception.CRSException;
import com.cg.project.presentation.ClaimCreationEntity;
import com.cg.project.presentation.ClaimDetailsEntity;
import com.cg.project.presentation.PolicyEntity;

public interface CRSDao {

	int addCustomerDetails(ClaimCreationEntity claimCreation) throws CRSException;

	List<ClaimDetailsEntity> getQuestions(long policyNumber) throws CRSException;

	int insertQuestions(long policyNumber, String claimQuesId, String claimAns)  throws CRSException;

	List<PolicyEntity> getPolicies()  throws CRSException;

}
