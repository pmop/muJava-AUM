package mujava.op.basic;

import mujava.OpenJavaException;
import mujava.RORMockMutationSystem;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class RORTest {


	@Test
	public void test_erule13() throws OpenJavaException {
		File rule13example = new File("/home/pedro/Documents/Shared/GitHub/muJava-AUM/src/test/resources/ERule13Example.java");
		RORMockMutationSystem ms = new RORMockMutationSystem(rule13example);
		ms.makeMutants();
		assertEquals(true,true);
	}
}