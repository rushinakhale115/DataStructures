public class SparseGradeMatrix {
    public static void main(String[] args) {
        int gradesMatrix[][] = {
                {0, 15, 0, 4, 0},
                {2, 0, 0, 0, 7},
                {0, 25, 6, 0, 0},
                {9, 0, 0, 0, 0},
                {0, 5, 0, 0, 5}
        };
        int numRows = gradesMatrix.length;
        int numCols = gradesMatrix[0].length;
// Count the number of non-zero entries in the matrix
        int nonZeroCount = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (gradesMatrix[i][j] != 0) {
                    nonZeroCount++;
                }
            }
        }
// Create a compact representation of the sparse matrix
        int compactMatrix[][] = new int[nonZeroCount][3];
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (gradesMatrix[i][j] != 0) {
                    compactMatrix[index][0] = i; // Row index
                    compactMatrix[index][1] = j; // Column index
                    compactMatrix[index][2] = gradesMatrix[i][j]; // Grade
                    index++;
                }
            }
        }
// Print the compact matrix
        for (int i = 0; i < nonZeroCount; i++) {
            for (int j = 0; j < compactMatrix[i].length; j++) {
                System.out.print(compactMatrix[i][j] + " ");
            }
            System.out.println();
        }
// Calculate average grade for subject 1
        int totalGradeSubject1 = 0;
        int subject1Count = 0;
        for (int i = 0; i < compactMatrix.length; i++) {
            if (compactMatrix[i][1] == 1) {
                totalGradeSubject1 += compactMatrix[i][2];
                subject1Count++;
            }
        }
        double averageGradeSubject1 = (subject1Count > 0) ? (double) totalGradeSubject1 /
                subject1Count : 0;
        System.out.println("Average grade of subject one : " + averageGradeSubject1);
// Find highest marks
        int highestMarks = 0;
        for (int i = 0; i < compactMatrix.length; i++) {
            if (compactMatrix[i][2] > highestMarks) {
                highestMarks = compactMatrix[i][2];
            }
        }
        System.out.println("Highest marks : " + highestMarks);
// Find the subject with the highest marks
        int subjectWithHighestMarks = 0;
        int studentIndex = 0;
        for (int i = 0; i < compactMatrix.length; i++) {
            if (compactMatrix[i][2] > subjectWithHighestMarks) {
                subjectWithHighestMarks = compactMatrix[i][2];
                studentIndex = compactMatrix[i][0];
            }
        }
        System.out.println("Subject with highest marks : " + subjectWithHighestMarks + " (Student index: " + studentIndex + ")");
    }
}