import java.util.Scanner;

interface Program {
    int calculateScore(int theory, int lab);
    String getGrade(int score);
}

class BTech implements Program {

    public int calculateScore(int theory, int lab) {
        return (theory + lab) / 2;
    }

    public String getGrade(int score) {
        if (score >= 75) return "A";
        if (score >= 60) return "B";
        return "C";
    }
}

class MCA implements Program {

    public int calculateScore(int theory, int lab) {
        return (theory * 60 + lab * 40) / 100;
    }

    public String getGrade(int score) {
        if (score >= 70) return "Distinction";
        if (score >= 50) return "Pass";
        return "Fail";
    }
}

abstract class Calculate {

    Program program;
    int theory;
    int lab;
    int attendance;

    Calculate(Program program, int theory, int lab, int attendance) {
        this.program = program;
        this.theory = theory;
        this.lab = lab;
        this.attendance = attendance;
    }

    final void evaluate() {
        int score = program.calculateScore(theory, lab);

        if (attendance >= 85) {
            score += 5;
        }

        System.out.println("Final Score: " + score);
        System.out.println("Grade: " + program.getGrade(score));
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter branch (BTech / MCA): ");
        String branch = sc.next();

        System.out.print("Enter theory marks: ");
        int theory = sc.nextInt();

        System.out.print("Enter lab marks: ");
        int lab = sc.nextInt();

        System.out.print("Enter attendance percentage: ");
        int attendance = sc.nextInt();

        Program program;

        if (branch.equalsIgnoreCase("BTech")) {
            program = new BTech();
        } else if (branch.equalsIgnoreCase("MCA")) {
            program = new MCA();
        } else {
            System.out.println("Invalid branch");
            sc.close();
            return;
        }

        Calculate result =
                new Calculate(program, theory, lab, attendance) {};

        result.evaluate();
        sc.close();
    }
}
