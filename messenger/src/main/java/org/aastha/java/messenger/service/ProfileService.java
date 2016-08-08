package org.aastha.java.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aastha.java.messenger.database.DatabaseClass;
import org.aastha.java.messenger.model.Message;
import org.aastha.java.messenger.model.Profile;

public class ProfileService {

	
		private Map<String,Profile> profiles=DatabaseClass.getProfiles();
		
		public ProfileService(){
		profiles.put("Aastha Jain", new Profile(1L,"zola","Aastha","Jain"));
		}
			
		public List<Profile> getAllProfiles() {
			return new ArrayList<Profile>(profiles.values());
		}
		
		public Profile getProfile(String profileName){
			return profiles.get(profileName);
		}
		
		public Profile addProfile(Profile profile){
			profile.setId(profiles.size() +1);
			profiles.put(profile.getProfileName(),profile);
			return profile;
			
		}
		public Profile updateProfile(Profile profile){
			if(profile.getFirstName().isEmpty())
				return null;
			profiles.put(profile.getProfileName(), profile);
			return profile ;
		}
		public Profile removeProfile(String profileName){
			return profiles.remove(profileName);
		}
		

		
		


}
