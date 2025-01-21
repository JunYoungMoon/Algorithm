import java.util.*;
import java.util.stream.Collectors;


class Student {
    int[] answersPattern;
    int score;
    int id;

    public Student(int id, int[] pattern, int size) {
        this.id = id;
        this.answersPattern = new int[size];
        for (int i = 0; i < size; i++) {
            answersPattern[i] = pattern[i % pattern.length];
        }
    }
}

class Solution {
    int[] answersPattern1 = new int[]{1, 2, 3, 4, 5};
    int[] answersPattern2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
    int[] answersPattern3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

    public int[] solution(int[] answers) {
        int totalQuestions = answers.length;

        List<Student> students = new ArrayList<>();

        students.add(new Student(1, answersPattern1, totalQuestions));
        students.add(new Student(2, answersPattern2, totalQuestions));
        students.add(new Student(3, answersPattern3, totalQuestions));

        for (int i = 0; i < totalQuestions; i++) {
            for (Student student : students) {
                if (student.answersPattern[i] == answers[i]) {
                    student.score++;
                }
            }
        }

        int maxScore = students.stream().mapToInt(student -> student.score).max().orElse(0);

        List<Student> topStudents = students.stream().filter(student -> {
                    return student.score == maxScore;
                })
                .sorted(Comparator.comparingInt(student -> {
                    return student.id;
                }))
                .collect(Collectors.toList());

        return topStudents.stream().mapToInt(student -> student.id).toArray();
    }
}