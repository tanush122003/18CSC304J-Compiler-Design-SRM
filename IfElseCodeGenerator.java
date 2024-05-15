// INTERMIDIATE CODE GENERATION IF/ELSE

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IfElseCodeGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the condition for the if statement:");
        String condition = scanner.nextLine();

        System.out.println("Enter the code to execute if the condition is true:");
        String trueCode = scanner.nextLine();

        System.out.println("Enter the code to execute if the condition is false:");
        String falseCode = scanner.nextLine();

        String generatedCode = generateIfElseCode(condition, trueCode, falseCode);
        List<String> tac = generateThreeAddressCode(condition, trueCode, falseCode);

        System.out.println("\nGenerated Java Code:");
        System.out.println(generatedCode);

        System.out.println("\nThree-Address Code:");
        for (String instruction : tac) {
            System.out.println(instruction);
        }
    }

    private static String generateIfElseCode(String condition, String trueCode, String falseCode) {
        StringBuilder codeBuilder = new StringBuilder();

        codeBuilder.append("if (").append(condition).append(") {\n");
        codeBuilder.append("    ").append(trueCode).append("\n");
        codeBuilder.append("} else {\n");
        codeBuilder.append("    ").append(falseCode).append("\n");
        codeBuilder.append("}");

        return codeBuilder.toString();
    }

    private static List<String> generateThreeAddressCode(String condition, String trueCode, String falseCode) {
        List<String> tac = new ArrayList<>();

        String tempCondition = "t1";
        String tempTrue = "t2";
        String tempFalse = "t3";
        String labelTrue = "L1";
        String labelFalse = "L2";
        String labelEnd = "L3";

        // Assuming simple conditions and statements for demonstration purposes
        tac.add(tempCondition + " = " + condition);
        tac.add("if " + tempCondition + " goto " + labelTrue);
        tac.add("goto " + labelFalse);
        tac.add(labelTrue + ": " + tempTrue + " = " + trueCode);
        tac.add("goto " + labelEnd);
        tac.add(labelFalse + ": " + tempFalse + " = " + falseCode);
        tac.add(labelEnd + ":");

        return tac;
    }
}
