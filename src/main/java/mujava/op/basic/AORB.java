/**
 * Copyright (C) 2015  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mujava.op.basic;

import openjava.mop.*;
import openjava.ptree.*;
import java.io.*;
import java.util.List;

import mujava.op.util.LogReduction;

/**
 * <p>
 * Generate AORB (Arithmetic Operator Replacement (Binary)) mutants -- replace
 * an arithmetic operator by each of the other operators (*, /, %, +, -)
 * </p>
 * 
 * @author Yu-Seung Ma
 * @version 1.0
 */

public class AORB extends Arithmetic_OP {

	private List<String> allOperatorsSelected;

	public AORB(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit) {
		super(file_env, comp_unit);
	}

	public AORB(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit,
			List<String> allOperators) {
		super(file_env, comp_unit);
		allOperatorsSelected = allOperators;
	}

	/**
	 * Mutate the arithmetic operator to TIMES, DIVIDE, MOD, PLUS, MINUS
	 * (excluding itself)
	 */
	public void visit(BinaryExpression p) throws ParseTreeException {
		Expression left = p.getLeft();
		left.accept(this);

		Expression right = p.getRight();
		right.accept(this);

		if (isArithmeticType(p)) {
			int op_type = p.getOperator();
			switch (op_type) {
			// for AOR mutation operator
			// 5 Arithmetic Operators : TIMES, DIVIDE, MOD, PLUS, MINUS
			case BinaryExpression.TIMES:
				aorMutantGen(p, BinaryExpression.TIMES);
				break;

			case BinaryExpression.DIVIDE:
				aorMutantGen(p, BinaryExpression.DIVIDE);
				break;

			case BinaryExpression.MOD:
				aorMutantGen(p, BinaryExpression.MOD);
				break;

			case BinaryExpression.PLUS:
				aorMutantGen(p, BinaryExpression.PLUS);
				break;

			case BinaryExpression.MINUS:
				aorMutantGen(p, BinaryExpression.MINUS);
				break;
			}
		}
	}

	private void aorMutantGen(BinaryExpression exp, int op) {
		BinaryExpression mutant;
		if (op != BinaryExpression.TIMES) {
			mutant = (BinaryExpression) exp.makeRecursiveCopy();
			mutant.setOperator(BinaryExpression.TIMES);
			if (!isDuplicated(exp, mutant))
				aor_outputToFile(exp, mutant);

		}
		if (op != BinaryExpression.DIVIDE) {
			mutant = (BinaryExpression) exp.makeRecursiveCopy();
			mutant.setOperator(BinaryExpression.DIVIDE);
			if (!isDuplicated(exp, mutant))
				aor_outputToFile(exp, mutant);
		}
		if (op != BinaryExpression.MOD) {
			mutant = (BinaryExpression) exp.makeRecursiveCopy();
			mutant.setOperator(BinaryExpression.MOD);
			if (!isDuplicated(exp, mutant))
				aor_outputToFile(exp, mutant);
		}
		if (op != BinaryExpression.PLUS) {
			mutant = (BinaryExpression) exp.makeRecursiveCopy();
			mutant.setOperator(BinaryExpression.PLUS);
			if (!isDuplicated(exp, mutant))
				aor_outputToFile(exp, mutant);
		}
		if (op != BinaryExpression.MINUS) {
			mutant = (BinaryExpression) exp.makeRecursiveCopy();
			mutant.setOperator(BinaryExpression.MINUS);
			if (!isDuplicated(exp, mutant))
				aor_outputToFile(exp, mutant);
		}
	}

	/**
	 * Output AORB mutants to file
	 * 
	 * @param original
	 * @param mutant
	 */
	public void aor_outputToFile(BinaryExpression original, BinaryExpression mutant) {
		if (comp_unit == null)
			return;

		String f_name;
		num++;
		f_name = getSourceName("AORB");
		String mutant_dir = getMuantID("AORB");

		try {
			PrintWriter out = getPrintWriter(f_name);
			AORB_Writer writer = new AORB_Writer(mutant_dir, out);
			writer.setMutant(original, mutant);
			writer.setMethodSignature(currentMethodSignature);
			comp_unit.accept(writer);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println("fails to create " + f_name);
		} catch (ParseTreeException e) {
			System.err.println("errors during printing " + f_name);
			e.printStackTrace();
		}
	}

	/**
	 * Avoid generate duplicated mutants
	 */
	private boolean isDuplicated(BinaryExpression original, BinaryExpression mutant) {
		// #Rule 1: AORB x ODL X CDL: If it is a binary operation with PLUS or
		// MINUS and the constant 1
		// AORB can yields duplicates with ODL and CDL.
		// Eg.: x = y + 1; -> x = y * 1;(AORB) -> x = y;(CDL)
		int op_type = original.getOperator();
		Expression left = original.getLeft();
		Expression right = original.getRight();
		if (op_type == BinaryExpression.PLUS || op_type == BinaryExpression.MINUS) {
			//Check the right side of the binary expression
			if (right instanceof Literal) {
				if (((Literal) right).equals(Literal.constantOne())) {
					if (mutant.getOperator() == BinaryExpression.TIMES) {
						if (allOperatorsSelected.contains("ODL") || allOperatorsSelected.contains("CDL")){
							String desc = original.toFlattenString() + " => " + mutant.toFlattenString();
							logReduction("AORB", "ODL", desc);
							return LogReduction.AVOID;
						}
						//#Rule 2: AORB x AORB (Mutants TIMES and DIVIDE will yields duplicates when changed)
					} else 	if(mutant.getOperator() == BinaryExpression.DIVIDE){
						String desc = original.toFlattenString() + " => " + mutant.toFlattenString();
						logReduction("AORB", "AORB", desc);
						return LogReduction.AVOID;
					}
				}
			}
			//Check the left side of the binary expression
			if (left instanceof Literal) {
				if (((Literal) left).equals(Literal.constantOne())) {
					if (mutant.getOperator() == BinaryExpression.TIMES) {
						if (allOperatorsSelected.contains("ODL") || allOperatorsSelected.contains("CDL")){
							String desc = original.toFlattenString() + " => " + mutant.toFlattenString();
							logReduction("AORB", "ODL", desc);
							return LogReduction.AVOID;
						}
					}
				}
			}
		}

		return false;
	}

}
