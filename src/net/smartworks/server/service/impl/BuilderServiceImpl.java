package net.smartworks.server.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.smartworks.server.service.IBuilderService;
import org.springframework.stereotype.Service;

@Service
public class BuilderServiceImpl implements IBuilderService {

	@Override
	public void startWorkService(String workId) throws Exception {

		try{
		}catch (Exception e){
			// Exception Handling Required
			e.printStackTrace();
			// Exception Handling Required			
		}		
	}

	@Override
	public void stopWorkService(String workId) throws Exception {

		try{
		}catch (Exception e){
			// Exception Handling Required
			e.printStackTrace();
			// Exception Handling Required			
		}		
	}

	@Override
	public void startWorkEditing(String workId) throws Exception {

		try{
		}catch (Exception e){
			// Exception Handling Required
			e.printStackTrace();
			// Exception Handling Required			
		}		
	}

	@Override
	public void stopWorkEditing(String workId) throws Exception {

		try{
		}catch (Exception e){
			// Exception Handling Required
			e.printStackTrace();
			// Exception Handling Required			
		}		
	}

	@Override
	public void setWorkSettings(Map<String, Object> requestBody, HttpServletRequest request) throws Exception {

		try{
		}catch (Exception e){
			// Exception Handling Required
			e.printStackTrace();
			// Exception Handling Required			
		}
	}

	@Override
	public void publishWorkToStore(Map<String, Object> requestBody, HttpServletRequest request) throws Exception {

		try{
		}catch (Exception e){
			// Exception Handling Required
			e.printStackTrace();
			// Exception Handling Required			
		}
	}
}