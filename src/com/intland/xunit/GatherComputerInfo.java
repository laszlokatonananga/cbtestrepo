package com.intland.xunit;

import java.io.File;
import java.util.Properties;

public class GatherComputerInfo {
	private String FILE_ROOT = "/";
	private long BYTE_TO_MB = 1024 * 1024;
	private long GB_4 = 4 * 1024;
	private long GB_10 = 10 * 1024;
	
	public long getMaxMemoryInMb() {
		return Runtime.getRuntime().maxMemory() / BYTE_TO_MB;
	}
	
	public long getTotalMemoryInMb() {
		return Runtime.getRuntime().totalMemory() / BYTE_TO_MB;
	}
	
	public long getFreeMemoryInMb() {
		return Runtime.getRuntime().freeMemory() / BYTE_TO_MB;
	}
	
	public long getUsedMem() {
		return getTotalMemoryInMb() - getFreeMemoryInMb();
	}
	
	public int getProcessorCount() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	public long getUsableSpace() {
		File root = new File(FILE_ROOT);
		return root.getUsableSpace() / BYTE_TO_MB;
	}
	
	public long getTotalSpace() {
		File root = new File(FILE_ROOT);
		return root.getTotalSpace() / BYTE_TO_MB;
	}
	
	public boolean isSystemRecommended() {
		boolean mem = getMaxMemoryInMb() > GB_4;
		boolean cpu = getProcessorCount() > 1;
		boolean disk = getUsableSpace() > GB_10;
		
		return mem & cpu & disk;
	}
	
	public String getOsName() {
		final Properties props = System.getProperties();
		String os_name = props.getProperty("os.name");
		String os_version = props.getProperty("os.version");
		if (os_version != null) {
			os_name += " " + os_version;
		} else {
			return null;
		}
		return os_name;
	}
}
