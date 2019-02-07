package com.cg.crs.dao;

import java.util.List;

import com.cg.crs.entity.ClaimCreationEntity;
import com.cg.crs.entity.ClaimDetailsEntity;
import com.cg.crs.entity.PolicyEntity;
import com.cg.crs.exception.CRSException;

public interface CRSDao {

	int insertClaimDetails(ClaimCreationEntity claimCreation) throws CRSException;

	List<ClaimDetailsEntity> getQuestions(long policyNumber) throws CRSException;

	int insertQuestions(long policyNumber, String claimQuesId, String claimAns)  throws CRSException;

	List<PolicyEntity> getPolicies() throws CRSException;

	List<ClaimCreationEntity> viewClaimStatus(long claimPolicyNo) throws CRSException;;
 
}
