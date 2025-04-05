package service;

import model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl extends ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> result = new HashSet<>();
        Collection<Question> all = questionService.getAll();

        if (amount > all.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Недостаточно вопросов");
        }

        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }

        return result;
    }
}
