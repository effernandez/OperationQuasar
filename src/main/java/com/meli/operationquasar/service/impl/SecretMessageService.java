package com.meli.operationquasar.service.impl;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;


import com.meli.operationquasar.dto.SatelliteDto;
import com.meli.operationquasar.dto.SatellitesDto;




@Service
public class SecretMessageService {

	public String getMessage(SatellitesDto satellites) {
		
		String messageFinal = "";

		ArrayList<String> messageFull = new ArrayList<String>();

		for (SatelliteDto satellite : satellites.getSatellite()) {
			if (messageFull.size() == 0) {
				messageFull.addAll(satellite.getMessage());
			}
			for (int i = 0; i < satellite.getMessage().size(); i++) {

				if (!(messageFull.get(i) == (satellite.getMessage().get(i))) && (satellite.getMessage().get(i) != "")) {
					messageFull.set(i, satellite.getMessage().get(i));
				}
			}

		}
		
		messageFinal = data(messageFull.toString(), ", ", " ");
		messageFinal = data(messageFinal, "[", "");
		messageFinal = data(messageFinal, "]", "");
		
		return messageFinal;
	}

	public static String data(String string, String searchPattern, String replacementPattern) {

		String search = Pattern.quote(new StringBuffer(searchPattern).toString());
		String replace = Matcher.quoteReplacement(new StringBuffer(replacementPattern).toString());
		Pattern pat = Pattern.compile(search, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher mat = pat.matcher(string);
		string = mat.replaceAll(replace);

		return string;

	}

}
