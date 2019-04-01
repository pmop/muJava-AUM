package mujava.gui;

import mujava.AllMutantsGenerator;
import mujava.MutationSystem;
import mujava.TraditionalMutantsGenerator;
import mujava.op.basic.ExpressionAnalyzer;
import mujava.util.Debug;
import openjava.ptree.Expression;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class DummyExecution {
	public static void main(String[] args) {
	    String session = "/session2";
	    String FILEPATH = "/home/pedro/Documents/Shared/GitHub/muJava-AUM/mujava/examples" + session;
		try {
			Debug.setDebugLevel(Debug.DETAILED_LEVEL);
			ExpressionAnalyzer.DbgLevel = ExpressionAnalyzer.DebugLevel.BASIC;
			MutationSystem.setJMutationStructureFromFilePath(FILEPATH);
			MutationSystem.recordInheritanceRelation();
			MutationSystem.ORIGINAL_PATH = "/home/pedro/Documents/Shared/GitHub/muJava-AUM/mujava/examples" + session +
					"/result/ERule13Example/original";
			MutationSystem.CLASS_NAME = "ERule13Example";
			MutationSystem.TRADITIONAL_MUTANT_PATH = "/home/pedro/Documents/Shared/GitHub/muJava-AUM/mujava/" +
					"examples" + session + "/result/ERule13Example/traditional_mutants";

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
