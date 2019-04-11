package model.prank;

import configuration.Configuration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PrankGeneratorTest {
    @Test
    void testGenerator() {


        Configuration config = null;
        try {
            config = new Configuration();
            List<Prank> pranksList;
            PrankGenerator PG = new PrankGenerator(config);
            pranksList = PG.genererPranks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
    }
}
