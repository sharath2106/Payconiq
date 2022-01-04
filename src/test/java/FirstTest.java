import org.junit.jupiter.api.Test;
import utils.ApiHelper;

public class FirstTest {
    @Test
    void generateToken() {
        ApiHelper apiHelper = new ApiHelper();
        System.out.println(apiHelper.generateToken());
    }
}
