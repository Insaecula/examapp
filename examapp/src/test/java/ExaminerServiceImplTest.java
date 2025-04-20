import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import service.ExaminerServiceImpl;
import service.QuestionService;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;






    class ExaminerServiceImplTest {

        private QuestionService questionService;
        private ExaminerServiceImpl examinerService;

        @BeforeEach
        void setUp() {
            questionService = mock(QuestionService.class);
            examinerService = new ExaminerServiceImpl(questionService);
        }

        @Test
        void getQuestions_shouldReturnUniqueRandomQuestions() {
            List<Question> questions = List.of(
                    new Question("Q1", "A1"),
                    new Question("Q2", "A2"),
                    new Question("Q3", "A3")
            );

            when(questionService.getAll()).thenReturn(questions);
            when(questionService.getRandomQuestion())
                    .thenReturn(questions.get(0), questions.get(1), questions.get(2));

            Collection<Question> result = examinerService.getQuestions(3);

            assertEquals(3, result.size());
            assertTrue(result.containsAll(questions));
        }

        @Test
        void getQuestions_shouldThrowIfNotEnoughQuestions() {
            when(questionService.getAll()).thenReturn(List.of(
                    new Question("Q1", "A1")
            ));

            assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(2));
        }
    }

