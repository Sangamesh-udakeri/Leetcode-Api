package com.project.generator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.project.model.Problem;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Optional;

public class CsvGenerator {

	public static void generateProblemsCsv(OutputStream outputStream, Optional<Problem> downloadProblemById)
			throws IOException {
		try (CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream), CSVFormat.DEFAULT)) {
			csvPrinter.printRecord("Problem Name", "Problem Type", "Problem Level", "Acceptance", "Problem Body");

			if (downloadProblemById.isPresent()) {
				Problem problem = downloadProblemById.get();
				csvPrinter.printRecord(problem.getProblemName(), problem.getProblemType(), problem.getProblemLevel(),
						problem.getAcceptance(), problem.getProblemBody());
			}

		}
	}
}
