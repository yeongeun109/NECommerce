package com.ecommerce.blockchain.domain;

import com.ecommerce.blockchain.domain.exception.ApplicationException;
import com.ecommerce.blockchain.domain.exception.DomainException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class CommonUtil
{
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static String readString(String filePath) {
		try {
			File file = getFile(filePath);
			logger.info(file.getAbsolutePath());
			if(file.exists()) {
				byte[] encoded = new byte[0];
				try {
					encoded = FileCopyUtils.copyToByteArray(file);
				}
				catch (IOException e) {
					e.printStackTrace();
					throw new ApplicationException(filePath + " CANNOT READ STRING!");
				}
				return new String(encoded, StandardCharsets.UTF_8);
			}
			return null;
		}
		catch (IOException e) {
			throw new ApplicationException(e.getMessage());
		}

	}

	public static Credentials getCredential(final String filePath, final String password)
	{
		try {
			File file = getFile(filePath);
			if(file.exists()){
				return WalletUtils.loadCredentials(password, file);
			}
			return null;
		} catch (IOException e) {
			throw new DomainException(e.getMessage());
		}
		catch (CipherException e) {
			throw new DomainException(e.getMessage());
		}
	}

	private static File getFile(final String filePath)
			throws IOException
	{
		String[] tokens = filePath.split("\\.");

		ClassPathResource classPathResource = new ClassPathResource(filePath);
		String protocol = classPathResource.getURL().getProtocol();
		logger.info(protocol + "| url=" + classPathResource.getURL());

		if(protocol.equals("file"))
			return classPathResource.getFile();

		if(protocol.equals("jar")) {
			InputStream inputStream = new ClassPathResource(filePath).getInputStream();
			File tempFile = File.createTempFile(tokens[0] + "-temp", "." + tokens[1]);
			try {
				FileUtils.copyInputStreamToFile(inputStream, tempFile);
			}
			finally {
				IOUtils.closeQuietly(inputStream);
			}
			return tempFile;
		}

		return null;
	}

	public static LocalDateTime convertEthTimestamp(long value) {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(value),
				TimeZone.getDefault().toZoneId());
	}
}
