import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.JavaQuestionService;

import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private JavaQuestionService service;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService();
    }

    @Test
    void add_shouldAddQuestion() {
        Question q = service.add("Что такое Java?", "Язык программирования");
        assertTrue(service.getAll().contains(q));
    }

    @Test
    void remove_shouldRemoveQuestion() {
        Question q = service.add("Что такое OOP?", "Объектно-ориентированное программирование");
        Question removed = service.remove("Что такое OOP?", "Объектно-ориентированное программирование");
        assertEquals(q, removed);
        assertFalse(service.getAll().contains(q));
    }

    @Test
    void remove_shouldThrowIfNotFound() {
        assertThrows(NoSuchElementException.class, () ->
                service.remove("Несуществующий вопрос", "Ответ"));
    }

    @Test
    void getRandomQuestion_shouldReturnQuestion() {
        service.add("Q1", "A1");
        service.add("Q2", "A2");
        Question random = service.getRandomQuestion();
        assertNotNull(random);
    }

    @Test
    void getRandomQuestion_shouldThrowIfEmpty() {
        assertThrows(NoSuchElementException.class, () -> service.getRandomQuestion());
    }

    @Test
    void getAll_shouldReturnAllQuestions() {
        service.add("Q1", "A1");
        service.add("Q2", "A2");
        Collection<Question> all = service.getAll();
        assertEquals(2, all.size());
    }
}