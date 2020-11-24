package FinalProject;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;

//public class Repository2 {
//	BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
//	while ((row = csvReader.readLine()) != null) {
//	    String row;
//		String[] data = row.split(",");
//	    // do something with the data
//	}
//	csvReader.close();
//	File csvFile = new File(pathToCsv);
//	if (csvFile.isFile()) {
//	    // create BufferedReader and read data from csv
//		
//	}
//	List<List<String>> rows = Arrays.asList(
//		    Arrays.asList("Jean", "author", "Java"),
//		    Arrays.asList("David", "editor", "Python"),
//		    Arrays.asList("Scott", "editor", "Node.js")
//		);
//
//		FileWriter csvWriter = new FileWriter("new.csv");
//		csvWriter.append("Name");
//		csvWriter.append(",");
//		csvWriter.append("Role");
//		csvWriter.append(",");
//		csvWriter.append("Topic");
//		csvWriter.append("\n");
//
//		for (List<String> rowData : rows) {
//		    csvWriter.append(String.join(",", rowData));
//		    csvWriter.append("\n");
//		}
//
//		csvWriter.flush();
//		csvWriter.close();
//}
//}
