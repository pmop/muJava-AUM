package mujava.gui;

import mujava.AllMutantsGenerator;
import mujava.MutationSystem;
import mujava.TraditionalMutantsGenerator;
import mujava.util.Debug;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class DummyExecution {
	public static void main(String[] args) {
	    String FILEPATH = "/home/pedro/Documents/Shared/GitHub/muJava-AUM/mujava/examples/session3";
		try {
			Debug.setDebugLevel(Debug.DETAILED_LEVEL);
			MutationSystem.setJMutationStructureFromFilePath(FILEPATH);
			MutationSystem.recordInheritanceRelation();
			MutationSystem.ORIGINAL_PATH = "/home/pedro/Documents/Shared/GitHub/muJava-AUM/mujava/examples" +
					"/session3/result/ERule13Example/original";
			MutationSystem.CLASS_NAME = "ERule13Example";
			MutationSystem.TRADITIONAL_MUTANT_PATH = "/home/pedro/Documents/Shared/GitHub/muJava-AUM/mujava/" +
					"examples/session3/result/ERule13Example/traditional_mutants";

			File original = new File(FILEPATH + "/src/ERule13Example.java");
			ArrayList<String> selected = new ArrayList<>();
			selected.add("ROR");
			TraditionalMutantsGenerator tmg = new TraditionalMutantsGenerator(original, new String[]{"ROR"}, selected);
			tmg.makeMutants();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println();

		}
		catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
