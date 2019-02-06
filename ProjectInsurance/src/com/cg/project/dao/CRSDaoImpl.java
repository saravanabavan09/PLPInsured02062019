package com.cg.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.cg.project.exception.CRSException;
import com.cg.project.jdbc.JDBCUtility;
import com.cg.project.presentation.ClaimCreationEntity;
import com.cg.project.presentation.ClaimDetailsEntity;
import com.cg.project.presentation.PolicyEntity;

public class CRSDaoImpl implements CRSDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	static Logger logger = Logger.getLogger(CRSDaoImpl.class);

	@Override
	public int addCustomerDetails(ClaimCreationEntity claimCreation) throws CRSException {
		logger.info("in add Customer method");
		connection = JDBCUtility.getConnection();
		logger.info("connection object created");
		int generatedId = 0;
		try {
			statement = connection.prepareStatement(QueryMapper.insertCustomerDetails);
			logger.debug("statement object created");
			statement.setString(1, claimCreation.getClaimReason());
			statement.setString(2, claimCreation.getAccidentLocationStreet());
			statement.setString(3, claimCreation.getAccidentCity());
			statement.setString(4, claimCreation.getAccidentState());
			statement.setLong(5, claimCreation.getAccidentZip());
			statement.setString(6, claimCreation.getClaimType());
			statement.setLong(7, claimCreation.getPolicyNumber());
			generatedId = statement.executeUpdate();
			logger.info("execute update called");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new CRSException("problem occured while creating the statement object");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new CRSException("Statament Not Closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new CRSException("Connection Not Closed");
			}
		}

		return generatedId;
	}

	@Override
	public List<ClaimDetailsEntity> getQuestions(long policyNumber) throws CRSException {
		// TODO Auto-generated method stub
		List<ClaimDetailsEntity> list = new ArrayList<>();
		connection = JDBCUtility.getConnection();

		try {
			statement = connection.prepareStatement(QueryMapper.getClaimQuestionId);
			statement.setLong(1, policyNumber);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String claimQuesId = resultSet.getString(1);
				int claimQuesSeq = resultSet.getInt(2);
				String busSeqId = resultSet.getString(3);
				String claimQuesDesc = resultSet.getString(4);
				String claimQuesAns1 = resultSet.getString(5);
				int claimQues1Weightage = resultSet.getInt(6);
				String claimQuesAns2 = resultSet.getString(7);
				int claimQues2Weightage = resultSet.getInt(8);
				String claimQuesAns3 = resultSet.getString(9);
				int claimQues3Weightage = resultSet.getInt(10);
				String claimQuesAns4 = resultSet.getString(11);
				int claimQues4Weightage = resultSet.getInt(12);
				ClaimDetailsEntity claimDetailsEntity = new ClaimDetailsEntity();
				claimDetailsEntity.setClaimQuesId(claimQuesId);
				claimDetailsEntity.setClaimQuesSeq(claimQuesSeq);
				claimDetailsEntity.setBusSeqId(busSeqId);
				claimDetailsEntity.setClaimQuesDesc(claimQuesDesc);
				claimDetailsEntity.setClaimQuesAns1(claimQuesAns1);
				claimDetailsEntity.setClaimQuesAns1Weightage(claimQues1Weightage);
				claimDetailsEntity.setClaimQuesAns2(claimQuesAns2);
				claimDetailsEntity.setClaimQuesAns2Weightage(claimQues2Weightage);
				claimDetailsEntity.setClaimQuesAns3(claimQuesAns3);
				claimDetailsEntity.setClaimQuesAns3Weightage(claimQues3Weightage);
				claimDetailsEntity.setClaimQuesAns4(claimQuesAns4);
				claimDetailsEntity.setClaimQuesAns4Weightage(claimQues4Weightage);
				list.add(claimDetailsEntity);
			}

		} catch (SQLException e) {
			throw new CRSException("problem occured while creating the statement object");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new CRSException("Result Set Not Closed");
			}
			try {
				statement.close();
			} catch (SQLException e) {
				throw new CRSException("Statament Not Closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new CRSException("Connection Not Closed");
			}
		}

		return list;
	}

	@Override
	public int insertQuestions(long policyNumber, String claimQuesId, String claimAns) throws CRSException {
		// TODO Auto-generated method stub
		connection = JDBCUtility.getConnection();
		int rows = 0;
		try {
			statement = connection.prepareStatement(QueryMapper.insertQuestion);
			statement.setLong(1, policyNumber);
			statement.setString(2, claimQuesId);
			statement.setString(3, claimAns);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new CRSException("problem occured while creating the statement object");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new CRSException("Statament Not Closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new CRSException("Connection Not Closed");
			}
		}

		return rows;
	}

	@Override
	public List<PolicyEntity> getPolicies() throws CRSException {
		// TODO Auto-generated method stub
		List<PolicyEntity> list = new ArrayList<>();
		connection = JDBCUtility.getConnection();
		try {
			statement = connection.prepareStatement(QueryMapper.getPolicies);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long policyNumber = resultSet.getLong(1);
				double policyPremium = resultSet.getDouble(2);
				long accountNumber = resultSet.getLong(3);
				String busSeqId = resultSet.getString(4);

				PolicyEntity policy = new PolicyEntity();
				policy.setPolicyNumber(policyNumber);
				policy.setPolicyPremium(policyPremium);
				policy.setAccountNumber(accountNumber);
				policy.setBusSeqId(busSeqId);
				list.add(policy);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CRSException("problem occured while creating the statement object");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new CRSException("Result Set Not Closed");
			}
			try {
				statement.close();
			} catch (SQLException e) {
				throw new CRSException("Statament Not Closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new CRSException("Connection Not Closed");
			}
		}
		return list;
	}

}
