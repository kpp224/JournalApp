package KpCoding.example.JournalApp.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(UserRepositoryImp.class)  // ← force load the bean
public class UserRepositoryImpLTest {

    @Autowired
    private UserRepositoryImp userRepository;

    @Test
    public void testSaveNewUser(){
        userRepository.getUserForSA();
    }

}
