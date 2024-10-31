package com.practice.swd392_demo.repository.answer;

import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.Answer;
import com.practice.swd392_demo.models.Question;
import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.question.QuestionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class AnswerRepository implements IAnswerRepository {


    List<Answer> answers = new ArrayList<>();

    public AnswerRepository() {
        sampleList(); // Populate initial data
    }
    private void sampleList() {
        List<Question> questions = new QuestionRepository().getList();
//        List<User> users = new ArrayList<>();
//        users.add(new User(1, "John", "A.", "Doe", AccountRole.STAFF));
//        users.add(new User(2, "Jane", "B.", "Smith", AccountRole.STAFF));
//        users.add(new User(3, "Alice", "C.", "Johnson",AccountRole.USER));
//        users.add(new User(4, "Bob", "D.", "Williams", AccountRole.USER));

//        answers.add(new Answer(1, "Answer 1 Title", "This is the content of answer 1", new Date(), 101, 1,users.get(0),questions.get(0)));
//        answers.add(new Answer(2, "Answer 2 Title", "This is the content of answer 2", new Date(), 102, 1, users.get(1), questions.get(0)));
//
//        answers.add(new Answer(3, "Answer 3 Title", "This is the content of answer 3", new Date(), 103, 2, users.get(2),questions.get(1)));

    }

    @Override
    public void InsertAnswer(Answer ans) {
        answers.add(ans);
    }

    @Override
    public void UpdateAnswer(Answer ans) {
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).getId() == ans.getId()) {
                answers.set(i, ans);
                return;
            }
        }
    }

    @Override
    public void DeleteAnswer(int id) {
        answers.removeIf(question -> question.getId() == id);
    }

    @Override
    public List<Answer> getList() {
        return new ArrayList<>(answers);
    }

    @Override
    public Answer GetAnswer(int id) {
        return answers.stream()
                .filter(question -> question.getId() == id)
                .findFirst()
                .orElse(null);
    }

}