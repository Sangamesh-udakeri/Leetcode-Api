package com.project.generator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.ByteArrayResource;

public class ZipGenerator {

	 public static void generateZipArchive(OutputStream outputStream, ByteArrayResource... resources) throws IOException {
	        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
	            for (ByteArrayResource resource : resources) {
	                String entryName = "file_" + System.currentTimeMillis() + ".csv";
	                zipOutputStream.putNextEntry(new ZipEntry(entryName));

	                byte[] data = resource.getByteArray();
	                zipOutputStream.write(data, 0, data.length);

	                zipOutputStream.closeEntry();
	            }
	        }
	    }
}
