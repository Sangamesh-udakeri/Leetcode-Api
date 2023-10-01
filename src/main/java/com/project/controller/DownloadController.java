package com.project.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.project.generator.CsvGenerator;
import com.project.generator.ZipGenerator;
import com.project.model.Problem;
import com.project.model.Resource;
import com.project.service.ProblemDownloadServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class DownloadController {

	@Autowired
	ProblemDownloadServiceImpl downloadServiceImpl;

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadProblemById(@PathVariable Integer id, HttpServletResponse response)
			throws IOException {

		Optional<Problem> downloadProblemById = downloadServiceImpl.downloadProblemById(id);
		if (downloadProblemById == null) {

			return ResponseEntity.notFound().build();
		}

		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		CsvGenerator.generateProblemsCsv(arrayOutputStream, downloadProblemById);

		ByteArrayResource csvResource = new ByteArrayResource(arrayOutputStream.toByteArray());
		ByteArrayOutputStream zipOutputStream = new ByteArrayOutputStream();
		ZipGenerator.generateZipArchive(zipOutputStream, csvResource);
		ByteArrayResource zipResource = new ByteArrayResource(zipOutputStream.toByteArray());
		// Create a Resource object and populate it with relevant data
		Resource resource = new Resource();
		resource.setProblemName(downloadProblemById.get().getProblemName());
		resource.setProblemType(downloadProblemById.get().getProblemType());
		resource.setProblemLevel(downloadProblemById.get().getProblemLevel());
		resource.setProblemBody(downloadProblemById.get().getProblemBody());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=problem_" + id + ".zip").body(resource);

	}

	@GetMapping("/download/csv/{id}")
	public ResponseEntity<ByteArrayResource> downloadCsvById(@PathVariable Integer id, HttpServletResponse response)
			throws IOException {

		Optional<Problem> resourceOptional = downloadServiceImpl.downloadProblemById(id);
		if (!resourceOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Resource resource1 = new Resource();
		Problem resource = resourceOptional.get();
		String csvContent = generateCsvContent(resource);
		byte[] csvBytes = csvContent.getBytes();
		ByteArrayResource csvResource = new ByteArrayResource(csvBytes);
		String filename = resource.getProblemName() + id + ".csv";
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
		headers.setContentType(MediaType.parseMediaType("text/csv"));
		return ResponseEntity.ok().headers(headers).body(csvResource);
	}

	private String generateCsvContent(Problem resource) {
		return resource.getProblemName() + "," + resource.getProblemType() + "," + resource.getProblemLevel()
				+ resource.getProblemBody() + "," + resource.getAcceptance() + "\n";
	}

	@GetMapping("/download/txt/{id}")
	public ResponseEntity<ByteArrayResource> downloadTxtById(@PathVariable Integer id, HttpServletResponse response)
			throws IOException {

		Optional<Problem> resourceOptional = downloadServiceImpl.downloadProblemById(id);
		if (!resourceOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Problem resource = resourceOptional.get();
		String txtContent = generateTxtContent(resource);
		byte[] txtBytes = txtContent.getBytes();
		ByteArrayResource txtResource = new ByteArrayResource(txtBytes);
		String filename = resource.getProblemName() + id + ".txt";
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
		headers.setContentType(MediaType.parseMediaType("text/plain")); // Set content type to plain text
		return ResponseEntity.ok().headers(headers).body(txtResource);
	}

	private String generateTxtContent(Problem resource) {
		return "Problem Name: " + resource.getProblemName() + "\n" + "Problem Type: " + resource.getProblemType() + "\n"
				+ "Problem Level: " + resource.getProblemLevel() + "\n" + "Acceptance: " + resource.getAcceptance()
				+ "\n" + "Problem Body: " + resource.getProblemBody() + "\n";
	}
}
