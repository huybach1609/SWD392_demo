package com.practice.swd392_demo.repository.question;

import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.Answer;
import com.practice.swd392_demo.models.Department;
import com.practice.swd392_demo.models.Question;
import com.practice.swd392_demo.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionRepository implements IQuestionRepository {

    private List<Question> questions = new ArrayList<>();

    public QuestionRepository() {
        sampleList(); // Populate initial data
    }
    private void sampleList() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1, "Cục hợp tác quốc tế"));
        departments.add(new Department(2, "Cục quản lí chất lượng"));
        departments.add(new Department(3, "Cục Tổ chức cán bộ"));
        departments.add(new Department(4, "Cục Nhà giáo và Cán bo quản lý giáo dục"));

        List<User> users = new ArrayList<>();
        users.add(new User(1, "John", "A.", "Doe", AccountRole.STAFF));
        users.add(new User(2, "Jane", "B.", "Smith", AccountRole.STAFF));
        users.add(new User(3, "Alice", "C.", "Johnson",AccountRole.USER));
        users.add(new User(4, "Bob", "D.", "Williams", AccountRole.USER));

        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer(1, "Answer 1 Title", "This is the content of answer 1", new Date(), 101, 1,null,null));
        answers1.add(new Answer(2, "Answer 2 Title", "This is the content of answer 2", new Date(), 102, 1, null, null));

        questions.add(new Question(1, "Question 1 Title", "This is the content of question 1", new Date(), 1, 1, answers1, users.get(0), departments.get(0)));

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer(3, "Answer 3 Title", "This is the content of answer 3", new Date(), 103, 2, null, null));

        questions.add(new Question(2, "Question 2 Title", "This is the content of question 2", new Date(), 2, 3, answers2, users.get(1), departments.get(2)));
    }

    @Override
    public void InsertQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public void UpdateQuestion(Question question) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId() == question.getId()) {
                questions.set(i, question);
                return;
            }
        }
    }

    @Override
    public void DeleteQuestion(int id) {
        questions.removeIf(question -> question.getId() == id);
    }

    @Override
    public List<Question> getList() {
        return new ArrayList<>(questions);
    }

    @Override
    public Question GetQuestion(int id) {
        return questions.stream()
                .filter(question -> question.getId() == id)
                .findFirst()
                .orElse(null);
    }

}