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
	    String FILEPATH = "/home/pedro/Documents/Shared/GitHub/muJava-AUM/examples" + session;
		String CLASSNAME = "Example";
		try {
			Debug.setDebugLevel(Debug.DETAILED_LEVEL);
			ExpressionAnalyzer.DbgLevel = ExpressionAnalyzer.DebugLevel.NONE;
			MutationSystem.setJMutationStructureFromFilePath(FILEPATH);
			MutationSystem.recordInheritanceRelation();
			MutationSystem.ORIGINAL_PATH = FILEPATH + "/result/" + CLASSNAME + "/original";
			MutationSystem.CLASS_NAME = CLASSNAME;
			MutationSystem.TRADITIONAL_MUTANT_PATH =  FILEPATH + "/result/"+ CLASSNAME + "/traditional_mutants";
			MutationSystem.CLASS_MUTANT_PATH = FILEPATH + "/result/"+ CLASSNAME +"/class_mutants";

			File original = new File(FILEPATH + "/src/"+ CLASSNAME + ".java");
			ArrayList<String> selected = new ArrayList<>();
			selected.add("ROR");
//			TraditionalMutantsGenerator tmg = new TraditionalMutantsGenerator(original, new String[]{"ROR"}, selected);
//			tmg.makeMutants();
            AllMutantsGenerator amg = new AllMutantsGenerator(original, new String[0], new String[]{"AORB"});
            amg.makeMutants();
		}
		catch (FileNotFoundException e) {
			System.out.println("Error! " + FILEPATH  + "\nFile not found!");
			e.printStackTrace();

		}
		catch (URISyntaxException e) {
			System.out.println("Error! Wrong URI!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Error!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
