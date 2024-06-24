package com.intland.xunit;

public class Main {

	public static void main(String[] args) {
		GatherComputerInfo computerInfo = new GatherComputerInfo();
		System.out.println("os: " + computerInfo.getOsName());
		System.out.println("max memory: " + computerInfo.getMaxMemoryInMb());
		System.out.println("free memory: " + computerInfo.getFreeMemoryInMb());
		System.out.println("total memory: " + computerInfo.getTotalMemoryInMb());
		System.out.println("used memory: " + computerInfo.getUsedMem());
		System.out.println("avail cpu: " + computerInfo.getProcessorCount());
		System.out.println("usable space: " + computerInfo.getUsableSpace());
		System.out.println("total space: " + computerInfo.getTotalSpace());
		System.out.println("system recommended: " + computerInfo.isSystemRecommended());
	}
}
