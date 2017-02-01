package com.jroche.property.ai;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan Roche
 */

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final PropertyRepository propertyRepository;

	private final List<String> array = new ArrayList<String>();

	private final Pattern pattern = Pattern.compile("\"([^\"]*)\"");

	@Autowired
	public DatabaseLoader(final PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		loadPropertyPriceRegisterCsv(20);
	}

	@SuppressWarnings("unused")
	private void loadPropertyPriceRegisterCsv() throws ParseException, FileNotFoundException, IOException {
		loadPropertyPriceRegisterCsv(Long.MAX_VALUE);
	}

	private void loadPropertyPriceRegisterCsv(final long MAX_LOAD)
			throws ParseException, FileNotFoundException, IOException {
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(
				getClass().getClassLoader().getResource("static/csv/ppr.csv").openStream(), "windows-1252"))) {
			String line = bufferReader.readLine();
			int i = 0;
			while ((line = bufferReader.readLine()) != null && i < MAX_LOAD) {
				addProperty(line);
				i++;
			}
		}
	}

	private void addProperty(final String line) throws ParseException {
		Matcher m = pattern.matcher(line);
		while (m.find()) {
			array.add(m.group(1));
		}
		System.out.println(line);
		this.propertyRepository.save(new Property(array.toArray(new String[0])));
		array.clear();
	}
}