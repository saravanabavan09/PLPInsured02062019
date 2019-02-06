package com.cg.project.dao;

public interface QueryMapper {

	public static final String insertCustomerDetails = "insert into claim values(claim_number_seq.nextval,?,?,?,?,?,?,?)";
	public static final String getClaimQuestionId = "select * from claim_questions where bus_seg_id=(select bus_seg_id from policy where policy_number=?)";
	public static final String insertQuestion = "insert into policy_details values(?,?,?)";
	public static final String getPolicies = "select * from policy";

}
