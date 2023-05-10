import java.time.LocalDate;
import java.util.*;
public class Recruitment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter date of birth in YYYY-MM-DD format: ");
        String dobStr = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dobStr);

        System.out.print("Enter nationality: ");
        String nationality = scanner.nextLine();

        System.out.println("Enter HSC marks (out of 100) for Physics, Chemistry, and Mathematics/Biology:");
        int hscPhysicsMarks = scanner.nextInt();
        int hscChemistryMarks = scanner.nextInt();
        int hscMathsBioMarks = scanner.nextInt();
        HSCMarks hscMarks = new HSCMarks(hscPhysicsMarks, hscChemistryMarks, hscMathsBioMarks);

        System.out.print("Enter UG marks (out of 10): ");
        double ugMarks = scanner.nextDouble();
        UGMarks ug = new UGMarks(ugMarks);

        System.out.print("Enter PG marks (out of 10): ");
        double pgMarks = scanner.nextDouble();
        PGMarks pg = new PGMarks(pgMarks);

        System.out.print("Enter number of projects: ");
        int numOfProjects = scanner.nextInt();

        System.out.print("Are you a full-time student? (true/false): ");
        boolean isFullTime = scanner.nextBoolean();

        System.out.print("Enter interview marks (out of 100): ");
        int interviewMarks = scanner.nextInt();
        Candidate candidate = new Candidate(name, dob, nationality, hscMarks, ug, pg, numOfProjects, isFullTime, interviewMarks);

        boolean isEligible = checkEligibility(candidate);
        if (isEligible) {
            System.out.println("Congratulations! You have been selected.");
        } else {
            System.out.println("Sorry, you have not been selected.");
        }
    }

    public static boolean checkEligibility(Candidate candidate) {
        // Check if the candidate meets the eligibility criteria
        boolean isEligible = true;
        if (candidate.getDateOfBirth().isBefore(LocalDate.of(1999, 6, 30))) {
            isEligible = false;
          
        }
        if (!candidate.getHscMarks().isEligible()) {
     
            isEligible = false;
           
        }
        if (!candidate.getUgMarks().isEligible()) {
            isEligible = false;
          
        }
        if (!candidate.getPgMarks().isEligible()) {
            isEligible = false;
        
        }
        if (candidate.getNumberOfProjects() < 2) {
            isEligible = false;
         
        }
        if (!candidate.isFullTime()) {
            isEligible = false;
        }
        if (candidate.getInterviewMarks() < 35) {
            isEligible = false;
         
        }
        return isEligible;
    }

    public static class Candidate {
        private String name;
        private LocalDate dateOfBirth;
        private String nationality;
        private HSCMarks hscMarks;
        private UGMarks ugMarks;
        private PGMarks pgMarks;
        private int numberOfProjects;
        private boolean isFullTime;
        private int interviewMarks;

        public Candidate(String name, LocalDate dateOfBirth, String nationality, HSCMarks hscMarks,
                         UGMarks ugMarks, PGMarks pgMarks, int numberOfProjects,
                         boolean isFullTime, int interviewMarks) {
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.nationality = nationality;
            this.hscMarks = hscMarks;
            this.ugMarks = ugMarks;
            this.pgMarks = pgMarks;
            this.numberOfProjects = numberOfProjects;
            this.isFullTime = isFullTime;
            this.interviewMarks = interviewMarks;
        }

        // Getters and setters omitted for brevity
        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public HSCMarks getHscMarks() {
            return hscMarks;
        }

        public UGMarks getUgMarks() {
            return ugMarks;
        }

        public PGMarks getPgMarks() {
            return pgMarks;
        }

        public int getNumberOfProjects() {
            return numberOfProjects;
        }

        public boolean isFullTime() {
            return isFullTime;
        }

        public int getInterviewMarks() {
            return interviewMarks;
        }
    }

    public static abstract class Marks {
        protected double marks;

        public double getMarks() {
            return marks;
            }
            public abstract boolean isEligible();
        }
        
        public static class HSCMarks extends Marks {
            private double physicsMarks;
            private double chemistryMarks;
            private double mathMarks;
        
            public HSCMarks(double physicsMarks, double chemistryMarks, double mathMarks) {
                this.physicsMarks = physicsMarks;
                this.chemistryMarks = chemistryMarks;
                this.mathMarks = mathMarks;
                this.marks = (physicsMarks + chemistryMarks + mathMarks) / 3;
            
            }
        
            @Override
            public boolean isEligible() {
                return marks >= 60.0;
            }
        
           
        }
        
        public static class UGMarks extends Marks {
            public UGMarks(double marks) {
                this.marks = marks;
            }
        
            @Override
            public boolean isEligible() {
                
                return marks >= 7.0;
            }
        
        }
        
        public static class PGMarks extends Marks {
            public PGMarks(double marks) {
                this.marks = marks;
            }
        
            @Override
            public boolean isEligible() {
                return marks >= 7.0;
            }
        
        }
    }
