package com.intland.xunit;

import org.junit.Assert;
import org.junit.Test;

public class GatherComputerInfoTest {
	@Test
	public void testIsMaxMemGreaterThan100Mb() {
		GatherComputerInfo computerInfo = new GatherComputerInfo();
		Assert.assertTrue(computerInfo.getMaxMemoryInMb() > 100);
	}
	
	//@Test
	public void testIsCpuCountOver2() {
		GatherComputerInfo computerInfo = new GatherComputerInfo();
		Assert.assertTrue(computerInfo.getProcessorCount() > 2);
	}
	
	@Test
	public void testIsSystemRecommended() {
		GatherComputerInfo computerInfo = new GatherComputerInfo();
		Assert.assertTrue(computerInfo.isSystemRecommended());
	}
	
	@Test
	public void testThrowFalseError() throws Exception {
		throw new Exception("ThrowFalseError");
	}
}
